using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model
{
    public class Rule
    {
        private String key;
        private String value;

        public Rule() 
        {

        }

        public void setkey(String key)
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
