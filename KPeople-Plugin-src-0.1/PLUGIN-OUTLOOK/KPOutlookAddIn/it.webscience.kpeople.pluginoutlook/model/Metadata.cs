using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model
{
   public class Metadata
    {   
        private String key;
        private String description;
        public List<Value> valueList = new List<Value>();
        public Metadata(){ 
        }
        public void setKey (String value)
        {
            this.key = value;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public String getKey()
        {
            return this.key;
        }

        public String getDescription()
        {
            return this.description;
        }
    }
}
