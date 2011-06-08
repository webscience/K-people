using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore
{
    public class SimpleExtraDataManager : IExtraDataManager
    {
        private String key;
        private String value;
        


        public MetadataSet updateExtraData(MetadataSet metadataSet, MetadataSet metadataSetOriginal)
        {
            for (int i = 0; i < metadataSet.cmpMetadataList.Count; i++)
            {
                string compoundKey = metadataSet.cmpMetadataList[i].getKey();
                for (int j = 0; j < metadataSet.cmpMetadataList[i].metadataList.Count; j++)
                {
                    string metadataKey = metadataSet.cmpMetadataList[i].metadataList[j].getKey();
                    for (int k = 0; k < metadataSet.cmpMetadataList[i].metadataList[j].valueList.Count; k++)
                    {
                        string valueKey = metadataSet.cmpMetadataList[i].metadataList[j].valueList[k].getValue();
                        metadataSetOriginal = updateMetadatasetOriginal(metadataSetOriginal, compoundKey, metadataKey, valueKey);
                    }
                }
            }
            return metadataSetOriginal;
        }

        private MetadataSet updateMetadatasetOriginal(MetadataSet metadataSetOriginal, string compoundKey, string metadataKey, string valueKey)
        {
            for (int a = 0; a < metadataSetOriginal.cmpMetadataList.Count; a++)
                        {
                            if ((metadataSetOriginal.cmpMetadataList[a].getKey()).Equals(compoundKey))
                            {
                                for (int b = 0; b < metadataSetOriginal.cmpMetadataList[a].metadataList.Count; b++)
                                {
                                    if ((metadataSetOriginal.cmpMetadataList[a].metadataList[b].getKey()).Equals(metadataKey))
                                    {
                                        for (int c = 0; c < metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList.Count; c++)
                                        {
                                            if ((metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList[c].getValue()).Equals(valueKey))
                                            {
                                                if (metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList[c].listExtraData.Count == 0)
                                                {
                                                    this.setKey(Constants.KP_SIMPLE_ESTRADATA_MANAGER_KEY);
                                                    this.setValue(Constants.KP_SIMPLE_ESTRADATA_MANAGER_VALUE);
                                                    metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList[c].listExtraData.Add(this);
                                                }
                                                else
                                                {
                                                    string count = (Convert.ToInt16(metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList[c].listExtraData[0].getValue()) + 1).ToString();
                                                    metadataSetOriginal.cmpMetadataList[a].metadataList[b].valueList[c].listExtraData[0].setValue(count);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
            return metadataSetOriginal;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getKey()
        {
            return this.key;
        }

        public String getValue()
        {
            return this.value;
        }
    }
}
