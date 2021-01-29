package org.osate.aadl.owl.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.osate.aadl.owl.Owl;
import org.osate.aadl.owl.OwlDeclaration;
import org.osate.aadl.owl.OwlElement;

public class OwlProcessTest 
{
    
    @Test
    public void teste_01_simples() throws Exception
    {
        List<String> parts = Arrays.asList( 
            ("@prefix sosa: <http://www.w3.org/ns/sosa/> .\n\n\n" +
            "foaf:Agent a owl:Class .").split( "\n" ) 
        );
        
        println( OwlProcess.process( parts ) );
    }
    
    @Test
    public void teste_02_simples() throws Exception
    {
        List<String> parts = Arrays.asList( 
            ("@prefix sosa: <http://www.w3.org/ns/sosa/> .\n\n\n" +
            "foaf:Agent a owl:Class .\n" +
            "sosa: a owl:Ontology , voaf:Vocabulary ;\n" +
            "  dcterms:title \"Sensor, Observation, Sample, and Actuator (SOSA) Ontology\"@en ;\n" +
            "  dcterms:description \"This ontology is based on the SSN Ontology by the W3C Semantic Sensor Networks Incubator Group (SSN-XG), together with considerations from the W3C/OGC Spatial Data on the Web Working Group.\"@en ;\n" +
            "  dcterms:creator [ a foaf:Agent ; foaf:name \"W3C/OGC Spatial Data on the Web Working Group\"@en ] ;\n" +
            "  dcterms:rights \"Copyright 2017 W3C/OGC.\" ;\n" +
            "  dcterms:license <http://www.w3.org/Consortium/Legal/2015/copyright-software-and-document> ;\n" +
            "  dcterms:license <http://www.opengeospatial.org/ogc/Software> ;\n" +
            "  dcterms:created \"2017-04-17\"^^xsd:date ;\n" +
            "  vann:preferredNamespacePrefix \"sosa\" ;\n" +
            "  vann:preferredNamespaceUri \"http://www.w3.org/ns/sosa/\" .").split( "\n" ) 
        );
        
        println( OwlProcess.process( parts ) );
    }
    
    @Test
    public void teste_03_simples() throws Exception
    {
        println( 
            OwlProcess.process( new File( "sosa.ttl" ) ) 
        );
    }
    
    private void println( Owl owl )
    {
        System.out.println( "Prefixes:" );
        
        for( Map.Entry<String,String> entry : owl.getPrefixes().entrySet() )
        {
            System.out.println( "@prefix " + entry.getKey() + " <" + entry.getValue() + ">" );
        }
        
        System.out.println( "\nElements:" );
        
        for( OwlElement element : owl.getElements().values() )
        {
            System.out.print( element.getName() );
            System.out.print( " a " );
            System.out.print( element.getA().toString().replace('[', ' ' ).replace(']', ' ' ).trim() );
            
            System.out.println( element.getDeclarations().isEmpty() ? "." : ";" );
            
            for( OwlDeclaration declaration : element.getDeclarations().values() )
            {
                System.out.print( "   " );
                System.out.print( declaration.toString() );
                System.out.println();
            }
            
            System.out.println( "" );
        }
    }
    
}
