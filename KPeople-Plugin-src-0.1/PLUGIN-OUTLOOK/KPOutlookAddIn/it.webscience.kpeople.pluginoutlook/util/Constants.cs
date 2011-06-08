using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Outlook = Microsoft.Office.Interop.Outlook;
using Microsoft.Win32;
using System.Windows.Forms;
using System.IO;
using System.Configuration;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util
{
    class Constants
    {
        public const string KP_CATEGORY_NAME = "Kpeople";
        public const string KP_FOLDER = "Kpeople";
        public const string KP_LOG_FILENAME = "Kpeople.log";
        public static string KP_CAPTION_ERROR = Localize.LocalizeString("errorCaption"); 
        
        public const string KP_RESOURCE_FILENAME = "KPLanguage";
        
        public static string KP_DOMAIN = ConfigurationManager.AppSettings["kpDomain"];
        public static string KP_USERNAME = ConfigurationManager.AppSettings["username"];
        public static int KP_METADATA_SERVICE_RETRY_COUNT = 3;
        public static string KP_METADATA_SERVICE_CONFIGURATION = "ProcessMetadataServiceHttpSoap11Endpoint";

        public static string KP_PROCESS_STATE_OPEN = Localize.LocalizeString("opened"); 
        public static string KP_PROCESS_STATE_CLOSE = Localize.LocalizeString("closed");

        public static string KP_ATTACHMENT_DISPLAY_NAME = ConfigurationManager.AppSettings["sDisplayName"];
        public static string KP_ATTACHMENT_NAME = ConfigurationManager.AppSettings["attachmentName"];
        public static string KP_METADATASET_NAME = ConfigurationManager.AppSettings["fileName"];
        public static int KP_METADATASET_TIMEOUT = Int32.Parse(ConfigurationManager.AppSettings["timeoutInMinutes"]);
        public static string KP_METADATASET_EXTENSION = ConfigurationManager.AppSettings["extension"];
        public static string KP_ATTACHMENT_EXTENSION = ConfigurationManager.AppSettings["extension"];
        public static string KP_METADATASET_TAGSELECTOR_LABEL = Localize.LocalizeString("searchProcess"); 

        public static string KP_MAIL_BCC = ConfigurationManager.AppSettings["mailCCN"];

        public static string KP_SIMPLE_ESTRADATA_MANAGER_KEY = "1";
        public static string KP_SIMPLE_ESTRADATA_MANAGER_VALUE = "1";

        public static string KP_LOG_FULLNAME = getWorkFolder() + @"\" + KP_LOG_FILENAME;

        public const Outlook.OlCategoryColor KP_COLOR = Outlook.OlCategoryColor.olCategoryColorPurple;
        public const Outlook.OlCategoryShortcutKey KP_CATEGORY_SHORTCUT = Outlook.OlCategoryShortcutKey.olCategoryShortcutKeyNone;
        public static string KP_TAG_SELECTOR_SEARCH_DEFAULT_TEXT = Localize.LocalizeString("search");
        public static string KP_TAG_BUTTON_INSERT_TEXT = Localize.LocalizeString("insert");
        public static string KP_TAG_BUTTON_CANCEL_TEXT = Localize.LocalizeString("cancel");

        public static string getWorkFolder()
        {
            string retVal = "" + Environment.GetFolderPath(Environment.SpecialFolder.Personal) + @"\" + KP_FOLDER;

            if (!Directory.Exists(retVal))
            {
                Directory.CreateDirectory(retVal);
            }
            
            return retVal;
        }

        public static string getMetadatasetFullName()
        {
            return getWorkFolder() + "\\" + KP_METADATASET_NAME + KP_METADATASET_EXTENSION;

        }
    }
}
