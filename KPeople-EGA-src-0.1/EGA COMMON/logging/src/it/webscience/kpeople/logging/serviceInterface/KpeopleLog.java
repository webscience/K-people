package it.webscience.kpeople.logging.serviceInterface;


public interface KpeopleLog {
	
	public int logInfo(final String name);
	public int logInfo(final String component, final String name);
	
	public int logWarn(final String name);
	public int logWarn(final String component, final String name);
	
	public int logError(final String name);
	public int logError(final String component, final String name);
	
	public int logFatal(final String name);
        public int logFatal(final String component, final String name);
        
        public int logDebug(final String name);
        public int logDebug(final String component, final String name);
	
}
