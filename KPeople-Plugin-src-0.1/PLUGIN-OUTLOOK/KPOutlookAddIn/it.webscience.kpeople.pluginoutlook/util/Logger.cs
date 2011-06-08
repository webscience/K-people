using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;
using System.Windows.Forms;
using System.IO;

using System.Configuration;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util
{
    class Logger
    {
        public void Info(string strEvent)
        {
            WriteEventToLog(strEvent, "INFO");
        }

        public void Warning(string strEvent)
        {
            WriteEventToLog(strEvent, "WARN");
        }

        public void Error(string strEvent)
        {
            WriteEventToLog(strEvent,"ERROR");
        }

        public void Fail(string strEvent)
        {
            WriteEventToLog(strEvent, "FAIL");
        }

        public void WriteEventToLog(string strEvent, string type)
        {
            StreamWriter writer = null;
            try
            {

                //MessageBox.Show("Da file di conf[timeoutInMinutes]" + ConfigurationManager.AppSettings["timeoutInMinutes"]);

                string logFile = Constants.KP_LOG_FULLNAME;

                if (!File.Exists(logFile))
                {
                    writer = File.CreateText(logFile);
                }
                else
                {
                    writer = File.AppendText(logFile);
                }

                writer.WriteLine(DateTime.Now.ToString() + " - " + type + " - " + strEvent);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(), Constants.KP_CAPTION_ERROR);
            }
            finally
            {
                writer.Close();
            }
        }

        public void Start()
        {
            WriteEventToLog("++++++++++++++++++++++++", "START");
            WriteEventToLog("Kpeople Plugin starting!", "START");
        }

        internal void Stop()
        {
            WriteEventToLog("Kpeople Plugin Stopped!", "STOP");
            WriteEventToLog("+++++++++++++++++++++++", "STOP");
        }
    }
}


    
