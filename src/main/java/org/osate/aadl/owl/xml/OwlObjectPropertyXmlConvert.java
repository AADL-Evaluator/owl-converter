package org.osate.aadl.owl.xml;

import org.osate.aadl.owl.main.PrintUtils;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OwlObjectPropertyXmlConvert 
{
    private static final String SUB_PROPERTY_OF = "rdfs:subPropertyOf";
    private static final String DOMAIN = "rdfs:domain";
    private static final String RANGE  = "rdfs:range";

    private OwlObjectPropertyXmlConvert()
    {
        // faz nada
    }
    
    public static void feel( Map<String,OwlObjectProperty> properties , NodeList list )
    {
        System.out.println( "" );
        System.out.println( "" );
        cabecalho();
        
        for( int i = 0 ; i < list.getLength() ; i++ )
        {
            OwlObjectProperty property = convert( list.item( i ) );
            properties.put( property.getName() , property );
            
            print( property );
        }
    }
    
    public static OwlObjectProperty convert( Node node )
    {
        OwlObjectProperty property = new OwlObjectProperty( node );
        property.setName( XmlUtils.getPropertyValue( node , XmlUtils.ABOUT ) );
        
        NodeList list = node.getChildNodes();
        
        for( int i = 0 ; i < list.getLength() ; i++ )
        {
            Node child = list.item( i );
            
            if( SUB_PROPERTY_OF.equalsIgnoreCase( child.getNodeName() ) )
            {
                property.getSubproperties().add( XmlUtils.getPropertyValue( node , XmlUtils.RESOURCE ) );
            }
            else if( RANGE.equalsIgnoreCase( child.getNodeName() ) )
            {
                property.setRange( XmlUtils.getPropertyValue( child , XmlUtils.RESOURCE ) );
            }
            else if( DOMAIN.equalsIgnoreCase( child.getNodeName() ) )
            {
                property.setDomain( XmlUtils.getPropertyValue( child , XmlUtils.RESOURCE ) );
            }
        }
        
        return property;
    }
    
    
    
    
    
    private static void cabecalho()
    {
        PrintUtils.center( "OWL OBJECT PROPERTY" ,   123 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""  ,   3 );
        PrintUtils.fill( "-" , 125 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""       ,  3 );
        PrintUtils.left( "NAME"   , 40 );
        PrintUtils.left( "TYPE"   ,  5 );
        PrintUtils.left( "DOMAIN" , 40 );
        PrintUtils.left( "RANGE"  , 40 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""  ,   3 );
        PrintUtils.fill( "-" , 125 );
        PrintUtils.newLine();
    }
    
    private static void print( OwlObjectProperty property )
    {
        PrintUtils.left( "" , 3 );
        PrintUtils.left( XmlUtils.getDronetologyValue( property.getName() )   , 40 );
        PrintUtils.left( property.isIs() ? "is" : "has" ,  5 );
        PrintUtils.left( XmlUtils.getDronetologyValue( property.getDomain() ) , 40 );
        PrintUtils.left( XmlUtils.getDronetologyValue( property.getRange()  ) , 40 );
        PrintUtils.newLine();
    }
    
}