/**
 * CopyStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:01 CEST)
 */
package it.webscience.kpeople.ega.adapter.sharepoint.copyservice;

/*
 *  CopyStub java implementation
 */

public class CopyStub extends org.apache.axis2.client.Stub implements Copy {
    protected org.apache.axis2.description.AxisOperation[] _operations;

    // hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();

    private static int counter = 0;

    private static synchronized java.lang.String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > 99999) {
            counter = 0;
        }
        counter = counter + 1;
        return java.lang.Long.toString(System.currentTimeMillis()) + "_"
                + counter;
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {

        // creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("Copy"
                + getUniqueSuffix());
        addAnonymousOperations();

        // creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[3];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/", "getItem"));
        _service.addOperation(__operation);

        _operations[0] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/",
                "copyIntoItems"));
        _service.addOperation(__operation);

        _operations[1] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://schemas.microsoft.com/sharepoint/soap/",
                "copyIntoItemsLocal"));
        _service.addOperation(__operation);

        _operations[2] = __operation;

    }

    // populates the faults
    private void populateFaults() {

    }

    /**
     * Constructor that takes in a configContext
     */

    public CopyStub(
            org.apache.axis2.context.ConfigurationContext configurationContext,
            java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext and useseperate listner
     */
    public CopyStub(
            org.apache.axis2.context.ConfigurationContext configurationContext,
            java.lang.String targetEndpoint, boolean useSeparateListener)
            throws org.apache.axis2.AxisFault {
        // To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(
                configurationContext, _service);

        _serviceClient.getOptions().setTo(
                new org.apache.axis2.addressing.EndpointReference(
                        targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

        // Set the soap version
        _serviceClient
                .getOptions()
                .setSoapVersionURI(
                        org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);

    }

    /**
     * Default Constructor
     */
    public CopyStub(
            org.apache.axis2.context.ConfigurationContext configurationContext)
            throws org.apache.axis2.AxisFault {

        this(configurationContext, "http://192.168.0.88/_vti_bin/copy.asmx");

    }

    /**
     * Default Constructor
     */
    public CopyStub() throws org.apache.axis2.AxisFault {

        this("http://192.168.0.88/_vti_bin/copy.asmx");

    }

    /**
     * Constructor taking the target endpoint
     */
    public CopyStub(java.lang.String targetEndpoint)
            throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    /**
     * Auto generated method signature
     * 
     * @see it.webscience.kpeople.ega.adapter.sharepoint.copyservice.Copy#getItem
     * @param getItem0
     */

    public com.microsoft.schemas.sharepoint.soap.GetItemResponse getItem(

    com.microsoft.schemas.sharepoint.soap.GetItem getItem0)

    throws java.rmi.RemoteException

    {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                    .createClient(_operations[0].getName());
            _operationClient.getOptions().setAction(
                    "http://schemas.microsoft.com/sharepoint/soap/GetItem");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
                    true);

            addPropertyToOperationClient(
                    _operationClient,
                    org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                    "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                    .getSoapVersionURI()), getItem0,
                    optimizeContent(new javax.xml.namespace.QName(
                            "http://schemas.microsoft.com/sharepoint/soap/",
                            "getItem")));

            // adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            // execute the operation client
           try {
            _operationClient.execute(true);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
                    .getEnvelope();

            java.lang.Object object = fromOM(
                    _returnEnv.getBody().getFirstElement(),
                    com.microsoft.schemas.sharepoint.soap.GetItemResponse.class,
                    getEnvelopeNamespaces(_returnEnv));

            return (com.microsoft.schemas.sharepoint.soap.GetItemResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    // make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
                                .get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class
                                .forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass
                                .newInstance();
                        // message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap
                                .get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class
                                .forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod(
                                "setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            _messageContext.getTransportOut().getSender()
                    .cleanup(_messageContext);
        }
    }

    /**
     * Auto generated method signature
     * 
     * @see it.webscience.kpeople.ega.adapter.sharepoint.copyservice.Copy#copyIntoItems
     * @param copyIntoItems2
     */

    public com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse copyIntoItems(

    com.microsoft.schemas.sharepoint.soap.CopyIntoItems copyIntoItems2)

    throws java.rmi.RemoteException

    {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                    .createClient(_operations[1].getName());
            _operationClient
                    .getOptions()
                    .setAction(
                            "http://schemas.microsoft.com/sharepoint/soap/CopyIntoItems");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
                    true);

            addPropertyToOperationClient(
                    _operationClient,
                    org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                    "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                    .getSoapVersionURI()), copyIntoItems2,
                    optimizeContent(new javax.xml.namespace.QName(
                            "http://schemas.microsoft.com/sharepoint/soap/",
                            "copyIntoItems")));

            // adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            // execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
                    .getEnvelope();

            java.lang.Object object = fromOM(
                    _returnEnv.getBody().getFirstElement(),
                    com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse.class,
                    getEnvelopeNamespaces(_returnEnv));

            return (com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    // make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
                                .get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class
                                .forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass
                                .newInstance();
                        // message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap
                                .get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class
                                .forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod(
                                "setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            _messageContext.getTransportOut().getSender()
                    .cleanup(_messageContext);
        }
    }

    /**
     * Auto generated method signature
     * 
     * @see it.webscience.kpeople.ega.adapter.sharepoint.copyservice.Copy#copyIntoItemsLocal
     * @param copyIntoItemsLocal4
     */

    public com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse copyIntoItemsLocal(

    com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal copyIntoItemsLocal4)

    throws java.rmi.RemoteException

    {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                    .createClient(_operations[2].getName());
            _operationClient
                    .getOptions()
                    .setAction(
                            "http://schemas.microsoft.com/sharepoint/soap/CopyIntoItemsLocal");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
                    true);

            addPropertyToOperationClient(
                    _operationClient,
                    org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                    "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                    .getSoapVersionURI()), copyIntoItemsLocal4,
                    optimizeContent(new javax.xml.namespace.QName(
                            "http://schemas.microsoft.com/sharepoint/soap/",
                            "copyIntoItemsLocal")));

            // adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            // execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
                    .getEnvelope();

            java.lang.Object object = fromOM(
                    _returnEnv.getBody().getFirstElement(),
                    com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse.class,
                    getEnvelopeNamespaces(_returnEnv));

            return (com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    // make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
                                .get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class
                                .forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass
                                .newInstance();
                        // message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap
                                .get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class
                                .forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod(
                                "setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            _messageContext.getTransportOut().getSender()
                    .cleanup(_messageContext);
        }
    }

    /**
     * A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
            org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator
                    .next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }
        return returnMap;
    }

    private javax.xml.namespace.QName[] opNameArray = null;

    private boolean optimizeContent(javax.xml.namespace.QName opName) {

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }
        return false;
    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.GetItem param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(
                    com.microsoft.schemas.sharepoint.soap.GetItem.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.GetItemResponse param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param
                    .getOMElement(
                            com.microsoft.schemas.sharepoint.soap.GetItemResponse.MY_QNAME,
                            org.apache.axiom.om.OMAbstractFactory
                                    .getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.CopyIntoItems param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param
                    .getOMElement(
                            com.microsoft.schemas.sharepoint.soap.CopyIntoItems.MY_QNAME,
                            org.apache.axiom.om.OMAbstractFactory
                                    .getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param
                    .getOMElement(
                            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse.MY_QNAME,
                            org.apache.axiom.om.OMAbstractFactory
                                    .getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param
                    .getOMElement(
                            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal.MY_QNAME,
                            org.apache.axiom.om.OMAbstractFactory
                                    .getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(
            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {
            return param
                    .getOMElement(
                            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse.MY_QNAME,
                            org.apache.axiom.om.OMAbstractFactory
                                    .getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
            org.apache.axiom.soap.SOAPFactory factory,
            com.microsoft.schemas.sharepoint.soap.GetItem param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
                    .getDefaultEnvelope();
            emptyEnvelope
                    .getBody()
                    .addChild(
                            param.getOMElement(
                                    com.microsoft.schemas.sharepoint.soap.GetItem.MY_QNAME,
                                    factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
            org.apache.axiom.soap.SOAPFactory factory,
            com.microsoft.schemas.sharepoint.soap.CopyIntoItems param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
                    .getDefaultEnvelope();
            emptyEnvelope
                    .getBody()
                    .addChild(
                            param.getOMElement(
                                    com.microsoft.schemas.sharepoint.soap.CopyIntoItems.MY_QNAME,
                                    factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
            org.apache.axiom.soap.SOAPFactory factory,
            com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal param,
            boolean optimizeContent) throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
                    .getDefaultEnvelope();
            emptyEnvelope
                    .getBody()
                    .addChild(
                            param.getOMElement(
                                    com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal.MY_QNAME,
                                    factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    /**
     * get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
            org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
            java.lang.Class type, java.util.Map extraNamespaces)
            throws org.apache.axis2.AxisFault {

        try {

            if (com.microsoft.schemas.sharepoint.soap.GetItem.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.GetItem.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (com.microsoft.schemas.sharepoint.soap.GetItemResponse.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.GetItemResponse.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (com.microsoft.schemas.sharepoint.soap.CopyIntoItems.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.CopyIntoItems.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.CopyIntoItemsResponse.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocal.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse.class
                    .equals(type)) {

                return com.microsoft.schemas.sharepoint.soap.CopyIntoItemsLocalResponse.Factory
                        .parse(param.getXMLStreamReaderWithoutCaching());

            }

        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
        return null;
    }

}
