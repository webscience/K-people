using System.Collections.Generic;
using System.Linq;
using System.Text;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.pluginsystemintegrator;
using System.IO.IsolatedStorage;
using System.Configuration;
using System.Xml;
using System.IO;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;


namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore
{
    class Cachemanager
    {

        MetadataSet metadataSet;
        ConnectionManager connManager;
        MetadataParser metadataParser;
        private static Cachemanager instance;
        public System.DateTime lastWriteTime;
        XmlDocument docXml = new XmlDocument();

        private Logger log = null;

        /**
         * Costruttore della classe CacheManager.
         * Al suo interno crea un'istanzia della classe ConnectionManager
         */
        protected Cachemanager()
        {
            connManager = new ConnectionManager();
            log = new Logger();
        }

        /**
         * Restituisce un'istanza della classe CacheManager
         */
        public static Cachemanager instanceCachemanager()
        {
            if (instance == null)
            {
                instance = new Cachemanager();
            }
            return instance;
        }

        /**
         * @return un oggetto di tipo MetadataSet contenente le
         * informazioni strutturate relative all'insieme dei metadati
         */
        public MetadataSet getAvailableMetadataSet()
        {
            const string pos = "Cachemanager.getAvailableMetadataSet - ";
            log.Info(pos + "INIT");    
            try
            {
                /**
                 * Si effettua preliminarmente un controllo sulla validità dei
                 * metadati salvati su disco. Nel caso risultino non validi, 
                 * si effettua una chiamata al servizio KPeople per scaricare il nuovo elenco
                 * dei metadati. Successivamente si effettua un update dell'elenco appena scaricato
                 * con i metadati salvati sul file System. Viceversa si va a leggere l'elenco dei
                 * metadati salvati su disco.
                 */
                if (!checkCachedMetadataValidity())
                {
                        metadataSet = connManager.retriveAvailableMetadataSetXml(Constants.KP_USERNAME);
                        metadataSet = updateAvailableMetadataSet(metadataSet);
                        SaveToDisk(metadataSet);
                }
                else
                {
                    metadataSet = getMetadataSetFromFileSystem();
                }
            }
            catch (System.Exception e)
            {
                throw e;
            }
            log.Info(pos + "END");    
            return metadataSet;
        }

        /** 
         * @param metadataSet rappresenta un oggetto di tipo MetadataSet.Ossia l'elenco
         * dei metadati appena scaricati dal servizio KPeople.
         * @return un oggetto di tipo MetadataSet contenente le informazioni
         * strutturate relative all'insieme dei metadati aggiornato.
         * Il metodo in questione effettua l'update dei metadati scaricati dal servizio e 
         * ricevuti come parametro di input con quelli salvati sul file System.
         */
        public MetadataSet updateAvailableMetadataSet(MetadataSet metadataSet)
        {
            
            MetadataSet metadataSetFromDisk = getMetadataSetFromFileSystem();
            if (metadataSetFromDisk != null)
            {
                for (int i = 0; i < metadataSet.cmpMetadataList.Count; i++)
                {
                    for (int j = 0; j < metadataSet.cmpMetadataList[i].metadataList.Count; j++)
                    {
                        string metadataKey = metadataSet.cmpMetadataList[i].metadataList[j].getKey();
                        for (int a = 0; a < metadataSetFromDisk.cmpMetadataList.Count; a++)
                        {
                            for (int b = 0; b < metadataSetFromDisk.cmpMetadataList[a].metadataList.Count; b++)
                            {
                                if (metadataSetFromDisk.cmpMetadataList[a].metadataList[b].getKey().Equals(metadataKey))
                                {
                                    compareValue(metadataSet, metadataSetFromDisk, i, j, a, b);
                                }
                            }
                        }

                    }
                }
                return metadataSet;
            }
            else 
            {
                return metadataSet;
            }
        }

        /** 
         * @param metadataSet rappresenta un oggetto di tipo MetadataSet.Ossia l'elenco
         * dei metadati appena scaricati dal servizio KPeople.
         * @param metadataSetFromDisk un oggetto di tipo MetadataSet contenente le informazioni
         * strutturate relative all'insieme dei metadati salvati sul File System.
         * Il metodo in questione verifica preliminarmente la presenza di matching tra i metadati del servizio 
         * KPeople e quelli sul File System. Nel caso di match, preleva l'ExtraData associato a ciascun metadato 
         * appartenente all'elenco dei metadati salvati su disco e lo aggiunge al corrispondente metadato 
         * appartenente all'elenco dei metadati scaricati dal servizio
         */
        private void compareValue(MetadataSet metadataset, MetadataSet metadataSetFromDisk, int iMetadataset, int jMetadataset, int aMetadataSetFromDisk, int bMetadataSetFromDisk)
        {
           
            Value value = new Value();
            for (int i = 0; i < metadataset.cmpMetadataList[iMetadataset].metadataList[jMetadataset].valueList.Count; i++)
            {
                string valueKey = metadataset.cmpMetadataList[iMetadataset].metadataList[jMetadataset].valueList[i].getValue();
                for (int j = 0; j < metadataSetFromDisk.cmpMetadataList[aMetadataSetFromDisk].metadataList[bMetadataSetFromDisk].valueList.Count; j++)
                {
                    if (metadataSetFromDisk.cmpMetadataList[aMetadataSetFromDisk].metadataList[bMetadataSetFromDisk].valueList[j].getValue().Equals(valueKey))
                    {
                        if (metadataSetFromDisk.cmpMetadataList[aMetadataSetFromDisk].metadataList[bMetadataSetFromDisk].valueList[j].listExtraData.Count != 0)
                        {
                            metadataset.cmpMetadataList[iMetadataset].metadataList[jMetadataset].valueList[i].listExtraData.Add(
                                metadataSetFromDisk.cmpMetadataList[aMetadataSetFromDisk].metadataList[bMetadataSetFromDisk].valueList[j].listExtraData[0]);
                        }
                    }
                }
            }
        }

        /** 
         * @return un oggetto di tipo MetadataSet contenente le informazioni
         * strutturate relative all'insieme dei metadati salvati sul File System.
         * Il metodo, carica il file dal File System, se presente, contenente l'elenco
         * dei metadati. Dopo di che passa il file alla classe MetadataParser che
         * ne effettua il parser.
         */
        public MetadataSet getMetadataSetFromFileSystem()
        {

            metadataSet = null;

            if (File.Exists(Constants.getMetadatasetFullName()))
            {
                docXml.Load(Constants.getMetadatasetFullName());
                metadataParser = new MetadataParser();
                metadataSet = metadataParser.parserMetadataSetFromXML(docXml);
            }

            return metadataSet;
        }


        /** 
         * @param metadataSet un oggetto di tipo MetadataSet contenente le informazioni
         * strutturate relative all'insieme dei metadati salvati sul File System.
         * @param metadataSetOriginal un oggetto di tipo MetadataSet contenente le informazioni
         * strutturate relative all'insieme dei metadati scaricati dal servizio KPeople
         */

        public void updateExtraData(MetadataSet metadataSet, MetadataSet metadataSetOriginal)
        {
            ExtraDataManagerFactory factory = new ExtraDataManagerFactory();
            IExtraDataManager iExtraDataManager = factory.createExtraDataManager(ExtraDataManagerType.SimpleExtraDataManager);
            metadataSet = iExtraDataManager.updateExtraData(metadataSet,metadataSetOriginal);
            SaveToDisk(metadataSet);
        }

        private void SaveToDisk(MetadataSet metadataSet)
        {
            string xml = metadataSet.convertToCompleteXml();
            System.IO.File.WriteAllText(Constants.getMetadatasetFullName(), xml);
        }
        

        /** 
         * @return un oggetto di tipo bool 
         * Il metodo in questione verifica la validità del file contenente i metadatai
         * salvato sul File System.
         * 
         */
        private bool checkCachedMetadataValidity()
        {
            const string pos = "Cachemanager.checkCachedMetadataValidity - ";
            log.Info(pos + "INIT");   

            bool validity = true;
            lastWriteTime = File.GetLastWriteTime(Constants.getMetadatasetFullName());

            log.Info(pos + "lastWriteTime:" + lastWriteTime);

            System.DateTime dateValidity = lastWriteTime.AddMinutes(Constants.KP_METADATASET_TIMEOUT);
            System.DateTime currDate = System.DateTime.Now;

            log.Info(pos + "Validity time:" + dateValidity);
            log.Info(pos + "Current time:" + currDate);

            if (dateValidity.CompareTo(currDate) < 0)
            {
                validity = false;
            }

            log.Info(pos + "Validity :" + validity);

            return validity;
        }
    }
}
