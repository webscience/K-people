
/**
 * ListsStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.2  Built on : Sep 06, 2010 (09:42:01 CEST)
 */
package it.webscience.kpeople.ega.notifier.sharepoint.soap;





/*
 *  ListsStub java implementation
 */


public class ListsStub extends org.apache.axis2.client.Stub
implements Lists{
	protected org.apache.axis2.description.AxisOperation[] _operations;

	//hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix(){
		// reset the counter if it is greater than 99999
		if (counter > 99999){
			counter = 0;
		}
		counter = counter + 1; 
		return java.lang.Long.toString(System.currentTimeMillis()) + "_" + counter;
	}


	private void populateAxisService() throws org.apache.axis2.AxisFault {

		//creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("Lists" + getUniqueSuffix());
		addAnonymousOperations();

		//creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[32];

		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListItems"));
		_service.addOperation(__operation);




		_operations[0]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteAttachment"));
		_service.addOperation(__operation);




		_operations[1]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateContentTypeXmlDocument"));
		_service.addOperation(__operation);




		_operations[2]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteContentTypeXmlDocument"));
		_service.addOperation(__operation);




		_operations[3]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addAttachment"));
		_service.addOperation(__operation);




		_operations[4]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListItemChanges"));
		_service.addOperation(__operation);




		_operations[5]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListItemChangesWithKnowledge"));
		_service.addOperation(__operation);




		_operations[6]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "undoCheckOut"));
		_service.addOperation(__operation);




		_operations[7]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getVersionCollection"));
		_service.addOperation(__operation);




		_operations[8]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateListItemsWithKnowledge"));
		_service.addOperation(__operation);




		_operations[9]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addDiscussionBoardItem"));
		_service.addOperation(__operation);




		_operations[10]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "checkInFile"));
		_service.addOperation(__operation);




		_operations[11]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addWikiPage"));
		_service.addOperation(__operation);




		_operations[12]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteList"));
		_service.addOperation(__operation);




		_operations[13]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "createContentType"));
		_service.addOperation(__operation);




		_operations[14]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListCollection"));
		_service.addOperation(__operation);




		_operations[15]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateContentType"));
		_service.addOperation(__operation);




		_operations[16]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListContentType"));
		_service.addOperation(__operation);




		_operations[17]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "checkOutFile"));
		_service.addOperation(__operation);




		_operations[18]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addList"));
		_service.addOperation(__operation);




		_operations[19]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteContentType"));
		_service.addOperation(__operation);




		_operations[20]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListContentTypesAndProperties"));
		_service.addOperation(__operation);




		_operations[21]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addListFromFeature"));
		_service.addOperation(__operation);




		_operations[22]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getAttachmentCollection"));
		_service.addOperation(__operation);




		_operations[23]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateListItems"));
		_service.addOperation(__operation);




		_operations[24]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListItemChangesSinceToken"));
		_service.addOperation(__operation);




		_operations[25]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "applyContentTypeToList"));
		_service.addOperation(__operation);




		_operations[26]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListAndView"));
		_service.addOperation(__operation);




		_operations[27]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getList"));
		_service.addOperation(__operation);




		_operations[28]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateList"));
		_service.addOperation(__operation);




		_operations[29]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "getListContentTypes"));
		_service.addOperation(__operation);




		_operations[30]=__operation;


		__operation = new org.apache.axis2.description.OutInAxisOperation();


		__operation.setName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateContentTypesXmlDocument"));
		_service.addOperation(__operation);




		_operations[31]=__operation;


	}

	//populates the faults
	private void populateFaults(){



	}

	/**
	 *Constructor that takes in a configContext
	 */

	public ListsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint)
	throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}


	/**
	 * Constructor that takes in a configContext  and useseperate listner
	 */
	public ListsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener)
	throws org.apache.axis2.AxisFault {
		//To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);


		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
				targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

		//Set the soap version
		_serviceClient.getOptions().setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);


	}

	/**
	 * Default Constructor
	 */
	public ListsStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://192.168.0.28/_vti_bin/lists.asmx" );

	}

	/**
	 * Default Constructor
	 */
	public ListsStub() throws org.apache.axis2.AxisFault {

		this("http://192.168.0.28/_vti_bin/lists.asmx" );

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public ListsStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}




	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListItems
	 * @param getListItems0

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListItemsResponse getListItems(

			com.microsoft.schemas.sharepoint.soap.GetListItems getListItems0)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListItems");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListItems0,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListItems")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListItemsResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListItemsResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#deleteAttachment
	 * @param deleteAttachment2

	 */



	public  com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse deleteAttachment(

			com.microsoft.schemas.sharepoint.soap.DeleteAttachment deleteAttachment2)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/DeleteAttachment");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					deleteAttachment2,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"deleteAttachment")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateContentTypeXmlDocument
	 * @param updateContentTypeXmlDocument4

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse updateContentTypeXmlDocument(

			com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument updateContentTypeXmlDocument4)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateContentTypeXmlDocument");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateContentTypeXmlDocument4,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateContentTypeXmlDocument")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#deleteContentTypeXmlDocument
	 * @param deleteContentTypeXmlDocument6

	 */



	public  com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse deleteContentTypeXmlDocument(

			com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument deleteContentTypeXmlDocument6)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/DeleteContentTypeXmlDocument");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					deleteContentTypeXmlDocument6,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"deleteContentTypeXmlDocument")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#addAttachment
	 * @param addAttachment8

	 */



	public  com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse addAttachment(

			com.microsoft.schemas.sharepoint.soap.AddAttachment addAttachment8)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[4].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/AddAttachment");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					addAttachment8,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"addAttachment")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListItemChanges
	 * @param getListItemChanges10

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse getListItemChanges(

			com.microsoft.schemas.sharepoint.soap.GetListItemChanges getListItemChanges10)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[5].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListItemChanges");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListItemChanges10,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListItemChanges")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListItemChangesWithKnowledge
	 * @param getListItemChangesWithKnowledge12

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse getListItemChangesWithKnowledge(

			com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge getListItemChangesWithKnowledge12)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[6].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListItemChangesWithKnowledge");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListItemChangesWithKnowledge12,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListItemChangesWithKnowledge")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#undoCheckOut
	 * @param undoCheckOut14

	 */



	public  com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse undoCheckOut(

			com.microsoft.schemas.sharepoint.soap.UndoCheckOut undoCheckOut14)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[7].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UndoCheckOut");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					undoCheckOut14,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"undoCheckOut")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getVersionCollection
	 * @param getVersionCollection16

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse getVersionCollection(

			com.microsoft.schemas.sharepoint.soap.GetVersionCollection getVersionCollection16)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[8].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetVersionCollection");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getVersionCollection16,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getVersionCollection")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateListItemsWithKnowledge
	 * @param updateListItemsWithKnowledge18

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse updateListItemsWithKnowledge(

			com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge updateListItemsWithKnowledge18)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[9].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateListItemsWithKnowledge");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateListItemsWithKnowledge18,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateListItemsWithKnowledge")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#addDiscussionBoardItem
	 * @param addDiscussionBoardItem20

	 */



	public  com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse addDiscussionBoardItem(

			com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem addDiscussionBoardItem20)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[10].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/AddDiscussionBoardItem");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					addDiscussionBoardItem20,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"addDiscussionBoardItem")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#checkInFile
	 * @param checkInFile22

	 */



	public  com.microsoft.schemas.sharepoint.soap.CheckInFileResponse checkInFile(

			com.microsoft.schemas.sharepoint.soap.CheckInFile checkInFile22)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[11].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/CheckInFile");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					checkInFile22,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"checkInFile")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.CheckInFileResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.CheckInFileResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#addWikiPage
	 * @param addWikiPage24

	 */



	public  com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse addWikiPage(

			com.microsoft.schemas.sharepoint.soap.AddWikiPage addWikiPage24)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[12].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/AddWikiPage");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					addWikiPage24,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"addWikiPage")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#deleteList
	 * @param deleteList26

	 */



	public  com.microsoft.schemas.sharepoint.soap.DeleteListResponse deleteList(

			com.microsoft.schemas.sharepoint.soap.DeleteList deleteList26)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[13].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/DeleteList");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					deleteList26,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"deleteList")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.DeleteListResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.DeleteListResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#createContentType
	 * @param createContentType28

	 */



	public  com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse createContentType(

			com.microsoft.schemas.sharepoint.soap.CreateContentType createContentType28)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[14].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/CreateContentType");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					createContentType28,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"createContentType")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListCollection
	 * @param getListCollection30

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse getListCollection(

			com.microsoft.schemas.sharepoint.soap.GetListCollection getListCollection30)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[15].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListCollection");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListCollection30,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListCollection")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateContentType
	 * @param updateContentType32

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse updateContentType(

			com.microsoft.schemas.sharepoint.soap.UpdateContentType updateContentType32)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[16].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateContentType");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateContentType32,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateContentType")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListContentType
	 * @param getListContentType34

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse getListContentType(

			com.microsoft.schemas.sharepoint.soap.GetListContentType getListContentType34)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[17].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListContentType");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListContentType34,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListContentType")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#checkOutFile
	 * @param checkOutFile36

	 */



	public  com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse checkOutFile(

			com.microsoft.schemas.sharepoint.soap.CheckOutFile checkOutFile36)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[18].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/CheckOutFile");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					checkOutFile36,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"checkOutFile")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#addList
	 * @param addList38

	 */



	public  com.microsoft.schemas.sharepoint.soap.AddListResponse addList(

			com.microsoft.schemas.sharepoint.soap.AddList addList38)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[19].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/AddList");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					addList38,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"addList")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.AddListResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.AddListResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#deleteContentType
	 * @param deleteContentType40

	 */



	public  com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse deleteContentType(

			com.microsoft.schemas.sharepoint.soap.DeleteContentType deleteContentType40)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[20].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/DeleteContentType");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					deleteContentType40,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"deleteContentType")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListContentTypesAndProperties
	 * @param getListContentTypesAndProperties42

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse getListContentTypesAndProperties(

			com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties getListContentTypesAndProperties42)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[21].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListContentTypesAndProperties");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListContentTypesAndProperties42,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListContentTypesAndProperties")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#addListFromFeature
	 * @param addListFromFeature44

	 */



	public  com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse addListFromFeature(

			com.microsoft.schemas.sharepoint.soap.AddListFromFeature addListFromFeature44)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[22].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/AddListFromFeature");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					addListFromFeature44,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"addListFromFeature")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getAttachmentCollection
	 * @param getAttachmentCollection46

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse getAttachmentCollection(

			com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection getAttachmentCollection46)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[23].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetAttachmentCollection");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getAttachmentCollection46,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getAttachmentCollection")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateListItems
	 * @param updateListItems48

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse updateListItems(

			com.microsoft.schemas.sharepoint.soap.UpdateListItems updateListItems48)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[24].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateListItems");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateListItems48,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateListItems")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListItemChangesSinceToken
	 * @param getListItemChangesSinceToken50

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse getListItemChangesSinceToken(

			com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken getListItemChangesSinceToken50)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[25].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListItemChangesSinceToken");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListItemChangesSinceToken50,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListItemChangesSinceToken")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#applyContentTypeToList
	 * @param applyContentTypeToList52

	 */



	public  com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse applyContentTypeToList(

			com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList applyContentTypeToList52)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[26].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/ApplyContentTypeToList");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					applyContentTypeToList52,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"applyContentTypeToList")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListAndView
	 * @param getListAndView54

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse getListAndView(

			com.microsoft.schemas.sharepoint.soap.GetListAndView getListAndView54)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[27].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListAndView");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListAndView54,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListAndView")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getList
	 * @param getList56

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListResponse getList(

			com.microsoft.schemas.sharepoint.soap.GetList getList56)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[28].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetList");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getList56,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getList")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateList
	 * @param updateList58

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateListResponse updateList(

			com.microsoft.schemas.sharepoint.soap.UpdateList updateList58)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[29].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateList");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateList58,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateList")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateListResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateListResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#getListContentTypes
	 * @param getListContentTypes60

	 */



	public  com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse getListContentTypes(

			com.microsoft.schemas.sharepoint.soap.GetListContentTypes getListContentTypes60)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[30].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/GetListContentTypes");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getListContentTypes60,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"getListContentTypes")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see it.webscience.kpeople.ega.notifier.soap.Lists#updateContentTypesXmlDocument
	 * @param updateContentTypesXmlDocument62

	 */



	public  com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse updateContentTypesXmlDocument(

			com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument updateContentTypesXmlDocument62)


	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[31].getName());
			_operationClient.getOptions().setAction("http://schemas.microsoft.com/sharepoint/soap/UpdateContentTypesXmlDocument");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



			addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();



			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;


			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					updateContentTypesXmlDocument62,
					optimizeContent(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/",
					"updateContentTypesXmlDocument")));

			//adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			//execute the operation client
			_operationClient.execute(true);


			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
					org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement() ,
					com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse.class,
					getEnvelopeNamespaces(_returnEnv));


			return (com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse)object;

		}catch(org.apache.axis2.AxisFault f){

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt!=null){
				if (faultExceptionNameMap.containsKey(faultElt.getQName())){
					//make the fault by reflection
					try{
						java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex=
							(java.lang.Exception) exceptionClass.newInstance();
						//message class
						java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[]{messageClass});
						m.invoke(ex,new java.lang.Object[]{messageObject});


						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}catch(java.lang.ClassCastException e){
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}  catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}   catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				}else{
					throw f;
				}
			}else{
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}



	/**
	 *  A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
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
	//http://192.168.0.66/_vti_bin/lists.asmx
	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItems param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemsResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteAttachment param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddAttachment param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChanges param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChanges.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UndoCheckOut param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UndoCheckOut.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetVersionCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetVersionCollection.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CheckInFile param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckInFile.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CheckInFileResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckInFileResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddWikiPage param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddWikiPage.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteListResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CreateContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CreateContentType.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListCollection.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentType.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentType.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CheckOutFile param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckOutFile.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddListResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentType.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddListFromFeature param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddListFromFeature.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateListItems param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListAndView param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListAndView.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateListResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentTypes param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypes.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}

	private  org.apache.axiom.om.OMElement  toOM(com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault {


		try{
			return param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListItems param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItems.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.DeleteAttachment param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteAttachment.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.AddAttachment param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddAttachment.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListItemChanges param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChanges.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UndoCheckOut param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UndoCheckOut.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetVersionCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetVersionCollection.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.CheckInFile param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckInFile.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.AddWikiPage param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddWikiPage.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.DeleteList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteList.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.CreateContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.CreateContentType.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListCollection.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentType.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentType.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.CheckOutFile param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.CheckOutFile.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.AddList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddList.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.DeleteContentType param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.DeleteContentType.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.AddListFromFeature param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.AddListFromFeature.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateListItems param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateListItems.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListAndView param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListAndView.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetList.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateList param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateList.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.GetListContentTypes param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.GetListContentTypes.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */



	private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument param, boolean optimizeContent)
	throws org.apache.axis2.AxisFault{


		try{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument.MY_QNAME,factory));
			return emptyEnvelope;
		} catch(org.apache.axis2.databinding.ADBException e){
			throw org.apache.axis2.AxisFault.makeFault(e);
		}


	}


	/* methods to provide back word compatibility */




	/**
	 *  get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
		return factory.getDefaultEnvelope();
	}


	private  java.lang.Object fromOM(
			org.apache.axiom.om.OMElement param,
			java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

		try {

			if (com.microsoft.schemas.sharepoint.soap.GetListItems.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItems.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemsResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteAttachment.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteAttachment.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteAttachmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddAttachment.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddAttachment.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddAttachmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChanges.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChanges.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledge.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UndoCheckOut.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UndoCheckOut.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UndoCheckOutResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetVersionCollection.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetVersionCollection.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledge.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItem.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CheckInFile.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CheckInFile.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CheckInFileResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CheckInFileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddWikiPage.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddWikiPage.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddWikiPageResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteList.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteList.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteListResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CreateContentType.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CreateContentType.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CreateContentTypeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListCollection.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListCollection.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListCollectionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentType.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentType.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentType.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentType.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CheckOutFile.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CheckOutFile.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.CheckOutFileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddList.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddList.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddListResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteContentType.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteContentType.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndProperties.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddListFromFeature.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddListFromFeature.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetAttachmentCollection.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateListItems.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateListItems.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceToken.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToList.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListAndView.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListAndView.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListAndViewResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetList.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetList.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateList.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateList.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateListResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentTypes.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentTypes.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

			if (com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse.class.equals(type)){

				return com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}




}
