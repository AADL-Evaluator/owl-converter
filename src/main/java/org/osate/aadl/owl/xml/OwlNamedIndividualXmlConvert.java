package org.osate.aadl.owl.xml;

import org.osate.aadl.evaluator.project.Component;
import org.osate.aadl.evaluator.project.Feature;
import org.osate.aadl.evaluator.project.Property;
import static org.osate.aadl.owl.xml.XmlUtils.getDronetologyValue;
import static org.osate.aadl.owl.xml.XmlUtils.getNodeValue;
import static org.osate.aadl.owl.xml.XmlUtils.getPropertyValue;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OwlNamedIndividualXmlConvert 
{
    private static final String TYPE = "rdf:type";
    private static final String HAS_PORT = "hasPort";
    private static final String NAME = "rdfs:label";
    
    private OwlNamedIndividualXmlConvert()
    {
        // faz nada
    }
    
    public static void feel( XmlConvert convert , NodeList list )
    {
        for( int i = 0 ; i < list.getLength() ; i++ )
        {
            element( convert , list.item( i ) );
        }
    }
    
    private static void element( XmlConvert convert , Node node )
    {
        System.out.println( node.getNodeName() );
        
        Component component = new Component();
        
        component.setName( XmlUtils.getDronetologyValue( 
            XmlUtils.getPropertyValue( node , XmlUtils.ABOUT ) 
        ) );
        
        NodeList list = node.getChildNodes();
        
        if( list.getLength() > 0 )
        {
            for( int i = 0 ; i < list.getLength() ; i++ )
            {
                Node child = list.item( i );
                
                if( XmlUtils.TEXT.equalsIgnoreCase( child.getNodeName() ) )
                {
                    continue ;
                }
                
                if( TYPE.equalsIgnoreCase( child.getNodeName() ) )
                {
                    setComponentType( convert , component , child );
                }
                else if( HAS_PORT.equalsIgnoreCase( child.getNodeName() ) )
                {
                    addComponentFeature( component , child );
                }
                else if( NAME.equalsIgnoreCase( child.getNodeName() ) )
                {
                    System.out.println( " - name = " + XmlUtils.getNodeValue( child ) );
                    component.setName( XmlUtils.getNodeValue( child ) );
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
            convert.getPack().add( component );
        }
    }
    
    private static void setComponentType( XmlConvert convert , Component component , Node node )
    {
        String value = XmlUtils.getPropertyValue( node , XmlUtils.RESOURCE );
        
        if( value == null || value.trim().isEmpty() )
        {
            return ;
        }
        
        String type = XmlUtils.getDronetologyValue( value );
        
        System.out.println( " - type = " + type );
        
        if( Component.TYPE_BUS.equalsIgnoreCase( type ) 
            || Component.TYPE_DATA.equalsIgnoreCase( type )
            || Component.TYPE_DEVICE.equalsIgnoreCase( type )
            || Component.TYPE_FEATURE.equalsIgnoreCase( type )
            || Component.TYPE_MEMORY.equalsIgnoreCase( type )
            || Component.TYPE_PROCESS.equalsIgnoreCase( type )
            || Component.TYPE_PROCESSOR.equalsIgnoreCase( type )
            || Component.TYPE_SUBPROGRAM.equalsIgnoreCase( type )
            || Component.TYPE_SYSTEM.equalsIgnoreCase( type )
            || Component.TYPE_THREAD.equalsIgnoreCase( type )
            || Component.TYPE_VIRTUAL.equalsIgnoreCase( type )
        )
        {
            component.setType( type.toLowerCase() );
        }
        else
        {
            setComponentTypeByOwnClass( convert , component , value );
        }
    }
    
    private static void addComponentFeature( Component component , Node node )
    {
        String name = getPropertyValue( node , XmlUtils.RESOURCE );
        
        if( name == null || name.trim().isEmpty() )
        {
            return ;
        }
        
        name = getDronetologyValue( name );
        System.out.println( " - feature = " + name );
        
        component.add( new Feature( name , null ) );
    }
    
    private static void addComponentProperty( Component component , Node node )
    {
        component.add( 
            new Property( node.getNodeName() , getNodeValue( node ) ) 
        );
    }
    
    private static void setComponentTypeByOwnClass( XmlConvert convert , Component component , String name )
    {
        if( !convert.getClasses().containsKey( name ) )
        {
            return ;
        }
        
        component.setType( 
            convert.getClasses().get( name ).getComponentType()
        );
    }
    
}