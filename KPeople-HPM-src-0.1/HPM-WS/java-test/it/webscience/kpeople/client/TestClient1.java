package it.webscience.kpeople.client;

import net.sf.json.JSONObject;

import org.junit.Test;

import junit.framework.Assert;
import it.webscience.kpeople.client.activiti.ActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiConstants;
import it.webscience.kpeople.client.activiti.object.ActivitiProcessInstance;
import it.webscience.kpeople.client.activiti.object.ActivitiTaskPerformResponse;
import it.webscience.kpeople.client.activiti.object.ActivitiUserTaskList;

public class TestClient1 {

	private String patternRequestor = "utente1@kpeople.webscience.it";
	private String patternProvider = "utente2@kpeople.webscience.it";
	private String httpPass = "password";
	
	@Test
	public void startActivitiProcessTest() throws Exception {
		
		
		
		IActivitiClient client = new ActivitiClient(patternRequestor,httpPass);
		
		// Generazione HASHMAP per l'invocazione ad Activiti
        JSONObject jSonObjectInput = new JSONObject();
        
        jSonObjectInput.put("processDefinitionId"
        		, "richiestaContributoFlowAdv:1:116");
        jSonObjectInput.put("patternProvider", patternProvider);
        jSonObjectInput.put("patternProvider_type", "User");
        jSonObjectInput.put("patternProvider_required", "true");
        jSonObjectInput.put("patternRequestor", patternRequestor);
        jSonObjectInput.put("patternRequestor_type", "User");
        jSonObjectInput.put("patternRequestor_required", "true");
        jSonObjectInput.put("patternTitle", "TITLE1");
        jSonObjectInput.put("patternDescription", "DESCRIPTION1");
        
        
		String jSonParams = jSonObjectInput.toString();
		
		ActivitiProcessInstance processInstance 
			= client.startActivitiProcessObj(jSonParams);
		
		System.out.println("Creato un processo su activiti con id = "
				+ processInstance.getId() );
		Assert.assertNotSame("", processInstance.getId() );
		
	}
	
	@Test
	public void listTasksAssigneeProvider() throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternProvider,httpPass);
	
		ActivitiUserTaskList tasks = client.listTasksAssigneeObj(patternProvider);
		
        Assert.assertEquals(tasks.getData().size(), 1);
	}
	
	@Test
	public void listTasksAssigneeRequestor() throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,httpPass);
	
		ActivitiUserTaskList tasks = client.listTasksAssigneeObj(patternRequestor);
		
		
        Assert.assertEquals(tasks.getData().size(), 1);
	}
	
	@Test
	public void executeActivityTest_InserisceContributo() throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternProvider,httpPass);
		
		String pTaskId = "421";
		
		JSONObject jSonObjectInput = new JSONObject();
		jSonObjectInput.put("patternDescription", "bla bla bla");
		String strJson = jSonObjectInput.toString();
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);

	}
	
	@Test
	public void executeActivityTest_ApprovaContributo() throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,httpPass);
		
		String pTaskId = "451";
		
		JSONObject jSonObjectInput = new JSONObject();
		jSonObjectInput.put("contributeApproved", "true");
		jSonObjectInput.put("contributeApproved_type", "Boolean");
		jSonObjectInput.put("contributeMotivation", "is good");
		
		String strJson = jSonObjectInput.toString();
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);
		
	}
	
	@Test
	public void executeActivityTest_VisualizzaContributo() throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,httpPass);
		
		String pTaskId = "456";
		
		String strJson = "";
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);
		
	}
}
