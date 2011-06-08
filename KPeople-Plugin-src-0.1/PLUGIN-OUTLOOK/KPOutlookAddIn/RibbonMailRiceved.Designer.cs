using System.Threading;

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
    partial class RibbonMailRiceved : Microsoft.Office.Tools.Ribbon.OfficeRibbon
    {
        /// <summary>
        /// Variabile di progettazione necessaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        public RibbonMailRiceved()
        {
            Thread.CurrentThread.CurrentUICulture = new System.Globalization.CultureInfo("en");
            InitializeComponent();
        }

        /// <summary> 
        /// Liberare le risorse in uso.
        /// </summary>
        /// <param name="disposing">true se le risorse gestite devono essere eliminati, altrimenti false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Codice generato da Progettazione componenti

        /// <summary>
        /// Metodo necessario per il supporto della finestra di progettazione. Non modificare
        /// il contenuto del metodo con l'editor di codice.
        /// </summary>
        private void InitializeComponent()
        {
            this.tab1 = new Microsoft.Office.Tools.Ribbon.RibbonTab();
            this.Kpeople = new Microsoft.Office.Tools.Ribbon.RibbonGroup();
            this.button1 = new Microsoft.Office.Tools.Ribbon.RibbonButton();
            this.group0 = new Microsoft.Office.Tools.Ribbon.RibbonGroup();
            this.label0 = new Microsoft.Office.Tools.Ribbon.RibbonLabel();
            this.label1 = new Microsoft.Office.Tools.Ribbon.RibbonLabel();
            this.group1 = new Microsoft.Office.Tools.Ribbon.RibbonGroup();
            this.label2 = new Microsoft.Office.Tools.Ribbon.RibbonLabel();
            this.label5 = new Microsoft.Office.Tools.Ribbon.RibbonLabel();
            this.label3 = new Microsoft.Office.Tools.Ribbon.RibbonLabel();
            this.tab1.SuspendLayout();
            this.Kpeople.SuspendLayout();
            this.group0.SuspendLayout();
            this.group1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tab1
            // 
            this.tab1.ControlId.ControlIdType = Microsoft.Office.Tools.Ribbon.RibbonControlIdType.Office;
            this.tab1.Groups.Add(this.Kpeople);
            this.tab1.Groups.Add(this.group0);
            this.tab1.Groups.Add(this.group1);
            this.tab1.Label = Localize.LocalizeString("ribbonLabel");
            this.tab1.Name = "tab1";
            // 
            // Kpeople
            // 
            this.Kpeople.Items.Add(this.button1);
            this.Kpeople.Label = Localize.LocalizeString("ribbonKpeopleGroup");
            this.Kpeople.Name = "Kpeople";
            // 
            // button1
            // 
            this.button1.ControlSize = Microsoft.Office.Core.RibbonControlSize.RibbonControlSizeLarge;
            this.button1.Label = Localize.LocalizeString("ribbonKpeopleGroupButton");
            this.button1.Image = global::KPOutlookAddIn.Properties.Resources.logo3;
            this.button1.Name = "button1";
            this.button1.ShowImage = true;
            this.button1.Click += new System.EventHandler<Microsoft.Office.Tools.Ribbon.RibbonControlEventArgs>(this.button1_Click);
            // 
            // group0
            // 
            this.group0.Items.Add(this.label0);
            this.group0.Items.Add(this.label1);
            this.group0.Label = Localize.LocalizeString("ribbonGroup0");
            this.group0.Name = "group0";
            this.group0.Visible = false;
            // 
            // label0
            // 
            this.label0.Label = Localize.LocalizeString("ribbonGroup0Label0");
            this.label0.Name = "label0";
            // 
            // label1
            // 
            this.label1.Label = Localize.LocalizeString("ribbonGroup0Label1");
            this.label1.Name = "label1";
            // 
            // group1
            // 
            this.group1.Items.Add(this.label2);
            this.group1.Items.Add(this.label5);
            this.group1.Items.Add(this.label3);
            this.group1.Label = Localize.LocalizeString("ribbonGroup1");
            this.group1.Name = "group1";
            this.group1.Visible = false;
            // 
            // label2
            // 
            this.label2.Label = Localize.LocalizeString("ribbonGroup1Label0");
            this.label2.Name = "label2";
            // 
            // label5
            //
            this.label5.Label = Localize.LocalizeString("ribbonGroup1Label1");
            this.label5.Name = "label5";
            // 
            // label3
            // 
            this.label3.Label = Localize.LocalizeString("ribbonGroup1Label2");
            this.label3.Name = "label3";
            // 
            // RibbonMailRiceved
            // 
            this.Name = "RibbonMailRiceved";
            this.RibbonType = "Microsoft.Outlook.Mail.Compose, Microsoft.Outlook.Mail.Read";
            this.Tabs.Add(this.tab1);
            this.Load += new System.EventHandler<Microsoft.Office.Tools.Ribbon.RibbonUIEventArgs>(this.RibbonMailRiceved_Load);
            this.tab1.ResumeLayout(false);
            this.tab1.PerformLayout();
            this.Kpeople.ResumeLayout(false);
            this.Kpeople.PerformLayout();
            this.group0.ResumeLayout(false);
            this.group0.PerformLayout();
            this.group1.ResumeLayout(false);
            this.group1.PerformLayout();
            this.ResumeLayout(false);

        }

        public void createDinamicallyRibbon(List<ListBox> listCheckedListBox)
        {
            int index = 0;
            for (int i = 0; i < listCheckedListBox.Count; i++)
            {
                for (int j = 0; j < listCheckedListBox[i].Items.Count; j++)
                {
                    if (listCheckedListBox[i].GetSelected(j))
                    {
                        //string text = listCheckedListBox[i].Text;
                        string processKey = (listCheckedListBox[i].Items[j].ToString()).Split('-')[0];
                        string processDescription = (listCheckedListBox[i].Items[j].ToString()).Split('-')[1];
                        string groupName = "group" + index;
                        index++;
                        RibbonGroup group = getGroup(groupName);
                        RibbonLabel keyProcess = (RibbonLabel)group.Items[0];
                        RibbonLabel descriptionProcess = (RibbonLabel)group.Items[1];
                        keyProcess.Label = "Processo - " + processKey;
                        descriptionProcess.Label = processDescription;
                        group.Visible = true;

                    }
                }
            }
        }

        public void createDinamicallyRibbonFromMetadata(MetadataSet metadataSet)
        {
            int index = 0;
            for (int i = 0; i < metadataSet.cmpMetadataList.Count; i++)
            {
                CompoundMetadata comp = metadataSet.cmpMetadataList[i];
                for (int j = 0; j < comp.metadataList.Count; j++)
                {
                    Metadata metadata = comp.metadataList[j];
                    for (int z = 0; z < metadata.valueList.Count; z++)
                    {
                        Value val = metadata.valueList[z];
                        string groupName = "group" + index;
                        string processKey = val.getValue();
                        string processDescription = val.getDescription();
                        RibbonGroup group = getRibbonGroup(groupName);
                        RibbonLabel keyProcess = (RibbonLabel)group.Items[0];
                        RibbonLabel descriptionProcess = (RibbonLabel)group.Items[1];
                        keyProcess.Label = "Processo - " + processKey;
                        descriptionProcess.Label = processDescription;
                        group.Visible = true;
                        index++;
                    }
                }
            }

        }

        private RibbonGroup getGroup(string groupName)
        {
            RibbonGroup group = null;
            for (int i = 0; i < this.tab1.Groups.Count; i++)
            {
                group = this.tab1.Groups[i];
                if (group.Name == groupName)
                {
                    break;
                }
            }
            return group;
        }

        #endregion

        internal Microsoft.Office.Tools.Ribbon.RibbonTab tab1;
        internal Microsoft.Office.Tools.Ribbon.RibbonGroup group0;
        internal Microsoft.Office.Tools.Ribbon.RibbonLabel label0;
        internal Microsoft.Office.Tools.Ribbon.RibbonLabel label1;
        internal Microsoft.Office.Tools.Ribbon.RibbonGroup group1;
        internal Microsoft.Office.Tools.Ribbon.RibbonLabel label2;
        internal Microsoft.Office.Tools.Ribbon.RibbonLabel label3;
        internal Microsoft.Office.Tools.Ribbon.RibbonLabel label5;
        internal Microsoft.Office.Tools.Ribbon.RibbonGroup Kpeople;
        internal Microsoft.Office.Tools.Ribbon.RibbonButton button1;
    }

     partial class ThisRibbonCollection : Microsoft.Office.Tools.Ribbon.RibbonReadOnlyCollection
    {
        internal RibbonMailRiceved RibbonMailRiceved
        {
            get { return this.GetRibbon<RibbonMailRiceved>(); }
        }
    }
}
