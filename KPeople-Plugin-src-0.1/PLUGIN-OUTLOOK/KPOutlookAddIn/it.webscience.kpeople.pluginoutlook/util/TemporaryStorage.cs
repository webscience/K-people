using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Microsoft.Office.Interop.Outlook;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util
{

    class TemporaryStorage
    {
        private String workFolder;
        private Logger log;
        private String fileFolder = null;
        private String fullFileName = null;

        public TemporaryStorage()
        {
            workFolder = Constants.getWorkFolder();
            log = new Logger();
        }

        public string GetFullFileName()
        {
            return fullFileName;
        }

        public void DeleteFile()
        {
            string pos = "TemporaryStorage.DeleteFile - ";
            log.Info(pos + "INIT");

            try
            {
                File.Delete(fullFileName);
            }
            catch (System.Exception)
            {
                log.Warning(pos + "END");
            }

            log.Info(pos + "END");
        }

        public void DeleteFolder()
        {
            string pos = "TemporaryStorage.DeleteFolder - ";
            log.Info(pos + "INIT");

            try
            {
                File.Delete(fullFileName);
                Directory.Delete(fileFolder);
            }
            catch (System.Exception ex)
            {
                log.Warning(pos + "Exception:" + ex.Message);
            }

            log.Info(pos + "END");
        }


        public string Save(Attachment attach)
        {
            string filename = System.Guid.NewGuid().ToString();

            return Save(filename, attach);
        }

        public string Save(string xml)
        {
            string filename = System.Guid.NewGuid().ToString();

            return Save(filename, xml);
        }

        public string Save(string filename, string xml)
        {
            string pos = "TemporaryStorage.Save - ";

            log.Info(pos + "INIT");

            System.Guid guid = System.Guid.NewGuid();

            fileFolder = Path.Combine(Constants.getWorkFolder(), guid.ToString());
            fullFileName = Path.Combine(fileFolder, filename);

            log.Info(pos + "Creo la dir di appoggio:" + fileFolder);
            Directory.CreateDirectory(fileFolder);

            log.Info(pos + "Salvo il file:" + fullFileName);

            File.WriteAllText(fullFileName, xml);

            log.Info(pos + "END");

            return fullFileName;
        }



        public string Save(string filename, Attachment attach)
        {
            string pos = "TemporaryStorage.Save - ";

            log.Info(pos + "INIT");

            System.Guid guid = System.Guid.NewGuid();

            fileFolder = Path.Combine(Constants.getWorkFolder(), guid.ToString());
            fullFileName = Path.Combine(fileFolder, filename);

            log.Info(pos + "Creo la dir di appoggio:" + fileFolder);
            Directory.CreateDirectory(fileFolder);

            log.Info(pos + "Salvo il file:" + fullFileName);

            attach.SaveAsFile(fullFileName);

            log.Info(pos + "END");

            return fullFileName;
        }



    }


}
