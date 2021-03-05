package org.osate.aadl.owl.file;

import java.io.File;
import org.osate.aadl.evaluator.project.ComponentPackage;
import org.osate.aadl.owl.xml.XmlConvert;
import org.osate.aadl.owl.main.ComponentPackageUtils;

public class XmlTest 
{
    public static final String FILE = "/home/avld/Área de Trabalho/xml_to_aadl/SAO_V3.owl";
    //String FILE = "/home/avld/Área de Trabalho/xml_to_aadl/example.owl";
    
    public static void main( String[] args ) throws Exception
    {
        //XmlTest test = new XmlTest();
        //test.test01();
        
        ComponentPackage pack = new XmlConvert()
            .convert( new File( FILE ) );
        
        System.out.println( "===============================" );
        System.out.println( "=============================== COMPONENT PACKAGE" );
        System.out.println( "===============================" );
        System.out.println( "" );
        System.out.println( "" );
        
        System.out.println( ComponentPackageUtils.convert( pack ) );
    }
    
    
    
}
