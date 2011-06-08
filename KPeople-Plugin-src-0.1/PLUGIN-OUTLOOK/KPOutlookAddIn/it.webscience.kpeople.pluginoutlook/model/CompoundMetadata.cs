using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model
{
    public class CompoundMetadata
    {
        private String key;
        private String description;
        public List<Metadata> metadataList = new List<Metadata>();
        
        public void setKey(String key){
            this.key = key;
        }

        public void setDescription(String description){
            this.description = description;  
        }

        public String getKey(){
            return this.key;
        }

        public String getDescription(){
            return this.description;
        }

       // public void addMetadata(Metadata metadata) { 
        
        //}
    }
}
