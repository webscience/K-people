package it.webscience.kpeople.web.portlet.user.util;
import java.util.Comparator;

import it.webscience.kpeople.service.process.ProcessServiceStub;


public class UserByNameComparator implements Comparator<ProcessServiceStub.User>{

 // compare(o1, o2) < 0     - o1 is less than o2
    // compare(o1, o2) == 0    - o1 is equal to o2
    // compare(o1, o2) > 0     - o1 is greater then o2
   public int compare(ProcessServiceStub.User o1, ProcessServiceStub.User o2) {
       return o1.getFirstName().compareToIgnoreCase( o2.getFirstName() );
   }

}
