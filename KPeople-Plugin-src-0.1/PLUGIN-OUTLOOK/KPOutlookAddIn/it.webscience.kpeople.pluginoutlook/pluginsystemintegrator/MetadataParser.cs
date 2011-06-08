using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using KPOutlookAddIn.ProcessMetadataService;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.pluginsystemintegrator
{
    class MetadataParser
    {
        Logger log = null;

        XmlDocument docXml = new XmlDocument();
        public MetadataParser() 
        {
            log = new Logger();
        }

        public MetadataSet metadataSetXml(ProcessMetadataSet metadataXml)
        {
            
            return parser(metadataXml);
        }

       /* public MetadataSet metadataSetFromFile()
        {
            MetadataSet metadataSet = new MetadataSet();
            docXml.Load("C:/Documents and Settings/depascalis/Impostazioni locali/Dati applicazioni/IsolatedStorage/metadataSet.xml");
            return parser(docXml, metadataSet);
        }*/
        
        public MetadataSet parser(ProcessMetadataSet mtdSet) {

            MetadataSet metadataSet = new MetadataSet();
            try
            {
                
               // XmlNodeList elemList = doc.GetElementsByTagName("CompoundMetadata");
                ProcessCompoundMetadata[] elemList = mtdSet.cmpMetadataList;
                for (int i = 0; i < elemList.Length; i++)
                {
                    CompoundMetadata cmpMetadata = new CompoundMetadata();
                    cmpMetadata.setKey(elemList[i].key);
                    cmpMetadata.setDescription(elemList[i].description);
                   // metadataSet.cmpMetadataList.Add(cmpMetadata);
                    ProcessMetadata[] elementListMetadata = elemList[i].metadataList;
                    //XmlNodeList elementListMetadata = elemList[i].ChildNodes;
                    for (int j = 0; j < elementListMetadata.Length; j++)
                    {
                        Metadata metadata = new Metadata();
                        metadata.setKey(elementListMetadata[j].key);
                        metadata.setDescription(elementListMetadata[j].description);
                        //cmpMetadata.metadataList.Add(metadata);
                        ProcessValue[] elementListValue = elementListMetadata[j].valueList;
                        for (int k = 0; k < elementListValue.Length; k++)
                        {
                            Value value = new Value();
                            value.setValue(elementListValue[k].value);
                            value.setDescription(elementListValue[k].description);
                            value.setState(elementListValue[k].zidState);
                            ProcessExtraData[] elementExtraData = elementListValue[k].listProcessExtraData;
                            if (elementExtraData != null)
                            {
                                SimpleExtraDataManager simpleExtraData = new SimpleExtraDataManager();
                                simpleExtraData.setKey(elementExtraData[0].key);
                                simpleExtraData.setValue(elementExtraData[0].value);
                                value.listExtraData.Add(simpleExtraData);
                            }
                            metadata.valueList.Add(value);

                            
                        }
                       cmpMetadata.metadataList.Add(metadata);
                    }
                    metadataSet.cmpMetadataList.Add(cmpMetadata);
                }
                /*XmlNodeList elemRuleSet = doc.GetElementsByTagName("RuleSet");
                RuleSet ruleSet = new RuleSet();
                for (int i = 0; i < elemRuleSet.Count; i++)
                {
                    XmlNodeList elemRule = elemRuleSet[i].ChildNodes;
                    for (int j = 0; j < elemRule.Count; j++)
                    {
                        Rule rule = new Rule();
                        rule.setkey(elemRule[j].Attributes["Key"].Value);
                        rule.setValue(elemRule[j].Attributes["Value"].Value);
                        ruleSet.ruleList.Add(rule);
                    }
                    mtdSet.ruleSetList.Add(ruleSet);
                }*/

                return metadataSet;
            }
            catch (System.Exception ex)
            {
                XmlException myEx = new XmlException("Errore nell'interpretazione del messaggio");
                throw myEx;
            }

            
                
            
        }

        public MetadataSet parserMetadataSetFromXML(XmlDocument doc)
        {
            string pos = "MetadataParser.parserMetadataSetFromFileSystem";
           
            log.Info(pos + "INIT");

            MetadataSet mtdSet = null;

            try
            {
                mtdSet = new MetadataSet();
                
                XmlNodeList elemList = doc.GetElementsByTagName("CompoundMetadata");
                for (int i = 0; i < elemList.Count; i++)
                {
                    CompoundMetadata cmpMetadata = new CompoundMetadata();
                    cmpMetadata.setKey(elemList[i].Attributes["Key"].Value);
                    cmpMetadata.setDescription(elemList[i].Attributes["Description"].Value);
                   // metadataSet.cmpMetadataList.Add(cmpMetadata);
                    XmlNodeList elementListMetadata = elemList[i].ChildNodes;
                    for (int j = 0; j < elementListMetadata.Count; j++)
                    {
                        Metadata metadata = new Metadata();
                        metadata.setKey(elementListMetadata[j].Attributes["Key"].Value);
                        metadata.setDescription(elementListMetadata[j].Attributes["Description"].Value);
                        //cmpMetadata.metadataList.Add(metadata);
                        XmlNodeList elementListValue = elementListMetadata[j].ChildNodes;
                        for (int k = 0; k < elementListValue.Count; k++)
                        {
                            Value value = new Value();
                            value.setValue(elementListValue[k].Attributes["value"].Value);
                            value.setState(Convert.ToInt32(elementListValue[k].Attributes["state"].Value));
                            value.setDescription(elementListValue[k].Attributes["description"].Value);
                            XmlNodeList elementExtraData = elementListValue[k].ChildNodes;
                            if (elementExtraData.Count != 0)
                            {
                                SimpleExtraDataManager simpleExtraData = new SimpleExtraDataManager();
                                simpleExtraData.setKey(elementExtraData[0].Attributes["Key"].Value);
                                simpleExtraData.setValue(elementExtraData[0].Attributes["Value"].Value);
                                value.listExtraData.Add(simpleExtraData);
                            }
                            metadata.valueList.Add(value);
                            
                        }
                       cmpMetadata.metadataList.Add(metadata);
                    }
                    mtdSet.cmpMetadataList.Add(cmpMetadata);
                }
                XmlNodeList elemRuleSet = doc.GetElementsByTagName("RuleSet");
                RuleSet ruleSet = new RuleSet();
                for (int i = 0; i < elemRuleSet.Count; i++)
                {
                    XmlNodeList elemRule = elemRuleSet[i].ChildNodes;
                    for (int j = 0; j < elemRule.Count; j++)
                    {
                        Rule rule = new Rule();
                        rule.setkey(elemRule[j].Attributes["Key"].Value);
                        rule.setValue(elemRule[j].Attributes["Value"].Value);
                        ruleSet.ruleList.Add(rule);
                    }
                    mtdSet.ruleSetList.Add(ruleSet);
                }

               
            }
            catch (System.Exception exep)
            {
                log.Error(pos + "Exception:" + exep.Message);
                log.Error(pos + "Exception:" + exep.StackTrace);
                XmlException myEx = new XmlException("Errore nell'interpretazione del messaggio:" + exep.Message);
                throw myEx;
            }

            log.Info(pos + "END");

            return mtdSet;
        }

        
    }

}
