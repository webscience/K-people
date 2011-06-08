package it.webscience.kpeople.clienttest;

import net.sf.json.JSONObject;

import org.junit.Test;

import junit.framework.Assert;
import it.webscience.kpeople.client.activiti.ActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiClient;
import it.webscience.kpeople.client.activiti.IActivitiConstants;
import it.webscience.kpeople.client.activiti.object.ActivitiProcessInstance;
import it.webscience.kpeople.client.activiti.object.ActivitiTaskPerformResponse;
import it.webscience.kpeople.client.activiti.object.ActivitiUserTaskList;

/**
 * 
 * @author gnoni
 *
 */
public class RichiestaContributo2 {

	private String nTest = "5";
	
	private String patternRequestor = "battaglia@kpeople.webscience.it";
	private String patternRequestorPass = "password";
	
	private String patternProvider = "tramis@kpeople.webscience.it";
	private String patternProviderPass = "password";
	
	private String processDefinitionId = "richiestaContributoFlowAdv:1:116";
	
	private String title = "TITLE"+nTest;
	private String description = "DESCRIPTION"+nTest;
	private String contribution = "CONTRIBUTO"+nTest;
	private String motivation = "MOTIVAZIONE"+nTest;
	private String approva = "true";

	
	@Test
	public void testRichiestaContributoFlow2 () throws Exception {
		
		String processInstanceId 
			= startActivitiProcessTest(processDefinitionId);
		Assert.assertNotSame(processInstanceId, "");
		String taskInserisciContributo 
			= listTasksAssignee(patternProvider,patternProviderPass);
		executeActivityTest_InserisceContributo(taskInserisciContributo);
		String taskApprovaContributo = listTasksAssignee(patternRequestor,patternRequestorPass);
		executeActivityTest_ApprovaContributo(taskApprovaContributo);
		String taskVisualizzaContributo = listTasksAssignee(patternProvider,patternProviderPass);
		executeActivityTest_VisualizzaContributo(taskVisualizzaContributo);
	}
	
	
	private String startActivitiProcessTest(String processDefinitionId) throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,patternRequestorPass);
		
		// Generazione HASHMAP per l'invocazione ad Activiti
        JSONObject jSonObjectInput = new JSONObject();
        
        jSonObjectInput.put("processDefinitionId"
        		, processDefinitionId);
        jSonObjectInput.put("patternProvider", patternProvider);
        jSonObjectInput.put("patternProvider_type", "User");
        jSonObjectInput.put("patternProvider_required", "true");
        jSonObjectInput.put("patternRequestor", patternRequestor);
        jSonObjectInput.put("patternRequestor_type", "User");
        jSonObjectInput.put("patternRequestor_required", "true");
        jSonObjectInput.put("patternTitle", title);
        jSonObjectInput.put("patternDescription", description);
        
        
		String jSonParams = jSonObjectInput.toString();
		
		ActivitiProcessInstance processInstance 
			= client.startActivitiProcessObj(jSonParams);
		
		String processInst = processInstance.getId();
		
		Assert.assertNotSame("", processInst);
		
		return processInst;
		
	}
	

	private String listTasksAssignee(String user,String pass) throws Exception {
		
		IActivitiClient client = new ActivitiClient(user,pass);
		ActivitiUserTaskList tasks = client.listTasksAssigneeObj(user);
		String ret  = tasks.getData().get(0).getId();
		Assert.assertNotSame("", ret);
		Assert.assertEquals(tasks.getData().size(), 1);
		return ret;
	}
	
	
	

	private void executeActivityTest_InserisceContributo(String pTaskId) throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternProvider,patternProviderPass);
		
		
		JSONObject jSonObjectInput = new JSONObject();
		jSonObjectInput.put("patternContent", contribution);
		String strJson = jSonObjectInput.toString();
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);

	}
	

	private void executeActivityTest_ApprovaContributo(String pTaskId) throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,patternRequestorPass);
		
		
		
		JSONObject jSonObjectInput = new JSONObject();
		jSonObjectInput.put("contributeApproved", approva);
		jSonObjectInput.put("contributeApproved_type", "Boolean");
		jSonObjectInput.put("contributeMotivation", motivation);
		
		String strJson = jSonObjectInput.toString();
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);
		
	}
	

	private void executeActivityTest_VisualizzaContributo(String pTaskId) throws Exception {
		
		IActivitiClient client = new ActivitiClient(patternRequestor,patternRequestorPass);
		
		String strJson = "";
		
		ActivitiTaskPerformResponse resp = client.performTaskOperationObj(
				pTaskId, 
				strJson, 
				IActivitiConstants.ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE);
		
         
        Assert.assertEquals(resp.isSuccess(), true);
		
	}
}
