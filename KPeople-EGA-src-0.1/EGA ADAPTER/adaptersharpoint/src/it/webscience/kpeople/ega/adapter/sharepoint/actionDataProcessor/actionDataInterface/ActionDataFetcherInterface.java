package it.webscience.kpeople.ega.adapter
.sharepoint.actionDataProcessor.actionDataInterface;



import it.webscience.kpeople.domain.model.KpeopleAction;

public interface ActionDataFetcherInterface {

	public Object fetch(KpeopleAction action) throws Exception;

}
