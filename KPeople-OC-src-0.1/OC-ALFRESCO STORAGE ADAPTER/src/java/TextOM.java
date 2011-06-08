import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;


public class TextOM {

    /**
     * @param args
     * @throws XMLStreamException 
     * @throws IOException 
     */
    public static void main(String[] args) throws XMLStreamException, IOException {
        
        File file = new File("C:\\Users\\dellanna\\Desktop\\text.txt");
        OMFactory factory = OMAbstractFactory.getOMFactory();

        FileDataSource fileDataSource = new FileDataSource(file);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        OMText textData = factory.createOMText(dataHandler, true);
        String tom = textData.getText();
        
        
        
    
         
        
   
        
        OMText binaryNode = (OMText) AXIOMUtil.stringToOM("<data>" + tom + "</data>").getFirstOMChild();
        binaryNode.setOptimize(true);
        DataHandler actualDH = (DataHandler) binaryNode.getDataHandler();
        InputStream is = actualDH.getInputStream();

        OutputStream out = new FileOutputStream(new File("C:\\Users\\dellanna\\newfile.xml"));

        int read=0;
        byte[] bytes = new byte[1024];

        while((read = is.read(bytes))!= -1){
                out.write(bytes, 0, read);
        }

        is.close();
        out.flush();
        out.close();    

    }
}
