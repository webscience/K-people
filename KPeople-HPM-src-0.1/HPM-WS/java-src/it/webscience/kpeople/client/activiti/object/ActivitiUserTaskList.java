package it.webscience.kpeople.client.activiti.object;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ActivitiUserTaskList {

	/**
	 * 
	 * @param pStrObj
	 */
	public ActivitiUserTaskList(String pStrObj) {
		this(JSONObject.fromObject(pStrObj));
	}
	
	/**
	 * 
	 * @param pJSonObj
	 */
	public ActivitiUserTaskList(JSONObject pJSonObj) {
		
		//Lettura parametri formali
		JSONObject jSonObj = pJSonObj;
		
		JSONArray jsonArray = jSonObj.getJSONArray("data");
		int size = jsonArray.size();
		
		this.data = new ArrayList<ActivitiTask>();
		for (int i = 0; i < size; i++) {
			String item = jsonArray.getString(i);
			JSONObject jSonItem = JSONObject.fromObject(item);
			ActivitiTask at = new ActivitiTask(jSonItem);
			data.add(at);
		}
		
		this.total = jSonObj.getInt("total");
		this.start = jSonObj.getInt("start");
		this.sort = jSonObj.getString("sort");
		this.order = jSonObj.getString("order");
		this.size = jSonObj.getInt("size");
		
	}
	
	/**
	 *  .
	 */
	private List<ActivitiTask> data;
	
	/**
	 *  .
	 */
	private int total;
	
	/**
	 *  .
	 */
	private int start;
	
	/**
	 *  .
	 */
	private String sort;
	
	/**
	 *  .
	 */
	private String order;
	
	/**
	 *  .
	 */
	private int size;

	/**
	 * 
	 * @return
	 */
	public final List<ActivitiTask> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public final void setData(List<ActivitiTask> data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public final int getTotal() {
		return total;
	}

	/**
	 * 
	 * @param pTotal
	 */
	public final void setTotal(final int pTotal) {
		this.total = pTotal;
	}

	/**
	 * 
	 * @return
	 */
	public final int getStart() {
		return start;
	}

	/**
	 * 
	 * @param pStart
	 */
	public final void setStart(final int pStart) {
		this.start = pStart;
	}

	/**
	 * 
	 * @return
	 */
	public final String getSort() {
		return sort;
	}

	/**
	 * 
	 * @param pSort
	 */
	public final void setSort(final String pSort) {
		this.sort = pSort;
	}

	/**
	 * 
	 * @return
	 */
	public final String getOrder() {
		return order;
	}

	/**
	 * 
	 * @param pOrder
	 */
	public final void setOrder(final String pOrder) {
		this.order = pOrder;
	}

	/**
	 * 
	 * @return
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * 
	 * @param pSize
	 */
	public final void setSize(final int pSize) {
		this.size = pSize;
	}

}
