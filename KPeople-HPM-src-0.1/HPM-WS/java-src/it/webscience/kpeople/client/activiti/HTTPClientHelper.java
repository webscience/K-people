package it.webscience.kpeople.client.activiti;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Classe di supporto per le chiamate HTTP: POST, GET e PUT.
 * @author gnoni
 *
 */
public class HTTPClientHelper {

	/**
	 * Costruttore privato.
	 */
	private HTTPClientHelper() {
		
	}
	
	/** logger. */
    private static Logger logger = Logger.getLogger(
    		HTTPClientHelper.class.getName());
    
	/**
	 *  .
	 * @param pServiceUrl
	 * @param pJSONParams
	 * @param pHttpClient
	 * @return HttpResponse
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPost(
			final String pServiceUrl, 
			final String pJSONParams, 
			final DefaultHttpClient pHttpClient) 
		throws ClientProtocolException, IOException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("doPost: " + pServiceUrl);
        }
		
		String jSONParams = pJSONParams;
		String serviceUrl = pServiceUrl;
		DefaultHttpClient dhc = pHttpClient;
		
		HttpPost hp = new HttpPost(serviceUrl);
		hp.setEntity(new StringEntity(jSONParams, "UTF-8"));
		HttpResponse processResponse = null;
		processResponse = dhc.execute(hp);
		String responseContent = null;
		responseContent = EntityUtils.toString(processResponse.getEntity());
		dhc.getConnectionManager().shutdown();
		
		if (logger.isDebugEnabled()) {
            logger.debug("end doPost");
        }
		
		return responseContent;
	}
	
	/**
	 * 
	 * @param pServiceUrl
	 * @param pHttpClient
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doGet(
			final String pServiceUrl, 
			final DefaultHttpClient pHttpClient) 
		throws ClientProtocolException, IOException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("doGet: " + pServiceUrl);
        }
		
		String serviceUrl = pServiceUrl;
		DefaultHttpClient dhc = pHttpClient;
		
		HttpGet hp = new HttpGet(serviceUrl);
		
		HttpResponse processResponse = null;
		processResponse = dhc.execute(hp);
		String responseContent = null;
		responseContent = EntityUtils.toString(processResponse.getEntity());
		dhc.getConnectionManager().shutdown();
		
		if (logger.isDebugEnabled()) {
            logger.debug("end doGet");
        }
		
		return responseContent;
	}
	
	public static String doGet(
			final String pServiceUrl, 
			final DefaultHttpClient pHttpClient,
			final BasicHttpContext localcontext		
	) 
		throws ClientProtocolException, IOException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("doGet: " + pServiceUrl);
        }
		
		String serviceUrl = pServiceUrl;
		DefaultHttpClient dhc = pHttpClient;
		
		HttpGet hp = new HttpGet(serviceUrl);
		
		HttpResponse processResponse = null;
		processResponse = dhc.execute(hp,localcontext);
		String responseContent = null;
		responseContent = EntityUtils.toString(processResponse.getEntity());
		dhc.getConnectionManager().shutdown();
		
		if (logger.isDebugEnabled()) {
            logger.debug("end doGet");
        }
		
		return responseContent;
	}
	/**
	 * 
	 * @param pServiceUrl
	 * @param pJSONParams
	 * @param pHttpClient
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String doPut(
			final String pServiceUrl, 
			final String pJSONParams, 
			final DefaultHttpClient pHttpClient) 
		throws ClientProtocolException, IOException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("doPut: " + pServiceUrl);
        }
		
		String jSONParams = pJSONParams;
		String serviceUrl = pServiceUrl;
		DefaultHttpClient dhc = pHttpClient;
		
		HttpPut hp = new HttpPut(serviceUrl);
		hp.setEntity(new StringEntity(jSONParams, "UTF-8"));
		HttpResponse processResponse = null;
		processResponse = dhc.execute(hp);
		String responseContent = null;
		responseContent = EntityUtils.toString(processResponse.getEntity());
		dhc.getConnectionManager().shutdown();
		
		if (logger.isDebugEnabled()) {
            logger.debug("end doPut");
        }
		
		return responseContent;
	}
}
