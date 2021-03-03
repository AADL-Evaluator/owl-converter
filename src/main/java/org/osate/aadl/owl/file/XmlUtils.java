package org.osate.aadl.owl.file;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XmlUtils 
{
    
    private XmlUtils()
    {
        // faz nada
    }
    
    public static Document read( File file ) throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        Document doc = db.parse( file );
        doc.getDocumentElement().normalize();  
        
        return doc;
    }
    
}
