using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Windows.Forms;
using KPOutlookAddIn.ProcessMetadataService;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.pluginsystemintegrator
{
    class ConnectionManager
    {
        private Logger log = null;
       /** 
        * Costruttore della classe ConnectionManager
        */
        public ConnectionManager(){
            log = new Logger();
        }

       /** 
        * @param parametro di tipo stringa contenente lo username dell'utente che 
        * richiede i metadati al servizio KPeople
        * @return oggetto di tipo MetadataSet contenete le informazioni strutturate
        * circa i metadati
        */
        public MetadataSet retriveAvailableMetadataSetXml(string username)
        {
            string pos = "ConnectionManager.retriveAvailableMetadataSetXml - ";
            log.Info(pos + "INIT");
            MetadataSet metadataSet= null;
            int counter = 0;
            while (counter <= Constants.KP_METADATA_SERVICE_RETRY_COUNT)
            {
                try
                {

                    ProcessMetadataService.ProcessMetadataServicePortTypeClient req = new ProcessMetadataServicePortTypeClient(Constants.KP_METADATA_SERVICE_CONFIGURATION);

                    log.Info(pos + "Endpoint:" + req.Endpoint.Address);


                    User user = new User();
                    user.hpmUserId = username;

                    req.Open();


                    ProcessMetadataSet retrivedAvailableMetadataSetXml = req.getMetadataProcessV1(user);

                    req.Close();

                    MetadataParser parser = new MetadataParser();
                    metadataSet = parser.metadataSetXml(retrivedAvailableMetadataSetXml);

                    counter = Constants.KP_METADATA_SERVICE_RETRY_COUNT;
                }
                catch (Exception ex)
                {
                    log.Error(pos + "Exception:" + ex.Message);

                    if (counter == Constants.KP_METADATA_SERVICE_RETRY_COUNT)
                    {
                        throw new Exception(Localize.LocalizeString("networkError"));
                    }
                }
                finally
                {
                    counter++;
                }

            }
               
            log.Info(pos + "END");
            return metadataSet;
        }
    }
}
