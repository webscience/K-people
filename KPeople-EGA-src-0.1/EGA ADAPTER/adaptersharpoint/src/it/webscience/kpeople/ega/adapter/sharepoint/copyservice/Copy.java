

/**
 * Copy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:01 CEST)
 */

    package it.webscience.kpeople.ega.adapter.sharepoint.copyservice;

    /*
     *  Copy java interface
     */

    public interface Copy {
          

        /**
          * Auto generated method signature
          * 
                    * @param copyIntoItemsLocal
                
         */

         
                     public com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse copyIntoItemsLocal(

                        com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal copyIntoItemsLocal)
                        throws java.rmi.RemoteException
             ;

        

        /**
          * Auto generated method signature
          * 
                    * @param getItem
                
         */

         
                     public com.microsoft.schemas.sharepoint.soap.GetItemResponse getItem(

                        com.microsoft.schemas.sharepoint.soap.GetItem getItem)
                        throws java.rmi.RemoteException
             ;

        

        /**
          * Auto generated method signature
          * 
                    * @param copyIntoItems
                
         */

         
                     public com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse copyIntoItems(

                        com.microsoft.schemas.sharepoint.soap.CopyIntoItems copyIntoItems)
                        throws java.rmi.RemoteException
             ;

        

        
       //
       }
    