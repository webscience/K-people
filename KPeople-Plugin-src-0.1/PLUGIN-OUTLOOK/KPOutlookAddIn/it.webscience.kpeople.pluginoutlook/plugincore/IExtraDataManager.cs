using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore
{
    interface IExtraDataManager
    {
        MetadataSet updateExtraData(MetadataSet metadataSet, MetadataSet metadataSetOriginal);
    }
}
