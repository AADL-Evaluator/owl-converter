package org.osate.aadl.owl.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.osate.aadl.evaluator.project.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlUtils 
{
    public static final String ABOUT = "rdf:about";
    public static final String RESOURCE = "rdf:resource";
    public static final String TEXT = "#text";
    
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
    
    public static String getPropertyValue( Node node , String name )
    {
        NamedNodeMap attributes = node.getAttributes();
        
        if( attributes == null )
        {
            return null;
        }
        
        Node attribute = attributes.getNamedItem( name );
        
        return attribute == null 
            ? null 
            : attribute.getNodeValue();
    }
    
    public static String getNodeValue( Node node )
    {
        if( node.getNodeValue() != null )
        {
            return node.getNodeValue().trim();
        }
        else if( node.getChildNodes().getLength() > 0 )
        {
            return getNodeValue( 
                node.getChildNodes().item( 0 ) 
            );
        }
        else
        {
            return null;
        }
    }
    
    public static String getDronetologyValue( String value )
    {
        return value == null || !value.contains( "#" ) 
            ? "" 
            : value.substring( value.indexOf( "#" ) + 1 ).trim();
    }
    
    public static void printProperty( String espaco , String name , String value )
    {
        System.out.print( espaco + "- " );
        System.out.print( name );
        System.out.print( " = " );
        System.out.print( value );
        System.out.println();
    }
    
    public static boolean isComponentTypeValid( final String resource )
    {
        String value = XmlUtils.getDronetologyValue( resource ).toLowerCase();
        
        return Component.TYPE_BUS.equalsIgnoreCase( value ) 
            || Component.TYPE_DATA.equalsIgnoreCase( value )
            || Component.TYPE_DEVICE.equalsIgnoreCase( value )
            || Component.TYPE_FEATURE.equalsIgnoreCase( value )
            || Component.TYPE_MEMORY.equalsIgnoreCase( value )
            || Component.TYPE_PROCESS.equalsIgnoreCase( value )
            || Component.TYPE_PROCESSOR.equalsIgnoreCase( value )
            || Component.TYPE_SUBPROGRAM.equalsIgnoreCase( value )
            || Component.TYPE_SYSTEM.equalsIgnoreCase( value )
            || Component.TYPE_THREAD.equalsIgnoreCase( value )
            || Component.TYPE_VIRTUAL.equalsIgnoreCase( value );
    }
    
}
