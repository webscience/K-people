using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Office.Tools.Ribbon;
using System.Xml;


using Office = Microsoft.Office.Core;
using Outlook = Microsoft.Office.Interop.Outlook;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.pluginsystemintegrator;
using System.Windows.Forms;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;


namespace KPOutlookAddIn
{
    public partial class RibbonMailRiceved
    {
        private Logger log;
        private Outlook.MailItem currMailItem;

        private void RibbonMailRiceved_Load(object sender, RibbonUIEventArgs e)
        {
            if (Globals.ThisAddIn.Ribbons.ContainsKey(Globals.ThisAddIn.currMailItem))
                Globals.ThisAddIn.Ribbons.Remove(Globals.ThisAddIn.currMailItem);
            Globals.ThisAddIn.Ribbons.Add(Globals.ThisAddIn.currMailItem, this);

            if (Globals.ThisAddIn.RibbonsType.Contains(Globals.ThisAddIn.currMailItem))
                Populate((KPOutlookAddIn.ThisAddIn.RIBBON_TYPE)Globals.ThisAddIn.RibbonsType[Globals.ThisAddIn.currMailItem], (Outlook.MailItem)Globals.ThisAddIn.MailItems[Globals.ThisAddIn.currMailItem]);
            else
                Populate(KPOutlookAddIn.ThisAddIn.RIBBON_TYPE.Insert, (Outlook.MailItem)Globals.ThisAddIn.MailItems[Globals.ThisAddIn.currMailItem]);
        }

        public void Populate(KPOutlookAddIn.ThisAddIn.RIBBON_TYPE showRibbon, Outlook.MailItem mail)
        {
            log = new Logger();

            currMailItem = mail;

            const string pos = "RibbonMailRiceved.RibbonMailRiceved_Load - ";
            log.Info(pos + "INIT");

            string pathWorkFolder = Constants.getWorkFolder();
            string attachmentName = Constants.KP_ATTACHMENT_NAME;

            log.Info(pos + "pathWorkFolder:" + pathWorkFolder);
            log.Info(pos + "attachmentName:" + attachmentName);

            try
            {

                if (showRibbon == ThisAddIn.RIBBON_TYPE.Read)
                {
                    //Disabilito il pulsante kpeople
                    this.button1.Enabled = false;

                    //Leggo le informazioni dall'attachment
                    Outlook.Attachment attach = mail.Attachments[attachmentName];
                    if (attach != null)
                    {
                        //Salvo temporaneamente il file 
                        TemporaryStorage storage = new TemporaryStorage();
                        XmlDocument docXml = new XmlDocument();
                        MetadataParser parser = new MetadataParser();
                        docXml.Load(storage.Save(attach));

                        createDinamicallyRibbonFromMetadata(parser.parserMetadataSetFromXML(docXml));
                        storage.DeleteFolder();
                    }

                }
                else if (showRibbon == ThisAddIn.RIBBON_TYPE.Reply)
                {
                    //Disabilito il pulsante kpeople
                    this.button1.Enabled = true;

                    //Leggo le informazioni dall'attachment
                    Outlook.Attachment attach = mail.Attachments[attachmentName];
                    if (attach != null)
                    {
                        //Salvo temporaneamente il file 
                        TemporaryStorage storage = new TemporaryStorage();
                        XmlDocument docXml = new XmlDocument();
                        MetadataParser parser = new MetadataParser();
                        docXml.Load(storage.Save(attach));

                        createDinamicallyRibbonFromMetadata(parser.parserMetadataSetFromXML(docXml));
                        storage.DeleteFolder();
                    }
                }
                else
                {
                    this.button1.Enabled = true;
                }
            }
            catch (Exception ex)
            {
                log.Error(pos + "Ex:" + ex.ToString());
            }

            log.Info(pos + "END");
        }


        private RibbonGroup getRibbonGroup(string groupName)
        {
            string pos = "RibbonMailRiceved - ";

            log.Info(pos + "INIT");

            RibbonGroup group = null;
            for (int i = 0; i < this.tab1.Groups.Count; i++)
            {
                group = this.tab1.Groups[i];
                if (this.tab1.Groups[i].Name == groupName)
                {
                    break;
                }
            }

            log.Info(pos + "END");

            return group;
        }

        private void button1_Click(object sender, RibbonControlEventArgs e)
        {
            string pos = "RibbonMailRiceved - ";

            log.Info(pos  + "INIT");

            try
            {
                Cachemanager cache = Cachemanager.instanceCachemanager();

                MetadataSet res = cache.getAvailableMetadataSet();

                if (currMailItem != null)
                {
                    //TO-DO sono quì
                    TagSelectorForm tagSelectorForm = new TagSelectorForm();
                    tagSelectorForm.createDinamicallyUi(res);
                    tagSelectorForm.setMailItem(currMailItem);
                    tagSelectorForm.setRibbon(this);
                    tagSelectorForm.ShowDialog();

                    currMailItem.Categories = Constants.KP_CATEGORY_NAME;
                    currMailItem.Display(true);
                    currMailItem.Save();
                }

            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
                log.Error("button1_Click - Ex:" + ex.Message);
            }

            log.Info(pos + "END");
        }
    }
}
