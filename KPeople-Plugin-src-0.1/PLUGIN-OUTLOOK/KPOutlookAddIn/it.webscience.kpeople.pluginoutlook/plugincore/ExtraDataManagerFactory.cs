using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore
{
    class ExtraDataManagerFactory
    {
        private static ExtraDataManagerFactory _instance = new ExtraDataManagerFactory();

        public static ExtraDataManagerFactory instance
        {
            get { return _instance; }
        }

        public IExtraDataManager createExtraDataManager(ExtraDataManagerType type)
        {
            switch (type)
            {
                case ExtraDataManagerType.SimpleExtraDataManager:
                    return new SimpleExtraDataManager();
                default:
                    throw new ArgumentException("type");

            }
        }

    }
}
