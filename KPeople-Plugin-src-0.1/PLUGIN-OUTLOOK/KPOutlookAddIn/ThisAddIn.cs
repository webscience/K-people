using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using System.Windows.Forms;
using Outlook = Microsoft.Office.Interop.Outlook;
using Office = Microsoft.Office.Core;
using Tools = Microsoft.Office.Tools;
using System.Collections;
using Microsoft.Office.Tools;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using System.Xml;
using System.IO;
using Microsoft.Office.Interop.Outlook;
using System.Configuration;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;
namespace KPOutlookAddIn
{

public partial class ThisAddIn
{

    Outlook.Inspectors inspectors = null;
    private Cachemanager cache = null;
    public MetadataSet res = null;
    public Outlook.Folder _inbox = null;
    public Outlook.Items _items = null;
    public string attachmentName = null;
    public string attachmentPath = null;
    public String currMailItem = null;
    private List<Outlook.Items> listItems = null;

    public enum RIBBON_TYPE { Insert=0, Reply=1, Read=2 };

    public Hashtable Ribbons = null;
    public Hashtable MailItems = null;
    public Hashtable RibbonsType = null;

    private Logger log = null;

    private void Init()
    {
        const string pos = "ThisAddIn.Init - ";
        log = new Logger();

        log.Start();

        log.Info(pos + "INIT");

        AddKPCategory();

        attachmentName = Constants.KP_ATTACHMENT_NAME;
        attachmentPath = Constants.getWorkFolder();

        //Inizializzazione degli inspector
        inspectors = this.Application.Inspectors;
        inspectors.NewInspector += new Microsoft.Office.Interop.Outlook.InspectorsEvents_NewInspectorEventHandler(Inspectors_NewInspector);

        //Inizializzazione della cache
        cache = Cachemanager.instanceCachemanager();
        
        Ribbons = new Hashtable();
        MailItems = new Hashtable();
        RibbonsType = new Hashtable();

        //Inizializzazione della lista della lista delle cartelle da monitorare
        listItems = new List<Outlook.Items>();

        log.Info(pos + "END");        
    }

    private void ThisAddIn_Startup(object sender, System.EventArgs e)
    {
        try
        {
            const string pos = "ThisAddIn.ThisAddIn_Startup - ";
            Init();

            log.Info(pos + "INIT");

            //TO-DO creare un pulsante nella barra per avviare la scansione delle mail già ricevute
            //InterceptArrivedMails();

            HooksNewMail();

            log.Info(pos + "END");
        }
        catch (System.Exception Ex)
        {
            MessageBox.Show(Ex.ToString());
            log.Error("ThisAddIn_Startup - Ex:" + Ex.ToString());
        }

        log.Info("ThisAddIn_Startup - END");
    }

    private void HooksNewMail()
    {
        string pos = "ThisAddIn.HooksNewMail - ";

        log.Info(pos + "INIT");

        Outlook.Application app = new Outlook.ApplicationClass();
        _NameSpace ns = app.GetNamespace("MAPI");
        ns.Logon(null, null, false, false);

        log.Info(pos + "Inizio hooking delle folder");

        Outlook.Folders collectionFolders = ns.Folders;
        for (int i = 1; i <= collectionFolders.Count; i++)
        {
            if (collectionFolders[i].Store.DisplayName.ToLower().EndsWith(Constants.KP_DOMAIN.ToLower()))
            {
                log.Info(pos + "Hook della root:" + collectionFolders[i].Name);

                Outlook.Folder rootFolder = (Outlook.Folder)collectionFolders[i];
                Outlook.Folders collectionRootFolders = rootFolder.Folders;
                for (int j = 1; j <= collectionRootFolders.Count; j++)
                {
                    Outlook.Folder childFolder = (Outlook.Folder)collectionRootFolders[j];

                    log.Info(pos + "Hook della cartella:" + childFolder.Name);

                    listItems.Add(childFolder.Items);
                    listItems[j - 1].ItemAdd += new Outlook.ItemsEvents_ItemAddEventHandler(FlagKpeopleMail);
                }

            }
        }

        log.Info(pos + "END");
    }

    /**
     * metodo che processa le nuove mail  
     * per poter poi categorizzare quelle generate 
     * dal plugin KPeople.
     */
    private void InterceptArrivedMails()
    {
        string pos = "ThisAddIn.InterceptArrivedMail - ";
        log.Info( pos + "INIT");

        Microsoft.Office.Interop.Outlook.Application app = new Microsoft.Office.Interop.Outlook.ApplicationClass();
        _NameSpace ns = app.GetNamespace("MAPI");
        ns.Logon(null, null, false, false);


        // Cartella di default: "Posta in arrivo"

        Folders collectionFolder = ns.Folders;
        for (int i = 1; i <= collectionFolder.Count; i++)
        {
            MAPIFolder folder = collectionFolder[i];
            

            scanSubFolder(folder);
        }

        log.Info(pos + "END");
    }

    private void scanSubFolder(MAPIFolder folder)
    {
        return;
        Folders collectionSubFolder = folder.Folders;
        for (int i = 1; i <= collectionSubFolder.Count; i++)
        {
            MAPIFolder subFolder = collectionSubFolder[i];
            object itemObj = null;

            for (int j = 1; j <= subFolder.Items.Count; j++)
            {
                itemObj = subFolder.Items[j];
                FlagKpeopleMail(itemObj);
            }
        }
    }


    private void ThisAddIn_Shutdown(object sender, System.EventArgs e)
    {
        log.Stop();
    }

    Outlook.ItemEvents_10_Event mailEvents;

    /**
     * @param Inspector rappresenta un oggetto
     * di tipo Outlook.Inspector restituito dalla registrazione dell'evento 
     * InspectorsEvents_NewInspectorEventHandler(Inspectors_NewInspector)
     * Il metodo crea un oggetto di tipo Outlook.MailItem e registra l'evento
     * Inspector.CurrentItem as Outlook.ItemEvents_10_Event
     */
    void Inspectors_NewInspector(Outlook.Inspector Inspector)
    {
        string pos = "ThisAddIn.Inspectors_NewInspector - ";

        log.Info(pos + "INIT");

        currMailItem = Guid.NewGuid().ToString();

        if (MailItems.ContainsKey(Globals.ThisAddIn.currMailItem))
            MailItems.Remove(Globals.ThisAddIn.currMailItem);
        MailItems.Add(currMailItem, Inspector.CurrentItem);

        if (Inspector.CurrentItem is Outlook.MailItem)
        {
            mailEvents = Inspector.CurrentItem as Outlook.ItemEvents_10_Event;
            mailEvents.Reply += new Outlook.ItemEvents_10_ReplyEventHandler(mail_Reply);
            mailEvents.ReplyAll += new Outlook.ItemEvents_10_ReplyAllEventHandler(mail_Reply);
            mailEvents.Forward += new Outlook.ItemEvents_10_ForwardEventHandler(mail_Reply);
            mailEvents.Read += new ItemEvents_10_ReadEventHandler(mailEvents_Read);
            mailEvents.Open += new ItemEvents_10_OpenEventHandler(mailEvents_Open);
        }

        log.Info(pos + "END");
    }
    

    /**
     * Aggiunge alle categorie la categoria KPeople 
     */
    private void AddKPCategory()
    {
        const string pos = "ThisAddIn.AddKPCategory - ";
        log.Info(pos + "INIT");
        try
        {
            Outlook.OlCategoryColor kpCategoryColor = Constants.KP_COLOR;
            Outlook.OlCategoryShortcutKey kpCategoryShortcut = Constants.KP_CATEGORY_SHORTCUT;

            IEnumerator categoriesEnum = this.Application.Session.Categories.GetEnumerator();
            bool kpFounded = false;
            while (categoriesEnum.MoveNext())
            {
                if (((Outlook.Category)categoriesEnum.Current).Name.Equals(Constants.KP_CATEGORY_NAME))
                {
                    log.Info(pos + "Categoria Kpeople già presente");
                    kpFounded = true;
                    break;
                }
            }

            if (!kpFounded)
                this.Application.Session.Categories.Add(Constants.KP_CATEGORY_NAME, kpCategoryColor, kpCategoryShortcut);
        }
        catch (System.Exception ex)
        {
            log.Error(ex.Message);
        }
        log.Info(pos + "INIT");
    }

    #region VSTO generated code

    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    private void InternalStartup()
    {
        this.Startup += new System.EventHandler(ThisAddIn_Startup);
        this.Shutdown += new System.EventHandler(ThisAddIn_Shutdown);
    }

    #endregion

    /**
     * @param item rappresenta un oggetto di tipo object
     * passato dal metodo InterceptArrivedMails(). Contrassegna, come appartenenti alla
     * categoria KPeople, tutte le emails che hanno un allegato "KPeople.xml"
     */
    private void FlagKpeopleMail(object item)
    {
        string pos = "ThisAddin.FlagKpeopleMail - ";

        log.Info(pos + "INIT");

        bool find = false;
        try
        {
            if (item != null)
            {
                Outlook.MailItem mailItem = item as Outlook.MailItem;

                if (mailItem.Attachments != null)
                {
                    for (int i = 1; i <= mailItem.Attachments.Count; i++)
                    {
                        if (mailItem.Attachments[i].FileName.Equals(attachmentName))
                        {
                            find = true;
                        }
                    }
                    if (find)
                    {
                        mailItem.Categories = Constants.KP_CATEGORY_NAME;
                        mailItem.Save();
                    }
                }
            }
        }
        catch (System.Exception e)
        {
            MessageBox.Show(e.ToString());
            //throw e;
        }

        log.Info(pos + "END");
    }

    /**
     * @param Response rappresenta un oggetto di tipo object
     * passato dal metodo Outlook.ItemEvents_10_ReplyEventHandler(mail_Reply).
     * Il metodo in questione permette di riassociare alla email di risposta, 
     * derivante dalla selezione del button Reply to, l'aattachment KPeople 
     * allegato alla mail KPeople ricevuta.
     */
    void mail_Reply(object Response, ref bool Cancel)
    {
        string pos = "ThisAddIn.Reply - ";
        log.Info(pos + "INIT");

        if (Response is Outlook.MailItem)
        {
            Outlook.MailItem replymailitem = Response as Outlook.MailItem;

            if (replymailitem != null)
            {

                for (int i = 1; i <= ((Outlook.MailItem)MailItems[currMailItem]).Attachments.Count; i++)
                {
                    if (((Outlook.MailItem)MailItems[currMailItem]).Attachments[i].FileName == Constants.KP_ATTACHMENT_NAME)
                    {
                        TemporaryStorage store = new TemporaryStorage();

                        store.Save(Constants.KP_ATTACHMENT_NAME, ((Outlook.MailItem)MailItems[currMailItem]).Attachments[i]);

                        bool found = false;

                        for (int z = 1; z <= replymailitem.Attachments.Count; z++)
                        {
                            if (replymailitem.Attachments[z].FileName == Constants.KP_ATTACHMENT_NAME)
                            {
                                found = true;
                            }
                        }

                        if (!found)
                        {
                            replymailitem.Attachments.Add(store.GetFullFileName(), Outlook.OlAttachmentType.olByValue, Type.Missing, Type.Missing);
                        }
                        
                        store.DeleteFolder();

                        if (!Constants.KP_MAIL_BCC.Equals(""))
                        {
                            if (replymailitem.BCC == null || !replymailitem.BCC.Contains(Constants.KP_MAIL_BCC))
                            {
                                replymailitem.BCC += Constants.KP_MAIL_BCC + ";";
                            }
                        }

                        FlagKpeopleMail(replymailitem);
                    }
                }

                replymailitem.Display(false);                   
            }
        }

        log.Info(pos + "Ribbon TYPE: Reply");

        if (RibbonsType.ContainsKey(Globals.ThisAddIn.currMailItem))
            RibbonsType.Remove(Globals.ThisAddIn.currMailItem);

        RibbonsType.Add(currMailItem, RIBBON_TYPE.Reply);

        populateRibbon();

        log.Info(pos + "END");
        
    }
    void  mailEvents_Read()
    {
        string pos = "ThisAddIn.Read - ";
        log.Info(pos + "INIT");

        if (RibbonsType.ContainsKey(Globals.ThisAddIn.currMailItem))
            RibbonsType.Remove(Globals.ThisAddIn.currMailItem);


        if (((Outlook.MailItem)MailItems[currMailItem]).Categories.IndexOf(Constants.KP_CATEGORY_NAME) >= 0)
        {
            log.Info(pos + "Selezionata Mail Kpeople!");
            RibbonsType.Add(currMailItem, RIBBON_TYPE.Read);
            log.Info(pos + "Ribbon TYPE: Read");
        }
        else
        {
            log.Info(pos + "Non è stata selezionata Mail Kpeople!");
            RibbonsType.Add(currMailItem, RIBBON_TYPE.Insert);
            log.Info(pos + "Ribbon TYPE: Insert");
        }

        populateRibbon();

        log.Info(pos + "END");
    }

    private void populateRibbon()
    {
        if (Ribbons.ContainsKey(currMailItem))
        {
            RibbonMailRiceved curRibbon = (RibbonMailRiceved)Ribbons[currMailItem];
            curRibbon.Populate((RIBBON_TYPE)RibbonsType[currMailItem], (MailItem)MailItems[currMailItem]);
        } 
    }

    void mailEvents_Open(ref bool Cancel)
    {
        mailEvents_Read();
    }

}
}


