package it.webscience.kpeople.client.activiti.object;

import net.sf.json.JSONObject;

public class ActivitiTask {

	public ActivitiTask(String pStrObj) {
		this(JSONObject.fromObject(pStrObj));
	}
	
	public ActivitiTask(JSONObject pJSonObj) {
		
		//Lettura parametri formali
		JSONObject jSonObj = pJSonObj;
		
		this.id = jSonObj.getString("id");
		this.name = jSonObj.getString("name");
		this.description = jSonObj.getString("description");
		this.priority = jSonObj.getString("priority");
		this.assignee = jSonObj.getString("assignee");
		this.executionId = jSonObj.getString("executionId");
		this.processInstanceId = jSonObj.getString("processInstanceId");
		this.formResourceKey = jSonObj.getString("formResourceKey");
	}
	
	/**
	 * identificativo del task
	 */
	private String id;
	
	/**
	 * nome del task
	 */
	private String name;
	
	/**
	 * descrizione del task
	 */
	private String description;
	
	/**
	 * priorità del task
	 */
	private String priority;
	
	/**
	 * assegnatario del task
	 */
	private String assignee;
	
	/**
	 * executionId
	 */
	private String executionId;
	
	/**
	 * processInstanceId
	 */
	private String processInstanceId;
	
	/**
	 * formResourceKey
	 */
	private String formResourceKey;

	public final String getId() {
		return id;
	}

	public final void setId(final String pId) {
		this.id = pId;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String pName) {
		this.name = pName;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String pDescription) {
		this.description = pDescription;
	}

	public final String getPriority() {
		return priority;
	}

	public final void setPriority(final String pPriority) {
		this.priority = pPriority;
	}

	public final String getAssignee() {
		return assignee;
	}

	public final void setAssignee(final String pAssignee) {
		this.assignee = pAssignee;
	}

	public final String getExecutionId() {
		return executionId;
	}

	public final void setExecutionId(final String pExecutionId) {
		this.executionId = pExecutionId;
	}

	public final String getProcessInstanceId() {
		return processInstanceId;
	}

	public final void setProcessInstanceId(final String pProcessInstanceId) {
		this.processInstanceId = pProcessInstanceId;
	}

	public final String getFormResourceKey() {
		return formResourceKey;
	}

	
	public final void setFormResourceKey(final String pFormResourceKey) {
		this.formResourceKey = pFormResourceKey;
	}
	
	
	
}
