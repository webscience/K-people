
/**
 * GetListItems.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:47 CEST)
 */
            
                package com.microsoft.schemas.sharepoint.soap;
            

            /**
            *  GetListItems bean class
            */
        
        public  class GetListItems
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/",
                "GetListItems",
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
                        * field for ViewName
                        */

                        
                                    protected java.lang.String localViewName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localViewNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getViewName(){
                               return localViewName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ViewName
                               */
                               public void setViewName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localViewNameTracker = true;
                                       } else {
                                          localViewNameTracker = false;
                                              
                                       }
                                   
                                            this.localViewName=param;
                                    

                               }
                            

                        /**
                        * field for Query
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.Query_type0 localQuery ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueryTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.Query_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.Query_type0 getQuery(){
                               return localQuery;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Query
                               */
                               public void setQuery(com.microsoft.schemas.sharepoint.soap.Query_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localQueryTracker = true;
                                       } else {
                                          localQueryTracker = false;
                                              
                                       }
                                   
                                            this.localQuery=param;
                                    

                               }
                            

                        /**
                        * field for ViewFields
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.ViewFields_type0 localViewFields ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localViewFieldsTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.ViewFields_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.ViewFields_type0 getViewFields(){
                               return localViewFields;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ViewFields
                               */
                               public void setViewFields(com.microsoft.schemas.sharepoint.soap.ViewFields_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localViewFieldsTracker = true;
                                       } else {
                                          localViewFieldsTracker = false;
                                              
                                       }
                                   
                                            this.localViewFields=param;
                                    

                               }
                            

                        /**
                        * field for RowLimit
                        */

                        
                                    protected java.lang.String localRowLimit ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRowLimitTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRowLimit(){
                               return localRowLimit;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RowLimit
                               */
                               public void setRowLimit(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localRowLimitTracker = true;
                                       } else {
                                          localRowLimitTracker = false;
                                              
                                       }
                                   
                                            this.localRowLimit=param;
                                    

                               }
                            

                        /**
                        * field for QueryOptions
                        */

                        
                                    protected com.microsoft.schemas.sharepoint.soap.QueryOptions_type0 localQueryOptions ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueryOptionsTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.schemas.sharepoint.soap.QueryOptions_type0
                           */
                           public  com.microsoft.schemas.sharepoint.soap.QueryOptions_type0 getQueryOptions(){
                               return localQueryOptions;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param QueryOptions
                               */
                               public void setQueryOptions(com.microsoft.schemas.sharepoint.soap.QueryOptions_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localQueryOptionsTracker = true;
                                       } else {
                                          localQueryOptionsTracker = false;
                                              
                                       }
                                   
                                            this.localQueryOptions=param;
                                    

                               }
                            

                        /**
                        * field for WebID
                        */

                        
                                    protected java.lang.String localWebID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localWebIDTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getWebID(){
                               return localWebID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param WebID
                               */
                               public void setWebID(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localWebIDTracker = true;
                                       } else {
                                          localWebIDTracker = false;
                                              
                                       }
                                   
                                            this.localWebID=param;
                                    

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
                       GetListItems.this.serialize(MY_QNAME,factory,xmlWriter);
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
                           namespacePrefix+":GetListItems",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "GetListItems",
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
                             } if (localViewNameTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"viewName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"viewName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("viewName");
                                    }
                                

                                          if (localViewName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("viewName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localViewName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localQueryTracker){
                                            if (localQuery==null){
                                                 throw new org.apache.axis2.databinding.ADBException("query cannot be null!!");
                                            }
                                           localQuery.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","query"),
                                               factory,xmlWriter);
                                        } if (localViewFieldsTracker){
                                            if (localViewFields==null){
                                                 throw new org.apache.axis2.databinding.ADBException("viewFields cannot be null!!");
                                            }
                                           localViewFields.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","viewFields"),
                                               factory,xmlWriter);
                                        } if (localRowLimitTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"rowLimit", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"rowLimit");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("rowLimit");
                                    }
                                

                                          if (localRowLimit==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("rowLimit cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRowLimit);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localQueryOptionsTracker){
                                            if (localQueryOptions==null){
                                                 throw new org.apache.axis2.databinding.ADBException("queryOptions cannot be null!!");
                                            }
                                           localQueryOptions.serialize(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","queryOptions"),
                                               factory,xmlWriter);
                                        } if (localWebIDTracker){
                                    namespace = "http://schemas.microsoft.com/sharepoint/soap/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"webID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"webID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("webID");
                                    }
                                

                                          if (localWebID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("webID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localWebID);
                                            
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
                                    } if (localViewNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "viewName"));
                                 
                                        if (localViewName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localViewName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("viewName cannot be null!!");
                                        }
                                    } if (localQueryTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "query"));
                            
                            
                                    if (localQuery==null){
                                         throw new org.apache.axis2.databinding.ADBException("query cannot be null!!");
                                    }
                                    elementList.add(localQuery);
                                } if (localViewFieldsTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "viewFields"));
                            
                            
                                    if (localViewFields==null){
                                         throw new org.apache.axis2.databinding.ADBException("viewFields cannot be null!!");
                                    }
                                    elementList.add(localViewFields);
                                } if (localRowLimitTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "rowLimit"));
                                 
                                        if (localRowLimit != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRowLimit));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("rowLimit cannot be null!!");
                                        }
                                    } if (localQueryOptionsTracker){
                            elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "queryOptions"));
                            
                            
                                    if (localQueryOptions==null){
                                         throw new org.apache.axis2.databinding.ADBException("queryOptions cannot be null!!");
                                    }
                                    elementList.add(localQueryOptions);
                                } if (localWebIDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
                                                                      "webID"));
                                 
                                        if (localWebID != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWebID));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("webID cannot be null!!");
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
        public static GetListItems parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            GetListItems object =
                new GetListItems();

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
                    
                            if (!"GetListItems".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (GetListItems)com.microsoft.schemas.sharepoint.soap.ExtensionMapper.getTypeObject(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","viewName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setViewName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","query").equals(reader.getName())){
                                
                                                object.setQuery(com.microsoft.schemas.sharepoint.soap.Query_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","viewFields").equals(reader.getName())){
                                
                                                object.setViewFields(com.microsoft.schemas.sharepoint.soap.ViewFields_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","rowLimit").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRowLimit(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","queryOptions").equals(reader.getName())){
                                
                                                object.setQueryOptions(com.microsoft.schemas.sharepoint.soap.QueryOptions_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/","webID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setWebID(
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
           
          