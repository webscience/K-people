package it.webscience.kpeople.client.activiti;

import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiGetTaskException;
import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiGetTaskFormException;
import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiListTaskException;
import it.webscience.kpeople.client.activiti.exception.KPeopleActivitiLoginException;
import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiPerformTaskOperationException;
import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiStartPatternException;
import it.webscience.kpeople.client.activiti.exception.
	KPeopleActivitiTaskSummaryException;
import it.webscience.kpeople.client.activiti.object.ActivitiProcessInstance;
import it.webscience.kpeople.client.activiti.object.ActivitiTask;
import it.webscience.kpeople.client.activiti.object.ActivitiTaskPerformResponse;
import it.webscience.kpeople.client.activiti.object.ActivitiUserTaskList;
import it.webscience.kpeople.dal.ActivitySingleton;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * 
 * @author gnoni
 *
 */
public class ActivitiClient extends BaseClient implements IActivitiClient {

	/** logger. */
    private Logger logger;
    
    private ActivitySingleton activitySingleton  = ActivitySingleton.getInstance();

    /** Costruttore. 
     * @param pHttpAuthUserName username per autenticazione http
     * @param pHttpAuthPassword password per autenticazione http
     * */
    public ActivitiClient(
    		final String pHttpAuthUserName, 
    		final String pHttpAuthPassword) {
    
    	super(pHttpAuthUserName , pHttpAuthPassword);
    			
        logger = Logger.getLogger(this.getClass().getName());
    }
 
    /** Costruttore. */
	
    public ActivitiClient(String pHttpAuthUserName) {
		this(pHttpAuthUserName,
			 IActivitiConstants.ACTIVITI_FAKE_HTTPAUTH_PASSWORD);
	}
	
    
	@Override
	public final String startActivitiProcess(
			final String pJSONParams) 
		throws KPeopleActivitiStartPatternException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("startActivitiProcess: " + pJSONParams);
        }
		//Lettura parametri formali
		String jSONParams = pJSONParams;
		
		//Costruzione indirizzo servizio da invocare
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
		//urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
		urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_PROCESSINSTANCE);
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doPost(
					serviceUrl, pJSONParams, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiStartPatternException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiStartPatternException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end startActivitiProcess");
        }
		
		return responseContent;
	}

	
	/**
	 * Esegue una operazione sul workflow engine activiti.
	 * //PUT /task/{taskId}/[claim|complete] + nel body JSON 
	 * con parametri x Activiti.
	 * @param pTaskId Identificativo del task da eseguire
	 * @param pJSonParams Json con i dati da passare al workflow engine
	 * @param pTaskOperation operazione da eseguire (claim o complete)
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiPerformTaskOperationException eccezione
	 */
	@Override
	public final String performTaskOperation(
				final String pTaskId, 
				final String pJSonParams,
				final String pTaskOperation)
			throws KPeopleActivitiPerformTaskOperationException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("performTaskOperation: " 
            		+ "pTaskId=" + pTaskId 
            		+ " pTaskOperation" + pTaskOperation);
        }
		
		//Lettura parametri formali
		String taskId = pTaskId;
		String jSonParams = pJSonParams;
		String taskOperation = pTaskOperation;
		
		//Costruzione indirizzo servizio da invocare 
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASK);
		urlSb.append(taskId);
		urlSb.append(taskOperation);
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doPut(
					serviceUrl, jSonParams, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiPerformTaskOperationException(
					e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiPerformTaskOperationException(
					e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end performTaskOperation");
        }
		
		return responseContent;
	}

	
	/**
	 * Ritorna un resoconto dei task dell'utente passato.
	 * //GET /tasks-summary?user={userId} 
	 * @param pUserId Identificativo dello user di cui si richiedono i task
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiTaskSummaryException eccezione
	 */
	@Override
	public final String getTaskSummary(final String pUserId)
			throws KPeopleActivitiTaskSummaryException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getTaskSummary: " + pUserId);
        }
		//Lettura parametri formali
		
		String userId = pUserId;
		//GET /tasks-summary?user={userId}
		
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKSSUMMARY);
		urlSb.append("?");
		urlSb.append("user=" + userId);
		
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(serviceUrl, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiTaskSummaryException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiTaskSummaryException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end getTaskSummary");
        }
		return responseContent;
	}

	
	/**
	 * Estrae la lista dei task assegnati ad uno user.
	 * //GET /tasks?[assignee={userId}]
	 * @param pUserId Identificativo dello user di cui si richiedono i task
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiListTaskException eccezione
	 */
	@Override
	public final String listTasksAssignee(final String pUserId)
			throws KPeopleActivitiListTaskException {

		if (logger.isDebugEnabled()) {
            logger.debug("listTasksAssignee: " + pUserId);
        }
		
		//Lettura parametri formali
		String userId = pUserId;
		
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKS);
		urlSb.append("?");
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKS_MODE_ASSIGNEE 
				+ "=" + userId);
		urlSb.append("&start=0");
		urlSb.append("&size=100");
		urlSb.append("&sort=id");
		urlSb.append("&order=desc");
		
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(
					serviceUrl, dhc);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end listTasksAssignee");
        }
		return responseContent;
	}

	
	/**
	 * Estrae la lista dei task che potenzialmente possono essere presi in 
	 * carico dallo user passato.
	 * //GET /tasks?candidate=fozzie
	 * @param pUserId Identificativo dello user di cui si richiedono i task
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiListTaskException eccezione
	 */
	@Override
	public final String listTasksCandidate(final String pUserId)
			throws KPeopleActivitiListTaskException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("listTasksCandidate: " + pUserId);
        }
		
		//Lettura parametri formali
		String userId = pUserId;
		
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKS);
		urlSb.append("?");
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKS_MODE_CANDIDATE 
				+ "=" + userId);
		
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(serviceUrl, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end listTasksCandidate");
        }
		return responseContent;
	}

	
	/**
	 * Estrae la lista dei task che potenzialmente possono essere presi in 
	 * carico dallo user passato.
	 * //GET /tasks?candidate=fozzie
	 * @param pGroupId Identificativo del gruppo di cui si richiedono i task
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiListTaskException eccezione
	 */
	@Override
	public final String listTasksCandidateGroup(final String pGroupId)
			throws KPeopleActivitiListTaskException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("listTasksCandidateGroup: " + pGroupId);
        }
		
		//Lettura parametri formali
		String groupId = pGroupId;
		
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASKS);
		urlSb.append("?");
		urlSb.append(
				IActivitiConstants.ACTIVITI_SERVICE_TASKS_MODE_CANDIDATEGROUP
				+ "=" + groupId);
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(serviceUrl,  dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiListTaskException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end listTasksCandidateGroup");
        }
		return responseContent;
		
	}

	
	/**
	 * Estrae le informazioni relative ad un task.
	 * //GET /task/{taskId}
	 * @param pTaskId Identificativo del task di cui si richiedono i dettagli
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiGetTaskException eccezione
	 */
	@Override
	public final String getTask(final String pTaskId)
			throws KPeopleActivitiGetTaskException {
		
		
		if (logger.isDebugEnabled()) {
            logger.debug("getTask: " + pTaskId);
        }
		
		//Lettura parametri formali
		String taskId = pTaskId;
		
		//Costruzione indirizzo servizio da invocare
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASK);
		urlSb.append(taskId);
		
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(serviceUrl, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiGetTaskException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiGetTaskException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end getTask");
        }
		return responseContent;
	}

	/**
	 *  //GET /task/{taskId}/form
	 */
	/**
	 * Estrae il form associato a task di cui si passa l'identificativo.
	 * //GET /task/{taskId}/form
	 * @param pTaskId Identificativo del task di cui si richiede il form
	 * @return responseContent risposta del workflow engine
	 * @throws KPeopleActivitiGetTaskFormException eccezione
	 */
	@Override
	public final String getTaskForm(final String pTaskId)
			throws KPeopleActivitiGetTaskFormException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("getTaskForm: " + pTaskId);
        }
		
		//Lettura parametri formali
		String taskId = pTaskId;
		
		//Costruzione indirizzo servizio da invocare
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASK);
		urlSb.append(taskId);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_TASK_FORM);
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doGet(serviceUrl, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiGetTaskFormException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiGetTaskFormException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("end getTaskForm");
        }
		return responseContent;
	}

	@Override
	public ActivitiProcessInstance startActivitiProcessObj(String jSonParams)
			throws KPeopleActivitiStartPatternException {
		// TODO Auto-generated method stub
		String response = startActivitiProcess(jSonParams);
		return new ActivitiProcessInstance(response);
	}

	@Override
	public ActivitiUserTaskList listTasksAssigneeObj(String pUserId)
			throws KPeopleActivitiListTaskException {
		// TODO Auto-generated method stub
		String response = listTasksAssignee(pUserId);
		return new ActivitiUserTaskList(response);
	}

	@Override
	public ActivitiUserTaskList listTasksCandidateObj(String pUserId)
			throws KPeopleActivitiListTaskException {
		// TODO Auto-generated method stub
		String response = listTasksCandidate(pUserId);
		return new ActivitiUserTaskList(response);
	}

	@Override
	public ActivitiUserTaskList listTasksCandidateGroupObj(String pGroupId)
			throws KPeopleActivitiListTaskException {
		// TODO Auto-generated method stub
		String response = listTasksCandidateGroup(pGroupId);
		return new ActivitiUserTaskList(response);
	}

	@Override
	public ActivitiTask getTaskObj(String pTaskId)
			throws KPeopleActivitiGetTaskException {
		// TODO Auto-generated method stub
		String response = getTask(pTaskId);
		return new ActivitiTask(response);
	}

	@Override
	public final ActivitiTaskPerformResponse performTaskOperationObj(
			final String pTaskId, 
			final String pJSonParams,
			final String pTaskOperation)
		throws KPeopleActivitiPerformTaskOperationException {
		// TODO Auto-generated method stub
		String response = performTaskOperation(
				pTaskId, pJSonParams, pTaskOperation);
		return new ActivitiTaskPerformResponse(response);
	}

	@Override
	public String login(String pUsername, String pPassword)
			throws KPeopleActivitiLoginException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("login: " + pUsername + " " + pPassword);
        }
		
		//Lettura parametri formali
		JSONObject jSonObjectInput = new JSONObject();
        
        jSonObjectInput.put("userId", pUsername);
        jSonObjectInput.put("password", pPassword);
        
		String jSonParams = jSonObjectInput.toString();
		
		//Costruzione indirizzo servizio da invocare
		StringBuffer urlSb = new StringBuffer();
		String actvityUrl = activitySingleton.getProperty(IActivitiConstants.ACTIVITI_REST_API_URL);
                //urlSb.append(IActivitiConstants.ACTIVITI_REST_API_URL);
                urlSb.append(actvityUrl);
		urlSb.append(IActivitiConstants.ACTIVITI_SERVICE_LOGIN);
		String serviceUrl = urlSb.toString();
		
		DefaultHttpClient dhc = createClientAndAuthorize();
		
		String responseContent = "";
		try {
			responseContent = HTTPClientHelper.doPost(
					serviceUrl, jSonParams, dhc);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiLoginException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KPeopleActivitiLoginException(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
            logger.debug("login end");
        }
		
		return responseContent;
	}	
}
