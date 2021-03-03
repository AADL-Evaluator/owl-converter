package org.osate.aadl.owl.file;

import java.io.File;
import org.junit.Test;
import org.osate.aadl.evaluator.project.Component;
import org.osate.aadl.evaluator.project.ComponentPackage;
import org.osate.aadl.owl.convert.XmlConvert;
import org.osate.aadl.owl.utils.ComponentPackageUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTest 
{
    public static final String FILE = "/home/avld/Área de Trabalho/xml_to_aadl/SAO_V2.owl";
    //String FILE = "/home/avld/Área de Trabalho/xml_to_aadl/example.owl";
    
    @Test
    public void test01() throws Exception
    {
        print("" , XmlUtils.read(new File( FILE ) ) );
    }
    
    private void print( String espaco , Node node )
    {
        NamedNodeMap attributes = node.getAttributes();
        NodeList list = node.getChildNodes();
        
        System.out.print( espaco );
        System.out.println( node.getNodeName() );
        
        if( attributes != null )
        {
            for( int i = 0 ; i < attributes.getLength() ; i++ )
            {
                Node attribute = attributes.item( i );

                System.out.print( espaco + "-" );
                System.out.print( attribute.getNodeName() );
                System.out.print( " = " );
                System.out.print( attribute.getNodeValue() );
                System.out.println();
            }
        }
        
        if( list.getLength() == 0 )
        {
            System.out.print( espaco );
            System.out.println( node.getNodeValue() );
        }
        else
        {
            for( int i = 0 ; i < list.getLength() ; i++ )
            {
                Node child = list.item( i );
                
                System.out.print( espaco );
                System.out.println( child.getNodeName() );
                
                print( espaco + "  " , child );
            }
        }
    }
    
    public static void main( String[] args ) throws Exception
    {
        //XmlTest test = new XmlTest();
        //test.test01();
        
        ComponentPackage pack = XmlConvert.convert( new File( FILE ) );
        
        System.out.println( "===============================" );
        System.out.println( "=============================== COMPONENT PACKAGE" );
        System.out.println( "===============================" );
        System.out.println( "" );
        System.out.println( "" );
        
        System.out.println( ComponentPackageUtils.convert( pack ) );
    }
    
    
    
}
