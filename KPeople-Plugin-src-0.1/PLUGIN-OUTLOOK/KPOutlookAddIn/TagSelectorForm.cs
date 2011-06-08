using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Outlook = Microsoft.Office.Interop.Outlook;
using System.Collections;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using System.Net.Mime;
using System.Configuration;
using Microsoft.Office.Core;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;
using System.IO;

namespace KPOutlookAddIn
{   
    public partial class TagSelectorForm : Form
    {
        string sDisplayName;  
        Outlook.MailItem mailItem;
        RibbonMailRiceved myRibbon;
        Microsoft.Office.Interop.Outlook.Inspector explorer = null;

        Logger log = null;
        
        public void setMailItem(Outlook.MailItem mailItem)
        {
            this.mailItem = mailItem;
        }
        public TagSelectorForm()
        {
            InitializeComponent();

            log = new Logger();
        }

        private void btnIgnore_Click(object sender, EventArgs e)
        {
            
            this.mailItem.Body = " ";
            this.mailItem.Subject = " ";
            this.mailItem.Categories = "";
            this.Close();
        }
         
        private void search_TextChanged(object sender, EventArgs e)
        {
            string pos = "TagSelectorForm.search_TextChanged - ";

            log.Info(pos + "INIT");
            try
            {
                string text = ((TextBox)sender).Text;
                int i = Convert.ToInt16(((TextBox)sender).Name);
                ListBox ckdListBox = listListBox[i];
                string ckdListBoxName = ckdListBox.Name;
                populateDinamicallySelectionList(ckdListBoxName, text);
            }
            catch (Exception ex)
            {
                log.Warning(pos + ex.Message);
                log.Warning(pos + ex.StackTrace);
            }

            log.Info(pos + "END");
        }

        private void search_MouseClick(object sender, EventArgs e)
        {
            string pos = "TagSelectorForm.search_MouseClick - ";

            log.Info(pos + "INIT");
            try
            {
                ((TextBox)sender).Text = "";
            }
            catch (Exception ex)
            {
                log.Warning(pos + "Execption:" + ex.Message);
            }
            log.Info(pos + "END");
        }

        private void btnAddTags_Click(object sender, EventArgs e)
        {
            string pos = "TagSelectorForm.btnAddTags_Click - ";
            log.Info(pos + "INIT");

            if (this.mailItem != null)
            {
                for (int i = 0; i < listListBox.Count; i++)
                {
                    string checkListBoxName = listListBox[i].Name;
                    int compoundContainer = int.Parse(checkListBoxName.Split(':')[0]);
                    int metadatoContainer = int.Parse(checkListBoxName.Split(':')[1]);

                    bool removeUnselected = true;

                    while(removeUnselected)
                    {
                        removeUnselected = false;
                        List<Value> listValue = metadataSet.cmpMetadataList[compoundContainer].metadataList[metadatoContainer].valueList;
                        for (int v = 0; v < listValue.Count; v++)
                        {
                            bool finded = false;
                            string curValue = listValue[v].getValue();
                            for (int j = 0; j < listListBox[i].Items.Count; j++)
                            {
                                if (listListBox[i].GetSelected(j) && listListBox[i].Items[j].ToString().StartsWith(curValue))
                                {
                                    finded = true;
                                    break;
                                }
                            }

                            if (!finded)
                            {
                                listValue.RemoveAt(v);
                                removeUnselected = true;
                                break;
                            }
                            else
                            {
                                listValue[v].listExtraData.RemoveRange(0, listValue[v].listExtraData.Count);
                            }
                        }
                    }

                    createNewKPDocument(metadataSet.convertToCompleteXml());
                    Cachemanager cache = Cachemanager.instanceCachemanager();
                    MetadataSet metadataSetOriginal = cache.getMetadataSetFromFileSystem();
                    cache.updateExtraData(metadataSet, metadataSetOriginal);

                    myRibbon.createDinamicallyRibbon(listListBox);
                    
                }
            }
            this.Close();

            log.Info(pos + "END");
        }

        private void createNewKPDocument(string xml)
        {
            const string pos = "TagSelectorForm.createNewKPDocument - ";

            log.Info(pos + "INIT");

            try
            {
                Outlook.Attachment oAttach = null;
                for (int i = 0; i < mailItem.Attachments.Count; i++)
                {
                    oAttach = mailItem.Attachments[i+1];
                    if (oAttach.FileName.Equals(Constants.KP_ATTACHMENT_DISPLAY_NAME + Constants.KP_ATTACHMENT_EXTENSION))
                    {
                        mailItem.Attachments.Remove(i+1);
                    }
                }

                TemporaryStorage store = new TemporaryStorage();
                store.Save(Constants.KP_ATTACHMENT_DISPLAY_NAME + Constants.KP_ATTACHMENT_EXTENSION, xml);

                log.Info(pos + "Leggo il file: " + store.GetFullFileName());

                oAttach = mailItem.Attachments.Add(store.GetFullFileName(), (int)Outlook.OlAttachmentType.olByValue, Type.Missing, sDisplayName);

                store.DeleteFolder();

                if (!Constants.KP_MAIL_BCC.Equals(""))
                {
                    if (mailItem.BCC == null || !mailItem.BCC.Contains(Constants.KP_MAIL_BCC))
                    {
                        mailItem.BCC += Constants.KP_MAIL_BCC + ";";
                    }
                }
            }
            catch (Exception ex)
            {
                log.Error(pos + "Exception:" +ex.Message);
            }
            log.Info(pos + "END");
        }

        private void TagSelectorForm_Load(object sender, EventArgs e)
        {

        }

        internal void setRibbon(RibbonMailRiceved ribbonProva)
        {
            myRibbon = ribbonProva;
        }
    }
}
