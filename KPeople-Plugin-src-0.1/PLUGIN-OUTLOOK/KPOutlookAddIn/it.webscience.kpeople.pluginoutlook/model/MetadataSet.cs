using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model
{
    public class MetadataSet
    {
        public List<CompoundMetadata> cmpMetadataList = new List<CompoundMetadata>();
        public List<RuleSet> ruleSetList = new List<RuleSet>();
        //public String xml = null;
        public MetadataSet() { 
        }

        public string convertToCompleteXml()
        {
            XmlDocument dom = new XmlDocument();
            MetadataSet metadataSetOriginal = this;

            XmlDeclaration dec = dom.CreateXmlDeclaration("1.0",null,null);
            XmlElement root = dom.CreateElement("MetaDataSet");

            dom.AppendChild(root);

            for (int i = 0; i < metadataSetOriginal.cmpMetadataList.Count; i++)
            {
                XmlElement cMetadata = dom.CreateElement("CompoundMetadata");
                cMetadata.SetAttribute("Key", metadataSetOriginal.cmpMetadataList[i].getKey());
                cMetadata.SetAttribute("Description", metadataSetOriginal.cmpMetadataList[i].getDescription());
                cMetadata = (XmlElement)root.AppendChild(cMetadata);

                for (int j = 0; j < metadataSetOriginal.cmpMetadataList[i].metadataList.Count; j++)
                {
                    XmlElement metadata = dom.CreateElement("Metadata");
                    metadata.SetAttribute("Key", metadataSetOriginal.cmpMetadataList[i].metadataList[j].getKey());
                    metadata.SetAttribute("Description", metadataSetOriginal.cmpMetadataList[i].metadataList[j].getDescription());
                    metadata = (XmlElement)cMetadata.AppendChild(metadata);

                    for (int k = 0; k < metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList.Count; k++)
                    {
                        XmlElement value = dom.CreateElement("Value");
                        value.SetAttribute("value", metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].getValue());
                        value.SetAttribute("description", metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].getDescription());
                        value.SetAttribute("state", metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].getIdState().ToString());
                        value = (XmlElement)metadata.AppendChild(value);

                        if (metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].listExtraData.Count != 0)
                        {
                            XmlElement extraData = dom.CreateElement("ExtraData");
                            extraData.SetAttribute("Key", metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].listExtraData[0].getKey());
                            extraData.SetAttribute("Value", metadataSetOriginal.cmpMetadataList[i].metadataList[j].valueList[k].listExtraData[0].getValue());
                            extraData = (XmlElement)value.AppendChild(extraData);
                        }
                    }
                }
            }

            return dom.OuterXml;
        }
    }
}
