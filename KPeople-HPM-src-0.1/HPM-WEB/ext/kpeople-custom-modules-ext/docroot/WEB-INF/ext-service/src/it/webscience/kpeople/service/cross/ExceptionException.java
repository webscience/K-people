
/**
 * ExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

package it.webscience.kpeople.service.cross;

public class ExceptionException extends java.lang.Exception{
    
    private it.webscience.kpeople.service.cross.MailServiceStub.ExceptionE faultMessage;

    
        public ExceptionException() {
            super("ExceptionException");
        }

        public ExceptionException(java.lang.String s) {
           super(s);
        }

        public ExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(it.webscience.kpeople.service.cross.MailServiceStub.ExceptionE msg){
       faultMessage = msg;
    }
    
    public it.webscience.kpeople.service.cross.MailServiceStub.ExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    