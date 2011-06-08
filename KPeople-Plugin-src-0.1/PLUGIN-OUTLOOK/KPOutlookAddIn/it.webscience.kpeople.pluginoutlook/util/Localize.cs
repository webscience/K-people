using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Resources;

using System.Resources;
using System.Reflection;
using System.Threading;
using System.Globalization;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util
{
    class Localize
    {
        private static ResourceManager rm = null;
        private static CultureInfo currCulture = null;

        public static ResourceManager GetInstance()
        {
            if (rm == null)
            {
                rm = new ResourceManager("KPOutlookAddIn.Resources.KPLanguage", Assembly.GetExecutingAssembly());
                currCulture = CultureInfo.CurrentCulture;
            }

            return rm;
        }

        public static String LocalizeString(String index)
        {
            ResourceManager rmc = GetInstance();
            return rmc.GetString(index, currCulture);
        }
    }
}
