
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:47 CEST)
 */

            package com.microsoft.schemas.sharepoint.soap;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://microsoft.com/wsdl/types/".equals(namespaceURI) &&
                  "guid".equals(typeName)){
                   
                            return  com.microsoft.wsdl.types.Guid.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "FieldInformationCollection".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.FieldInformationCollection.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "DestinationUrlCollection".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.DestinationUrlCollection.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "FieldInformation".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.FieldInformation.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "CopyErrorCode".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.CopyErrorCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "CopyResultCollection".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.CopyResultCollection.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "CopyResult".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.CopyResult.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/sharepoint/soap/".equals(namespaceURI) &&
                  "FieldType".equals(typeName)){
                   
                            return  com.microsoft.schemas.sharepoint.soap.FieldType.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    