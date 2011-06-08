package it.webscience.uima;

import org.apache.uima.aae.client.UimaAsBaseCallbackListener;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.EntityProcessStatus;

public class StatusCallbackListener extends UimaAsBaseCallbackListener{
	/**
	 * Method called when the processing of a Document is completed
	 */
	@Override
	public void entityProcessComplete(CAS cas, EntityProcessStatus status) {
		String statusMessage = status.getStatusMessage();
		
		AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
		FSIterator<AnnotationFS> it = annotationIndex.iterator();
		for (AnnotationFS annotationFS : annotationIndex) {
			System.out.println(annotationFS.toString());
		}
		
		System.out.println("END entityProcessComplete");
	}
}
