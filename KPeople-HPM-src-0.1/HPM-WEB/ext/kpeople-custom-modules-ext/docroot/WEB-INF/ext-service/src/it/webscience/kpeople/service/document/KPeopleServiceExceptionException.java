
/**
 * KPeopleServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

package it.webscience.kpeople.service.document;

public class KPeopleServiceExceptionException extends java.lang.Exception{
    
    private it.webscience.kpeople.service.document.DocumentServiceStub.KPeopleServiceExceptionE faultMessage;

    
        public KPeopleServiceExceptionException() {
            super("KPeopleServiceExceptionException");
        }

        public KPeopleServiceExceptionException(java.lang.String s) {
           super(s);
        }

        public KPeopleServiceExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public KPeopleServiceExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(it.webscience.kpeople.service.document.DocumentServiceStub.KPeopleServiceExceptionE msg){
       faultMessage = msg;
    }
    
    public it.webscience.kpeople.service.document.DocumentServiceStub.KPeopleServiceExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    