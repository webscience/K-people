
/**
 * FieldType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:47 CEST)
 */
            
                package com.microsoft.schemas.sharepoint.soap;
            

            /**
            *  FieldType bean class
            */
        
        public  class FieldType
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/",
                "FieldType",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://schemas.microsoft.com/sharepoint/soap/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for FieldType
                        */

                        
                                    protected java.lang.String localFieldType ;
                                
                            private static java.util.HashMap _table_ = new java.util.HashMap();

                            // Constructor
                            
                                protected FieldType(java.lang.String value, boolean isRegisterValue) {
                                    localFieldType = value;
                                    if (isRegisterValue){
                                        
                                               _table_.put(localFieldType, this);
                                           
                                    }

                                }
                            
                                    public static final java.lang.String _Invalid =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Invalid");
                                
                                    public static final java.lang.String _Integer =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Integer");
                                
                                    public static final java.lang.String _Text =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Text");
                                
                                    public static final java.lang.String _Note =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Note");
                                
                                    public static final java.lang.String _DateTime =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("DateTime");
                                
                                    public static final java.lang.String _Counter =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Counter");
                                
                                    public static final java.lang.String _Choice =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Choice");
                                
                                    public static final java.lang.String _Lookup =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Lookup");
                                
                                    public static final java.lang.String _Boolean =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Boolean");
                                
                                    public static final java.lang.String _Number =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Number");
                                
                                    public static final java.lang.String _Currency =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Currency");
                                
                                    public static final java.lang.String _URL =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("URL");
                                
                                    public static final java.lang.String _Computed =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Computed");
                                
                                    public static final java.lang.String _Threading =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Threading");
                                
                                    public static final java.lang.String _Guid =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Guid");
                                
                                    public static final java.lang.String _MultiChoice =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MultiChoice");
                                
                                    public static final java.lang.String _GridChoice =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("GridChoice");
                                
                                    public static final java.lang.String _Calculated =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Calculated");
                                
                                    public static final java.lang.String _File =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("File");
                                
                                    public static final java.lang.String _Attachments =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Attachments");
                                
                                    public static final java.lang.String _User =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("User");
                                
                                    public static final java.lang.String _Recurrence =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Recurrence");
                                
                                    public static final java.lang.String _CrossProjectLink =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("CrossProjectLink");
                                
                                    public static final java.lang.String _ModStat =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("ModStat");
                                
                                    public static final java.lang.String _AllDayEvent =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("AllDayEvent");
                                
                                    public static final java.lang.String _Error =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("Error");
                                
                                public static final FieldType Invalid =
                                    new FieldType(_Invalid,true);
                            
                                public static final FieldType Integer =
                                    new FieldType(_Integer,true);
                            
                                public static final FieldType Text =
                                    new FieldType(_Text,true);
                            
                                public static final FieldType Note =
                                    new FieldType(_Note,true);
                            
                                public static final FieldType DateTime =
                                    new FieldType(_DateTime,true);
                            
                                public static final FieldType Counter =
                                    new FieldType(_Counter,true);
                            
                                public static final FieldType Choice =
                                    new FieldType(_Choice,true);
                            
                                public static final FieldType Lookup =
                                    new FieldType(_Lookup,true);
                            
                                public static final FieldType Boolean =
                                    new FieldType(_Boolean,true);
                            
                                public static final FieldType Number =
                                    new FieldType(_Number,true);
                            
                                public static final FieldType Currency =
                                    new FieldType(_Currency,true);
                            
                                public static final FieldType URL =
                                    new FieldType(_URL,true);
                            
                                public static final FieldType Computed =
                                    new FieldType(_Computed,true);
                            
                                public static final FieldType Threading =
                                    new FieldType(_Threading,true);
                            
                                public static final FieldType Guid =
                                    new FieldType(_Guid,true);
                            
                                public static final FieldType MultiChoice =
                                    new FieldType(_MultiChoice,true);
                            
                                public static final FieldType GridChoice =
                                    new FieldType(_GridChoice,true);
                            
                                public static final FieldType Calculated =
                                    new FieldType(_Calculated,true);
                            
                                public static final FieldType File =
                                    new FieldType(_File,true);
                            
                                public static final FieldType Attachments =
                                    new FieldType(_Attachments,true);
                            
                                public static final FieldType User =
                                    new FieldType(_User,true);
                            
                                public static final FieldType Recurrence =
                                    new FieldType(_Recurrence,true);
                            
                                public static final FieldType CrossProjectLink =
                                    new FieldType(_CrossProjectLink,true);
                            
                                public static final FieldType ModStat =
                                    new FieldType(_ModStat,true);
                            
                                public static final FieldType AllDayEvent =
                                    new FieldType(_AllDayEvent,true);
                            
                                public static final FieldType Error =
                                    new FieldType(_Error,true);
                            

                                public java.lang.String getValue() { return localFieldType;}

                                public boolean equals(java.lang.Object obj) {return (obj == this);}
                                public int hashCode() { return toString().hashCode();}
                                public java.lang.String toString() {
                                
                                        return localFieldType.toString();
                                    

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
                       FieldType.this.serialize(MY_QNAME,factory,xmlWriter);
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
            
                
                //We can safely assume an element has only one type associated with it
                
                            java.lang.String namespace = parentQName.getNamespaceURI();
                            java.lang.String localName = parentQName.getLocalPart();
                        
                            if (! namespace.equals("")) {
                                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                                if (prefix == null) {
                                    prefix = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix, localName, namespace);
                                    xmlWriter.writeNamespace(prefix, namespace);
                                    xmlWriter.setPrefix(prefix, namespace);

                                } else {
                                    xmlWriter.writeStartElement(namespace, localName);
                                }

                            } else {
                                xmlWriter.writeStartElement(localName);
                            }

                            // add the type details if this is used in a simple type
                               if (serializeType){
                                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://schemas.microsoft.com/sharepoint/soap/");
                                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                                           namespacePrefix+":FieldType",
                                           xmlWriter);
                                   } else {
                                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                                           "FieldType",
                                           xmlWriter);
                                   }
                               }
                            
                                          if (localFieldType==null){
                                            
                                                     throw new org.apache.axis2.databinding.ADBException("Value cannot be null !!");
                                                
                                         }else{
                                        
                                                       xmlWriter.writeCharacters(localFieldType);
                                            
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


        
                
                //We can safely assume an element has only one type associated with it
                 return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(MY_QNAME,
                            new java.lang.Object[]{
                            org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT,
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFieldType)
                            },
                            null);

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        
                public static FieldType fromValue(java.lang.String value)
                      throws java.lang.IllegalArgumentException {
                    FieldType enumeration = (FieldType)
                       
                               _table_.get(value);
                           

                    if (enumeration==null) throw new java.lang.IllegalArgumentException();
                    return enumeration;
                }
                public static FieldType fromString(java.lang.String value,java.lang.String namespaceURI)
                      throws java.lang.IllegalArgumentException {
                    try {
                       
                                       return fromValue(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(value));
                                   

                    } catch (java.lang.Exception e) {
                        throw new java.lang.IllegalArgumentException();
                    }
                }

                public static FieldType fromString(javax.xml.stream.XMLStreamReader xmlStreamReader,
                                                                    java.lang.String content) {
                    if (content.indexOf(":") > -1){
                        java.lang.String prefix = content.substring(0,content.indexOf(":"));
                        java.lang.String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
                        return FieldType.Factory.fromString(content,namespaceUri);
                    } else {
                       return FieldType.Factory.fromString(content,"");
                    }
                }
            

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FieldType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FieldType object = null;
                // initialize a hash map to keep values
                java.util.Map attributeMap = new java.util.HashMap();
                java.util.List extraAttributeList = new java.util.ArrayList();
            

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                    
                while(!reader.isEndElement()) {
                    if (reader.isStartElement()  || reader.hasText()){
                
                                    java.lang.String content = reader.getElementText();
                                    
                                        if (content.indexOf(":") > 0) {
                                            // this seems to be a Qname so find the namespace and send
                                            prefix = content.substring(0, content.indexOf(":"));
                                            namespaceuri = reader.getNamespaceURI(prefix);
                                            object = FieldType.Factory.fromString(content,namespaceuri);
                                        } else {
                                            // this seems to be not a qname send and empty namespace incase of it is
                                            // check is done in fromString method
                                            object = FieldType.Factory.fromString(content,"");
                                        }
                                        
                                        
                             } else {
                                reader.next();
                             }  
                           }  // end of while loop
                        



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          