package org.osate.aadl.owl.convert;

import java.io.File;
import org.osate.aadl.evaluator.project.ComponentPackage;
import org.osate.aadl.evaluator.project.Component;
import org.osate.aadl.evaluator.project.Declaration;
import org.osate.aadl.evaluator.project.Feature;
import org.osate.aadl.evaluator.project.Property;
import org.osate.aadl.owl.file.XmlUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlConvert 
{
    private static final String ELEMENT = "owl:NamedIndividual";
    private static final String ELEMENT_ABOUT = "rdf:about";
    
    private static final String TYPE = "rdf:type";
    private static final String TYPE_RESOURCE = "rdf:resource";
    
    private static final String HAS_PORT = "hasPort";
    private static final String HAS_PORT_RESOURCE = "rdf:resource";
    
    private static final String NAME = "rdfs:label";
    
    private static final String TEXT = "#text";

    public XmlConvert() 
    {
        
    }
    
    public static ComponentPackage convert( File file ) throws Exception
    {
        ComponentPackage pack = new ComponentPackage();
        pack.setFile( file );
        pack.setName( "owl2aadl" );
        
        feed( pack , XmlUtils.read( file ) );
        
        return pack;
    }
    
    private static void feed( ComponentPackage pack , Node node ) throws Exception
    {
        if( ELEMENT.equalsIgnoreCase( node.getNodeName() ) )
        {
            element( pack , node );
        }
        else
        {
            NodeList list = node.getChildNodes();
            
            for( int i = 0 ; i < list.getLength() ; i++ )
            {
                feed( pack , list.item( i ) );
            }
        }
    }
    
    private static void element( ComponentPackage pack , Node node ) throws Exception
    {
        Component component = new Component();
        
        System.out.println( node.getNodeName() );
        
        printProperty( 
            " " , 
            "about" , 
            getPropertyValue( node , ELEMENT_ABOUT ) 
        );
        
        NodeList list = node.getChildNodes();
        
        if( list.getLength() > 0 )
        {
            for( int i = 0 ; i < list.getLength() ; i++ )
            {
                Node child = list.item( i );
                
                if( TEXT.equalsIgnoreCase( child.getNodeName() ) )
                {
                    continue ;
                }
                
                if( TYPE.equalsIgnoreCase( child.getNodeName() ) )
                {
                    setComponentType( component , child );
                }
                else if( HAS_PORT.equalsIgnoreCase( child.getNodeName() ) )
                {
                    addComponentFeature( component , child );
                }
                else if( NAME.equalsIgnoreCase( child.getNodeName() ) )
                {
                    System.out.println( " - name = " + getNodeValue( child ) );
                    component.setName( getNodeValue( child ) );
                }
                else
                {
                    addComponentProperty( component , child );
                }
            }
        }
        
        if( component.getName() != null 
            && component.getType() != null )
        {
            System.out.println( " - add = " + component.getType() );
            pack.add( component );
        }
    }
    
    private static void setComponentType( Component component , Node node ) throws Exception
    {
        String value = getPropertyValue( node , TYPE_RESOURCE );
        
        if( value == null || value.trim().isEmpty() )
        {
            return ;
        }
        
        value = getDronetologyValue( value ).toLowerCase();
        
        System.out.println( " - type = " + value );
        
        if( Component.TYPE_BUS.equalsIgnoreCase( value ) 
            || Component.TYPE_DATA.equalsIgnoreCase( value )
            || Component.TYPE_DEVICE.equalsIgnoreCase( value )
            || Component.TYPE_FEATURE.equalsIgnoreCase( value )
            || Component.TYPE_MEMORY.equalsIgnoreCase( value )
            || Component.TYPE_PROCESS.equalsIgnoreCase( value )
            || Component.TYPE_PROCESSOR.equalsIgnoreCase( value )
            || Component.TYPE_SUBPROGRAM.equalsIgnoreCase( value )
            || Component.TYPE_SYSTEM.equalsIgnoreCase( value )
            || Component.TYPE_THREAD.equalsIgnoreCase( value )
            || Component.TYPE_VIRTUAL.equalsIgnoreCase( value )
        )
        {
            component.setType( value );
        }
    }
    
    private static void addComponentFeature( Component component , Node node ) throws Exception
    {
        String name = getPropertyValue( node , HAS_PORT_RESOURCE );
        
        if( name == null || name.trim().isEmpty() )
        {
            return ;
        }
        
        name = getDronetologyValue( name );
        System.out.println( " - feature = " + name );
        
        component.add( new Feature( name , null ) );
    }
    
    private static void addComponentProperty( Component component , Node node ) throws Exception
    {
        component.add( 
            new Property( node.getNodeName() , getNodeValue( node ) ) 
        );
    }
    
    // ---------------------------------------- //
    // ---------------------------------------- //
    // ---------------------------------------- //
    
    private static String getPropertyValue( Node node , String name )
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
    
    private static String getNodeValue( Node node )
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
    
    private static String getDronetologyValue( String value )
    {
        return value == null || !value.contains( "#" ) 
            ? "" 
            : value.substring( value.indexOf( "#" ) + 1 ).trim();
    }
    
    private static void printProperty( String espaco , String name , String value )
    {
        System.out.print( espaco + "- " );
        System.out.print( name );
        System.out.print( " = " );
        System.out.print( value );
        System.out.println();
    }
    
}
