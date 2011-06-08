using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.plugincore;
using KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.util;

namespace KPOutlookAddIn.it.webscience.kpeople.pluginoutlook.model
{   
    public class Value
    {
        private String value;
        private String description;
        private PROCESS_STATE state;
        private int idState;
        private String stateString;
        public List<SimpleExtraDataManager> listExtraData = new List<SimpleExtraDataManager>();

        public enum PROCESS_STATE { Open = 1, Close = 2 };

        public Value() { }

        public void setValue(String value)
        {
            this.value = value;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public String getValue()
        {
            return this.value;
        }

        public String getDescription()
        {
            return this.description;
        }

        public String getStateString()
        {
            return this.stateString;
        }

        public PROCESS_STATE getState()
        {
            return state;
        }

        internal void setState(int p)
        {
            idState = p;
            this.state = (PROCESS_STATE)p;
            if (state == PROCESS_STATE.Open)
                stateString = Constants.KP_PROCESS_STATE_OPEN;
            else
                stateString = Constants.KP_PROCESS_STATE_CLOSE;
        }

        internal int getIdState()
        {
            return idState;
        }
    }
}
