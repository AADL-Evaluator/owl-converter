package org.osate.aadl.owl.xml;

import org.osate.aadl.owl.main.PrintUtils;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OwlClassXmlConvert 
{
    private static final String SUB_CLASS_OF  = "rdfs:subClassOf";
    private static final String DISJOINT_WITH = "owl:disjointWith";
    private static final String IS_DIFINED_BY = "rdfs:isDefinedBy";
    
    private OwlClassXmlConvert()
    {
        // faz nada
    }
    
    public static void feel( Map<String,OwlClass> classes , NodeList list )
    {
        cabecalho();
        
        for( int i = 0 ; i < list.getLength() ; i++ )
        {
            OwlClass oclass = convert( list.item( i ) );
            classes.put( oclass.getName() , oclass );
        }
        
        for( OwlClass oclass : classes.values() )
        {
            if( oclass.getComponentType() == null )
            {
                setComponentType( classes , oclass );
            }
            
            print( oclass );
        }
    }
    
    public static void setComponentType( Map<String,OwlClass> classes , OwlClass oclass )
    {
        for( String subclass : oclass.getSubclasses() )
        {
            if( !classes.containsKey( subclass ) )
            {
                continue ;
            }
            
            OwlClass sub = classes.get( subclass );
            
            if( sub.getComponentType() == null )
            {
                setComponentType( classes , sub );
            }
            
            if( sub.getComponentType() != null )
            {
                oclass.setComponentType( sub.getComponentType() );
            }
        }
    }
    
    public static OwlClass convert( Node node )
    {
        OwlClass ownClass = new OwlClass( node );
        ownClass.setName( XmlUtils.getPropertyValue( node , XmlUtils.ABOUT ) );
        
        children( ownClass , node );
        
        return ownClass;
    }
    
    private static void children( OwlClass ownClass , Node node )
    {
        NodeList list = node.getChildNodes();
        
        for( int i = 0 ; i < list.getLength() ; i++ )
        {
            Node child = list.item( i );
            
            if( SUB_CLASS_OF.equalsIgnoreCase( child.getNodeName() ) )
            {
                String value = XmlUtils.getPropertyValue( child , XmlUtils.RESOURCE );
                
                if( XmlUtils.isComponentTypeValid( value ) )
                {
                    ownClass.setComponentType( 
                        XmlUtils.getDronetologyValue( value ).toLowerCase() 
                    );
                }
                
                ownClass.getSubclasses().add( value );
            }
            else if( DISJOINT_WITH.equalsIgnoreCase( child.getNodeName() ) )
            {
                ownClass.getDisjoints().add(
                    XmlUtils.getPropertyValue( child , XmlUtils.RESOURCE )
                );
            }
        }
    }
    
    private static void cabecalho()
    {
        PrintUtils.center( "OWL:CLASS" ,   123 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""  ,   3 );
        PrintUtils.fill( "-" , 120 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""     ,   3 );
        PrintUtils.left( "NAME" , 100 );
        PrintUtils.left( "TYPE" ,  20 );
        PrintUtils.newLine();
        
        PrintUtils.left( ""  ,   3 );
        PrintUtils.fill( "-" , 100 );
        PrintUtils.fill( "-" ,  20 );
        PrintUtils.newLine();
    }
    
    private static void print( OwlClass oclass )
    {
        PrintUtils.left( "" , 3 );
        PrintUtils.left( oclass.getName()  , 100 );
        PrintUtils.left( oclass.getComponentType() == null ? "-" : oclass.getComponentType() ,  20 );
        PrintUtils.newLine();
    }
    
}