import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


public class Prova {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File f = new File("C:/Users/filieri/Desktop/KPeople/community/K-people-community.docx");
        InputStream is;
        try {
            is = new FileInputStream(f);
            ContentHandler contenthandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
            Parser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();
            //metadata.set(Metadata.KEYWORDS, "ciao");
            parser.parse(is, contenthandler, metadata, context);

            System.out.println(metadata.get(Metadata.AUTHOR));

            System.out.println(metadata.get(Metadata.CREATION_DATE));
            
            System.out.println(metadata);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        
    }

}
