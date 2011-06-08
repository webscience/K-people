using System;
using System.Runtime.InteropServices;
using System.Security.Permissions;
using Microsoft.SharePoint;
using Microsoft.SharePoint.Security;
using CustomProcessColumn.WebReference;
using CustomProcessColumn.MetadataService;
using System.Xml;
using System.Collections;
using System.Collections.Generic;
using System.Timers;
using System.Diagnostics;



namespace CustomProcessColumn.Features.Feature1
{
    /// <summary>
    /// Questa classe gestisce gli eventi generati durante l'attivazione, la disattivazione, l'installazione, la disinstallazione e l'aggiornamento della funzionalità.
    /// </summary>
    /// <remarks>
    /// Il GUID associato alla classe potrebbe essere utilizzato durante la creazione del pacchetto e non deve essere modificato.
    /// </remarks>

    [Guid("f86118da-3854-4e84-ba84-3e2e62c642c4")]
    public class Feature1EventReceiver : SPFeatureReceiver
    {
        
        public SPFieldChoice choiceField;
        public List<String> metadataValues = new List<string>();
        public string columnGroup;
        public string columnName;
        public string username;
        public long numberSeconds; 
        public SPFeatureReceiverProperties property;
        XmlDocument docXml = new XmlDocument();
        public bool firstTime = true;
        string sSource;
        string sLog;
        string sEvent;
        static Timer myTimer = null;


        
        
        
        
        //evento generato in seguito all'attivazione della feature medesima
        public override void FeatureActivated(SPFeatureReceiverProperties properties)
        {
           
            //Otteniamo tutte le properties dal file feature.xml
            columnGroup = properties.Feature.Properties["columnGroup"].Value;
            columnName = properties.Feature.Properties["columnName"].Value;
            username = properties.Feature.Properties["username"].Value;
            
            
            //Creazione di un'istanza della classe Timer
            if (myTimer == null)
            {
                numberSeconds = Convert.ToInt32(properties.Feature.Properties["numberSeconds"].Value);
                myTimer = new Timer();
                myTimer.Interval = numberSeconds;
                myTimer.Enabled = true;
				GC.KeepAlive(myTimer);
                property = properties;
                myTimer_Elapsed(null, null);

                //registrazione dell'evento myTimer_Elapsed che verrà richiamato
                //ogniqualvolta il timer scade
                myTimer.Elapsed += new ElapsedEventHandler(myTimer_Elapsed);
            }
        }

        //@param oggetto di tipo ProcessMetadataSet contenete l'elenco dei metadati 
        //scaricati dal servizio KPeople
        //@return oggetto di tipo List<String> contenente i valori dei metadati
        //Metodo che effettua il parser dell'oggetto ProcessMetadataSet ottenuto a seguito
        //dell'interrogazione del servizio KPeople
        private  List<String> parserXml(ProcessMetadataSet processMetadataSet)
        {
            string processState = null;
            List<String> metadataValues = new List<String>();
            ProcessCompoundMetadata[] elemList = processMetadataSet.cmpMetadataList;
            for (int i = 0; i < elemList.Length; i++)
            {
                ProcessMetadata[] elementListMetadata = elemList[i].metadataList;
                for (int j = 0; j < elementListMetadata.Length; j++)
                {
                    ProcessValue[] elementListValue = elementListMetadata[j].valueList;
                    for (int k = 0; k < elementListValue.Length; k++)
                    {
                        if (elementListValue[k].zidState == 1)
                        {
                            processState = "A";
                        }
                        else
                        {
                            processState = "C";
                        }
                        string metadataValue = elementListValue[k].value + "-" + elementListValue[k].description + "  " + "("+processState+")";
                        metadataValues.Add(metadataValue); 
                    }
                }
            }
            return metadataValues;
        }

        //evento che viene richiamato ogni qualvolta scade il timer
        //Effettua una chiamata al servizio KPeople. La prima volta che 
        //viene richiamato effettua un add della custom column. Tutte le volte 
        //successive effettua semplicemente un update dei valori della colonna.
        private void myTimer_Elapsed(object sender, ElapsedEventArgs e)    
        {   
            using (SPWeb spWeb = property.Feature.Parent as SPWeb)      
            {
                if (spWeb.Fields.ContainsField(columnName))
                {
                    updateExistColumn(property, spWeb);
                }
                else
                {
                    string choiceFieldName01 = spWeb.Fields.Add(columnName, SPFieldType.Choice, true);
                    choiceField = (SPFieldChoice)spWeb.Fields.GetFieldByInternalName(choiceFieldName01);
                    try
                    {
                        MetadataService.ProcessMetadataService metadataService = new MetadataService.ProcessMetadataService();
                        User user = null;
                        ProcessMetadataSet retrivedAvailableMetadataSetXml = metadataService.getMetadataProcessV1(user);
                        metadataValues = parserXml(retrivedAvailableMetadataSetXml);

                        for (int i = 0; i < metadataValues.Count; i++)
                        {
                            choiceField.Choices.Add(metadataValues[i]);

                        }
                        choiceField.Group = columnGroup;
                        choiceField.Update();
                        spWeb.Dispose();
                    
                    }
                    catch (System.Exception ex)
                    {
                        sSource = "Kpeople Sharepoint";
                        sLog = "Application";
                        sEvent = ex.Message;

                        if (!EventLog.SourceExists(sSource))
                            EventLog.CreateEventSource(sSource, sLog);

                        EventLog.WriteEntry(sSource, sEvent);
                        EventLog.WriteEntry(sSource, sEvent,
                            EventLogEntryType.Error, 234);

                    }
                    
                }           
            }           
        }

        //@param properties oggetto di tipo SPFeatureReceiverProperties
        //@param spWeb oggetto di tipo SPWeb
        //Effettua la connessione al servizio KPeople, scarica quindi i metadati 
        //ed effettua un update dei valori della custom column.
        private void updateExistColumn(SPFeatureReceiverProperties properties, SPWeb spWeb)
        {
           
            bool found = false;
            int indexMetadato = 0;
            int indexFieldColumn = 0;
            choiceField = (SPFieldChoice)spWeb.Fields.GetFieldByInternalName(columnName);
            try
            {
                MetadataService.ProcessMetadataService metadataService = new MetadataService.ProcessMetadataService();
                User user = null;
                ProcessMetadataSet retrivedAvailableMetadataSetXml = metadataService.getMetadataProcessV1(user);
                metadataValues = parserXml(retrivedAvailableMetadataSetXml);

                for (int i = 0; i < metadataValues.Count; i++)
                {
                    for (int j = 0; j < choiceField.Choices.Count; j++)
                    {
                        if (metadataValues[i] == choiceField.Choices[j])
                        {
                            found = true;
                            indexMetadato = i;
                            indexFieldColumn = j;
                        }
                    }
                    if (!found)
                    {
                        choiceField.Choices.Add(metadataValues[i]);
                    }
                    else
                    {
                        choiceField.Choices[indexFieldColumn] = metadataValues[indexMetadato];
                    }
                    found = false;
                }
                choiceField.Update();
                updateAllLists(spWeb, metadataValues);
            }
            catch (System.Exception ex)
            {

                sSource = "Kpeople Sharepoint";
                sLog = "Application";
                sEvent = ex.Message;

                if (!EventLog.SourceExists(sSource))
                    EventLog.CreateEventSource(sSource, sLog);

                EventLog.WriteEntry(sSource, sEvent);
                EventLog.WriteEntry(sSource, sEvent,
                    EventLogEntryType.Error, 234);

            }
        }



        //@param spWeb oggetto di tipo SPWeb
        //@parma metadataValues oggetto di tipo List<String>
        //Effettua un controllo su tutte le liste del sito per verificare quali 
        //tra esse hanno istanziato la custom column creata in precedenza andando
        //successivamente ad aggiornare in scrittura i campi della column.
        private void updateAllLists(SPWeb spWeb, List<String> metadataValues)
        {
            int indexMetadato = 0;
            int indexFieldColumn = 0;
            bool foundField = false;
            SPListCollection listCollection = spWeb.Lists;
            for (int i = 0; i < listCollection.Count; i++)
            {
                if (listCollection[i].Fields.ContainsField(columnName))
                {
                    SPFieldChoice spFieldChoice = (SPFieldChoice)listCollection[i].Fields.GetFieldByInternalName(columnName);
                    for (int j = 0; j < metadataValues.Count; j++)
                    {
                        for (int k = 0; k < spFieldChoice.Choices.Count; k++)
                        {
                            if (metadataValues[j] == spFieldChoice.Choices[k])
                            {
                                foundField = true;
                                indexMetadato = i;
                                indexFieldColumn = j;
                            }
                        }
                        if (!foundField)
                        {
                            spFieldChoice.Choices.Add(metadataValues[j]);
                        }
                        else
                        {
                            choiceField.Choices[indexFieldColumn] = metadataValues[indexMetadato];
                        }
                        foundField = false;
                    }
                    spFieldChoice.Update();
                    
                }
            }
        }


        // Rimuovere il commento dal metodo seguente per gestire l'evento generato prima della disattivazione di una funzionalità.
         /*public override void FeatureDeactivating(SPFeatureReceiverProperties properties)

         {

             using (SPWeb spWeb = properties.Feature.Parent as SPWeb)
             {
                 String column = "Process";
                 SPFieldChoice  choiceField = (SPFieldChoice)spWeb.Fields.GetFieldByInternalName(column);
                 for (int i = 0; i < choiceField.Choices.Count; i++)
                 {
                     choiceField.Choices.Remove(choiceField.Choices[i]);

                 }
                 choiceField.Update();
             }
           
         }*/


        // Rimuovere il commento dal metodo seguente per gestire l'evento generato dopo l'installazione di una funzionalità.

        //public override void FeatureInstalled(SPFeatureReceiverProperties properties)
        //{
        //}


        // Rimuovere il commento dal metodo seguente per gestire l'evento generato prima della disinstallazione di una funzionalità.

        //public override void FeatureUninstalling(SPFeatureReceiverProperties properties)
        //{
        //}

        // Rimuovere il commento dal metodo seguente per gestire l'evento generato al momento dell'aggiornamento di una funzionalità.

        //public override void FeatureUpgrading(SPFeatureReceiverProperties properties, string upgradeActionName, System.Collections.Generic.IDictionary<string, string> parameters)
        //{  
        //}

        
    }
}
