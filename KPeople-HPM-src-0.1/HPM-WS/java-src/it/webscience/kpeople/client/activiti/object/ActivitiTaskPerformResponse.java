package it.webscience.kpeople.client.activiti.object;

import net.sf.json.JSONObject;

public class ActivitiTaskPerformResponse {

	public ActivitiTaskPerformResponse(String pStrObj) {
		this(JSONObject.fromObject(pStrObj));
	}
	
	public ActivitiTaskPerformResponse(JSONObject pJSonObj) {
		
		//Lettura parametri formali
		JSONObject jSonObj = pJSonObj;
		
		this.success = jSonObj.getBoolean("success");
		
	}
	
	/**
	 * identificativo del task
	 */
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
