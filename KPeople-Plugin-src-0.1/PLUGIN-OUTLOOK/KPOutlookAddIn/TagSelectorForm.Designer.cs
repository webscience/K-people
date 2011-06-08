using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Drawing;
using System;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;
namespace KPOutlookAddIn
{
    partial class TagSelectorForm
    {

        List<Label> listMetadatoName = null;
        List<RichTextBox> listMetadatoDescription = null;
        List<TextBox> listSearch = null;
        List<ListBox> listListBox = null;
        MetadataSet metadataSet = null;


        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }
        public void createDinamicallyUi(MetadataSet metadataSet)
        {
            int p = 0;
            this.metadataSet = metadataSet;
            List<CompoundMetadata> listCmpMetadata = metadataSet.cmpMetadataList;
            List<Metadata> listMetadata = null;
            int q = 0;
            for (int i = 0; i < listCmpMetadata.Count; i++)
            {
                listMetadata = listCmpMetadata[i].metadataList;
                for (int j = 0; j < listMetadata.Count; j++)
                {
                    Metadata metadata = listMetadata[j];
                    ckdListBox = new ListBox();
                    TextBox search = new TextBox();
                    metadatoName = new Label();
                    //metadatoDescription = new RichTextBox();
                    ckdListBox.FormattingEnabled = true;
                    ckdListBox.Name = i.ToString() + ":" + j.ToString() + ":" + q.ToString();
                    ckdListBox.ScrollAlwaysVisible = true;
                    ckdListBox.Size = new System.Drawing.Size(400, 120);
                    ckdListBox.TabIndex = 4;
                    metadatoName.Name = q.ToString();
                    metadatoName.Size = new System.Drawing.Size(180, 30);
                    metadatoName.TabIndex = 5;
                    /*metadatoDescription.Name = q.ToString();
                    metadatoDescription.Size = new System.Drawing.Size(180, 40);
                    metadatoDescription.TabIndex = 5;*/
                    search.Name = q.ToString();
                    search.Size = new System.Drawing.Size(180, 20);
                    search.TabIndex = 5;
                    listMetadatoName.Add(metadatoName);
                    //listMetadatoDescription.Add(metadatoDescription);
                    search.TextChanged += new System.EventHandler(search_TextChanged);
                    search.MouseClick += new MouseEventHandler(search_MouseClick);
                    search.Text = Constants.KP_TAG_SELECTOR_SEARCH_DEFAULT_TEXT;
                    listSearch.Add(search);
                    listListBox.Add(ckdListBox);
                    if (p == 0)
                    {
                        ckdListBox.Location = new System.Drawing.Point(13, 70);
                        metadatoName.Location = new System.Drawing.Point(13, 13);
                        //metadatoDescription.Location = new System.Drawing.Point(13, 40);
                        search.Location = new System.Drawing.Point(210, 12);
                    }
                    else
                    {
                        ckdListBox.Location = new System.Drawing.Point(13, 70 + 185 * p);
                        metadatoName.Location = new System.Drawing.Point(13, 13 + 185 * p);
                        //metadatoDescription.Location = new System.Drawing.Point(13, 40 + 185 * p);
                        search.Location = new System.Drawing.Point(210, 12 + 185 * p);
                    }
                    p = p + 1;
                    //metadatoName.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
                    metadatoName.Text = metadata.getDescription();
                    metadatoName.Text = Constants.KP_METADATASET_TAGSELECTOR_LABEL;
                    //metadatoName.BackColor = Color.White;
                    metadatoName.Font = new Font("Microsoft Sans Serif", 10, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0))); 
                    //Commentato per semplificare l'interfaccia
                    /*metadatoDescription.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
                    metadatoDescription.Text = metadata.getDescription();
                    metadatoDescription.BackColor = Color.White;
                    metadatoDescription.Font = new Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                    metadatoDescription.Text = metadata.getDescription();*/
                    //ckdListBox.Items.Add("Processo " + metadata.getKey() + " " + metadata.getDescription());
                    List<Value> listValue = new List<Value>();
                    listValue = metadata.valueList;
                    populateDinamicallySelectionList(ckdListBox.Name, "");
                    this.Controls.Add(ckdListBox);
                    this.Controls.Add(metadatoName);
                    //this.Controls.Add(metadatoDescription);
                    this.Controls.Add(search);
                    q = q + 1;
                }
            }
            btnAddTags = new Button();
            btnAddTags.Location = new System.Drawing.Point(13, 196 + 25 * listMetadata.Count);
            btnAddTags.Name = "btnAddTags";
            btnAddTags.Size = new System.Drawing.Size(75, 23);
            btnAddTags.TabIndex = 1;
            btnAddTags.Text = Constants.KP_TAG_BUTTON_INSERT_TEXT;
            btnAddTags.UseVisualStyleBackColor = true;
            btnAddTags.Click += new System.EventHandler(this.btnAddTags_Click);
            btnIgnore = new Button();
            btnIgnore.Location = new System.Drawing.Point(94, 196 + 25 * listMetadata.Count);
            btnIgnore.Name = "btnIgnore";
            btnIgnore.Size = new System.Drawing.Size(75, 23);
            btnIgnore.TabIndex = 2;
            btnIgnore.Text = Constants.KP_TAG_BUTTON_CANCEL_TEXT;
            btnIgnore.UseVisualStyleBackColor = true;
            btnIgnore.Click += new System.EventHandler(this.btnIgnore_Click);
            this.Controls.Add(this.btnIgnore);
            this.Controls.Add(this.btnAddTags);
            this.ResumeLayout(false);
            this.PerformLayout();
        }
        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            listMetadatoName = new List<Label>();
            listMetadatoDescription = new List<RichTextBox>();
            listSearch = new List<TextBox>();
            listListBox = new List<ListBox>();

            this.btnAddTags = new System.Windows.Forms.Button();
            this.btnIgnore = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnAddTags
            // 
            this.btnAddTags.Location = new System.Drawing.Point(0, 0);
            this.btnAddTags.Name = "btnAddTags";
            this.btnAddTags.Size = new System.Drawing.Size(75, 23);
            this.btnAddTags.TabIndex = 0;
            this.btnAddTags.Click += new System.EventHandler(this.btnAddTags_Click);
            // 
            // btnIgnore
            // 
            this.btnIgnore.Location = new System.Drawing.Point(0, 0);
            this.btnIgnore.Name = "btnIgnore";
            this.btnIgnore.Size = new System.Drawing.Size(75, 23);
            this.btnIgnore.TabIndex = 0;
            this.btnIgnore.Click += new System.EventHandler(this.btnIgnore_Click);
            // 
            // TagSelectorForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(422, 244);
            this.Name = "TagSelectorForm";
            this.Text = Localize.LocalizeString("tagSelector");
            this.Load += new System.EventHandler(this.TagSelectorForm_Load);
            this.ResumeLayout(false);

        }

        public void populateDinamicallySelectionList(string objCounter, string searchedText)
        {
            string pos = "TagSelectorForm.populateDinamicallySelectionList - ";

            log.Info(pos + "INIT");

            log.Info(pos + "objCounter:" + objCounter);
            log.Info(pos + "searchedText:" + searchedText);

            List<CompoundMetadata> listCmpMetadata = metadataSet.cmpMetadataList;
            int compoundMetadato = int.Parse(objCounter.Split(':')[0]);
            int metadataCounter = int.Parse(objCounter.Split(':')[1]);
            int listBoxCounter = int.Parse(objCounter.Split(':')[2]);

            Metadata metadato = listCmpMetadata[compoundMetadato].metadataList[metadataCounter];
            List<Value> valuesMetadato = metadato.valueList;
            ListBox ckdListBox = listListBox[listBoxCounter];

            valuesMetadato = orderMetadataValue(valuesMetadato);
            for (int i = valuesMetadato.Count - 1; i >= 0; i--)
            {
                Value value = valuesMetadato[i];

                if (!(valueChecked(value, ckdListBox)) && (value.getState() == Value.PROCESS_STATE.Open))
                {

                    if (textFound(value, searchedText))
                    {
                        if (!(existCheckBox(value, ckdListBox)))
                            ckdListBox.Items.Add(value.getValue() + "-" + value.getDescription());
                    }
                    else
                    {
                        removeCheckBox(value, ckdListBox);
                    }
                }
            }

            log.Info(pos + "END");
        }

        private List<Value> orderMetadataValue(List<Value> valuesMetadato)
        {
            
            Value val = new Value();
            
            for (int i = 0; i < valuesMetadato.Count - 1; i++)
            {
                
                for (int j = i+1; j < valuesMetadato.Count; j++)
                {
                    if (valuesMetadato[i].listExtraData.Count == 0)
                    {
                       
                        break;      
                    }
                    if (valuesMetadato[j].listExtraData.Count == 0)
                    {
                        val = valuesMetadato[i];
                        valuesMetadato[i] = valuesMetadato[j];
                        valuesMetadato[j] = val;
                        break;
                    }
                        if ((int.Parse(valuesMetadato[i].listExtraData[0].getValue())) > (int.Parse(valuesMetadato[j].listExtraData[0].getValue())))
                        {
                            val = valuesMetadato[i];
                            valuesMetadato[i] = valuesMetadato[j];
                            valuesMetadato[j] = val;
                        }
                }
            }

            return valuesMetadato;
        }

        private bool existCheckBox(Value value, ListBox ckdListBox)
        {
            bool retVal = false;
            string val = value.getValue() + "-" + value.getDescription();
            for (int i = 0; i < ckdListBox.Items.Count; i++)
            {
                if (val.Equals(ckdListBox.Items[i]))
                {
                    retVal = true;
                    break;
                }
            }
            return retVal;
        }

        private void removeCheckBox(Value value, ListBox ckdListBox)
        {
            string val = value.getValue() + "-" + value.getDescription();
            for (int i = 0; i < ckdListBox.Items.Count; i++)
            {
                if (val.Equals(ckdListBox.Items[i]))
                {
                    ckdListBox.Items.RemoveAt(i);
                }
            }
        }

        private bool textFound(Value value, string searchedText)
        {
            bool retVal = false;
            string val = value.getValue() + "-" + value.getDescription();
            if (val.Contains(searchedText) || (searchedText == ""))
            {
                retVal = true;
            }
            return retVal;
        }

        private bool valueChecked(Value value, ListBox ckdListBox)
        {
            bool retVal = false;

            for (int i = 0; i < ckdListBox.Items.Count; i++)
            {
                string val = value.getValue() + "-" + value.getDescription();
                if ((val.Equals(ckdListBox.Items[i])) & ckdListBox.GetSelected(i))
                {
                    retVal = true;
                    break;
                }
            }
            return retVal;
        }


        #endregion

        private Button btnAddTags;
        private Button btnIgnore;
        //private CheckedListBox clbTagSelector;
        //private TextBox textBox1;
        public ListBox ckdListBox;
        public Label metadatoName;
        //public RichTextBox metadatoDescription;
        //public TextBox search;

    }
}