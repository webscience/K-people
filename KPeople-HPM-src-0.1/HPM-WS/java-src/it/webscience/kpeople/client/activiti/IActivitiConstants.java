package it.webscience.kpeople.client.activiti;

/**
 * 
 * Interfaccia che definisce le costanti da usare per interfacciarsi con il 
 * workflow engine Activiti.
 */
public interface IActivitiConstants {

	/**
	 * Definisce la password da fornire per eseguire l'autenticazione HTTP 
	 * verso i servizi REST del workflow engine Activiti.
	 */
	String ACTIVITI_FAKE_HTTPAUTH_PASSWORD = "password";
	
	
	/**
	 * Definisce la URL delle API REST del workflow engine Activiti.
	 */
	String ACTIVITI_REST_API_URL = 
		"activity.rest.api.url";
	
	/**
	 * Definisce il servizio REST che consente di creare una istanza di un 
	 * processo nel workflow engine.
	 */
	String ACTIVITI_SERVICE_PROCESSINSTANCE = "process-instance";
	
	/**
	 * Definisce il servizio REST che consente di eseguire una operazione 
	 * su un task nel workflow engine.
	 */
	String ACTIVITI_SERVICE_TASK = "task/"; 
	// /service/task/{taskId}/[claim|complete]
	
	
	String ACTIVITI_SERVICE_TASK_FORM = "/form"; 
	// /service/task/{taskId}/[claim|complete]
	
	/**
	 * Definisce l'operazione CLAIM che può essere effettuata 
	 * su una attività.
	 */
	String ACTIVITI_SERVICE_TASK_OPERATION_CLAIM = "/claim";
	
	
	/**
	 * Definisce l'operazione COMPLETE che può essere effettuata 
	 * su una attività.
	 */
	String ACTIVITI_SERVICE_TASK_OPERATION_COMPLETE = "/complete";
	
	
	/**
	 * Definisce il servizio REST che consente di estrarre il sommario dei 
	 * task di un utente
	 * 
	 * //GET /tasks-summary?user={userId}
	 */
	String ACTIVITI_SERVICE_TASKSSUMMARY = "tasks-summary"; 
	
	
	
	/**
	 * .
	 */
	String ACTIVITI_SERVICE_TASKS = "tasks"; 
	// /service/tasks?assignee=fozzie&start=0&size=10&sort=id&order=asc
	
	/**
	 * .
	 */
	String ACTIVITI_SERVICE_TASKS_MODE_ASSIGNEE="assignee";
	
	/**
	 * .
	 */
	String ACTIVITI_SERVICE_TASKS_MODE_CANDIDATE="candidate";
	
	/**
	 * .
	 */
	String ACTIVITI_SERVICE_TASKS_MODE_CANDIDATEGROUP="candidate-group";
	
	
	String ACTIVITI_SERVICE_LOGIN = "login"; 
	// /service/task/{taskId}/[claim|complete]
}
