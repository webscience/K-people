package it.webscience.kpeople.client.activiti.object;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ActivitiProcessInstance {

	public ActivitiProcessInstance(String pStrObj) {
		this(JSONObject.fromObject(pStrObj));
	}
	
	public ActivitiProcessInstance(JSONObject pJSonObj) {
		
		//Lettura parametri formali
		JSONObject jSonObj = pJSonObj;
		
		this.id = jSonObj.getString("id");
		this.processDefinitionId = jSonObj.getString("processDefinitionId");
		JSONArray activityNamesArr = jSonObj.getJSONArray("activityNames");
		this.activityNames = new ArrayList<String>(); 
		
		for (int i = 0; i < activityNamesArr.size(); i++) {
			String item = activityNamesArr.getString(i);
			activityNames.add(item);
		}
		
		this.ended = jSonObj.getString("ended");
		
	}

	/**
	 * .
	 */
	private String id;
	
	/**
	 * .
	 */
	private String processDefinitionId;
	
	/**
	 * .
	 */
	private List<String> activityNames;
	
	/**
	 * .
	 */
	private String ended;

	/**
	 * 
	 * @return
	 */
	final public String getId() {
		return id;
	}

	/**
	 * 
	 * @param pId
	 */
	final public void setId(final String pId) {
		this.id = pId;
	}

	/**
	 * 
	 * @return
	 */
	final public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	final public void setProcessDefinitionId(final String pProcessDefinitionId) {
		this.processDefinitionId = pProcessDefinitionId;
	}

	/**
	 * 
	 * @return
	 */
	final public List<String> getActivityNames() {
		return activityNames;
	}

	/**
	 * 
	 * @param pActivityNames
	 */
	final public void setActivityNames(final List<String> pActivityNames) {
		this.activityNames = pActivityNames;
	}

	/**
	 * 
	 * @return
	 */
	final public String getEnded() {
		return ended;
	}

	/**
	 * 
	 * @param pEnded
	 */
	final public void setEnded(final String pEnded) {
		this.ended = pEnded;
	}
	
	
	
}
