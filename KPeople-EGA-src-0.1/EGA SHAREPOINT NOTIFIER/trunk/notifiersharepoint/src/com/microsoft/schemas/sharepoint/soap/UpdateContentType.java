
/**
 * UpdateContentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:47 CEST)
 */
            
                package com.microsoft.schemas.sharepoint.soap;
            

            /**
            *  UpdateContentType bean class
            */
        
        public  class UpdateContentType
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/",
                "UpdateContentType",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://schemas.microsoft.com/sharepoint/soap/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for ListName
                        */

                        
                                    protected java.lang.String localListName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localListNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getListName(){
                               return localListName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ListName
                               */
                               public void setListName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localListNameTracker = true;
                                       } else {
                                          localListNameTracker = false;
                                              
                                       }
                                   
                                            this.localListName=param;
                                    

                               }
                            

                        /**
                        * field for ContentTypeId
                        */

                        
                                    protected java.lang.String localContentTypeId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContentTypeIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getContentTypeId(){
                               return localContentTypeId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ContentTypeId
                               */
                               public void setContentTypeId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localContentTypeIdTracker = true;
                                       } else {
                                          localContentTypeIdTracker = false;
                                              
                                       }
                                   
                                            this.localContentTypeId=param;
                                    

                               }
                            

                        /**
                        * field for ContentTypeProperties
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.ContentTypeProperties_type0 localContentTypeProperties ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContentTypePropertiesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.ContentTypeProperties_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.ContentTypeProperties_type0 getContentTypeProperties(){
                               return localContentTypeProperties;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ContentTypeProperties
                               */
                               public void setContentTypeProperties(com.microsoft.schemas.sharepoint.soap.ContentTypeProperties_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localContentTypePropertiesTracker = true;
                                       } else {
                                          localContentTypePropertiesTracker = false;
                                              
                                       }
                                   
                                            this.localContentTypeProperties=param;
                                    

                               }
                            

                        /**
                        * field for NewFields
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.NewFields_type0 localNewFields ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNewFieldsTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.NewFields_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.NewFields_type0 getNewFields(){
                               return localNewFields;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NewFields
                               */
                               public void setNewFields(com.microsoft.schemas.sharepoint.soap.NewFields_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localNewFieldsTracker = true;
                                       } else {
                                          localNewFieldsTracker = false;
                                              
                                       }
                                   
                                            this.localNewFields=param;
                                    

                               }
                            

                        /**
                        * field for UpdateFields
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.UpdateFields_type0 localUpdateFields ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUpdateFieldsTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.UpdateFields_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.UpdateFields_type0 getUpdateFields(){
                               return localUpdateFields;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UpdateFields
                               */
                               public void setUpdateFields(com.microsoft.schemas.sharepoint.soap.UpdateFields_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localUpdateFieldsTracker = true;
                                       } else {
                                          localUpdateFieldsTracker = false;
                                              
                                       }
                                   
                                            this.localUpdateFields=param;
                                    

                               }
                            

                        /**
                        * field for DeleteFields
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.DeleteFields_type0 localDeleteFields ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDeleteFieldsTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.DeleteFields_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.DeleteFields_type0 getDeleteFields(){
                               return localDeleteFields;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DeleteFields
                               */
                               public void setDeleteFields(com.microsoft.schemas.sharepoint.soap.DeleteFields_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDeleteFieldsTracker = true;
                                       } else {
                                          localDeleteFieldsTracker = false;
                                              
                                       }
                                   
                                            this.localDeleteFields=param;
                                    

                               }
                            

                        /**
                        * field for AddToView
                        */

                        
                                    protected java.lang.String localAddToView ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAddToViewTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAddToView(){
                               return localAddToView;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AddToView
                               */
                               public void setAddToView(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAddToViewTracker = true;
                                       } else {
                                          localAddToViewTracker = false;
                                              
                                       }
                                   
                                            this.localAddToView=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
                org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       UpdateContentType.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://schemas.microsoft.com/sharepoint/soap/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":UpdateContentType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "UpdateContentType",
                           xmlWriter);
                   }

               
                   }
                if (localListNameTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"listName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"listName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("listName");
                                    }
                                

                                          if (localListName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("listName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localListName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localContentTypeIdTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"contentTypeId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"contentTypeId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("contentTypeId");
                                    }
                                

                                          if (localContentTypeId==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("contentTypeId cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localContentTypeId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localContentTypePropertiesTracker){
                                            if (localContentTypeProperties==null){
                                                 throw new org.apache.axis2.databinding.ADBException("contentTypeProperties cannot be null!!");
                                            }
                                           localContentTypeProperties.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","contentTypeProperties"),
                                               factory,xmlWriter);
                                        } if (localNewFieldsTracker){
                                            if (localNewFields==null){
                                                 throw new org.apache.axis2.databinding.ADBException("newFields cannot be null!!");
                                            }
                                           localNewFields.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","newFields"),
                                               factory,xmlWriter);
                                        } if (localUpdateFieldsTracker){
                                            if (localUpdateFields==null){
                                                 throw new org.apache.axis2.databinding.ADBException("updateFields cannot be null!!");
                                            }
                                           localUpdateFields.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","updateFields"),
                                               factory,xmlWriter);
                                        } if (localDeleteFieldsTracker){
                                            if (localDeleteFields==null){
                                                 throw new org.apache.axis2.databinding.ADBException("deleteFields cannot be null!!");
                                            }
                                           localDeleteFields.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","deleteFields"),
                                               factory,xmlWriter);
                                        } if (localAddToViewTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"addToView", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"addToView");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("addToView");
                                    }
                                

                                          if (localAddToView==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("addToView cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAddToView);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localListNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "listName"));
                                 
                                        if (localListName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localListName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("listName cannot be null!!");
                                        }
                                    } if (localContentTypeIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "contentTypeId"));
                                 
                                        if (localContentTypeId != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localContentTypeId));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("contentTypeId cannot be null!!");
                                        }
                                    } if (localContentTypePropertiesTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "contentTypeProperties"));
                            
                            
                                    if (localContentTypeProperties==null){
                                         throw new org.apache.axis2.databinding.ADBException("contentTypeProperties cannot be null!!");
                                    }
                                    elementList.add(localContentTypeProperties);
                                } if (localNewFieldsTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "newFields"));
                            
                            
                                    if (localNewFields==null){
                                         throw new org.apache.axis2.databinding.ADBException("newFields cannot be null!!");
                                    }
                                    elementList.add(localNewFields);
                                } if (localUpdateFieldsTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "updateFields"));
                            
                            
                                    if (localUpdateFields==null){
                                         throw new org.apache.axis2.databinding.ADBException("updateFields cannot be null!!");
                                    }
                                    elementList.add(localUpdateFields);
                                } if (localDeleteFieldsTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "deleteFields"));
                            
                            
                                    if (localDeleteFields==null){
                                         throw new org.apache.axis2.databinding.ADBException("deleteFields cannot be null!!");
                                    }
                                    elementList.add(localDeleteFields);
                                } if (localAddToViewTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "addToView"));
                                 
                                        if (localAddToView != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddToView));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("addToView cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static UpdateContentType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            UpdateContentType object =
                new UpdateContentType();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"UpdateContentType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (UpdateContentType)com.microsoft.schemas.sharepoint.soap.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","listName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setListName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","contentTypeId").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setContentTypeId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","contentTypeProperties").equals(reader.getName())){
                                
                                                object.setContentTypeProperties(com.microsoft.schemas.sharepoint.soap.ContentTypeProperties_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","newFields").equals(reader.getName())){
                                
                                                object.setNewFields(com.microsoft.schemas.sharepoint.soap.NewFields_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","updateFields").equals(reader.getName())){
                                
                                                object.setUpdateFields(com.microsoft.schemas.sharepoint.soap.UpdateFields_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","deleteFields").equals(reader.getName())){
                                
                                                object.setDeleteFields(com.microsoft.schemas.sharepoint.soap.DeleteFields_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","addToView").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAddToView(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          