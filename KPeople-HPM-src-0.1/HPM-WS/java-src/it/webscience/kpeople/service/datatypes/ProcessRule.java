package it.webscience.kpeople.service.datatypes;

/**
 * @author depascalis
 * Classe relativa alle rule degli utenti
 */
public class ProcessRule {
        
        /** Chiave identificativa della rule */
        private String key;
        
        /** Valore della rule */
        private String value;
        
        /** Costruttore */
        public ProcessRule()
        {
                
        }
        
        /**
         * @param in la key da settare
         */
        public void setKey (String in)
        {
                this.key = in;
        }
        
        /**
         * @param in il value da settare
         */
        public void setValue(String in)
        {
                this.value = in;
        }
        
        /**
         * @return la key che identifica la rule
         */
        public String getKey()
        {
                return key;
        }
        
        /**
         * @return value
         */
        public String getValue()
        {
                return value;
        }

}
