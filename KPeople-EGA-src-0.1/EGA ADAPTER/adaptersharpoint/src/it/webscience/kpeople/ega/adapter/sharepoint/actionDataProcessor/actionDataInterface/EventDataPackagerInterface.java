package it.webscience.kpeople.ega.adapter
.sharepoint.actionDataProcessor.actionDataInterface;




import it.webscience.kpeople.ega.adapter
.sharepoint.kpeopleeventmodel.KpeopleSimpleEvent;


/**
 * @author filieri
 */
public interface EventDataPackagerInterface {

	public String pack(KpeopleSimpleEvent unstructuredEvent) throws Exception;

}
