package org.osate.aadl.owl.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.osate.aadl.evaluator.project.ComponentPackage;
import org.w3c.dom.Document;

public class XmlConvert 
{
    private static final String OWL_OBJECT_PROPERTY  = "owl:ObjectProperty";
    private static final String OWL_NAMED_INDIVIDUAL = "owl:NamedIndividual";
    private static final String OWL_CLASS = "owl:Class";
    
    // --------------------------------------------------------
    // --------------------------------------------------------
    // --------------------------------------------------------
    
    private final Map<String,OwlClass> classes;
    private final Map<String,OwlObjectProperty> properties;
    
    private ComponentPackage pack;
    private Document root;
    private File file;
    
    public XmlConvert() 
    {
        classes = new HashMap<>();
        properties = new HashMap<>();
    }
    
    public ComponentPackage convert( File file ) throws Exception
    {
        this.file = file;
        this.root = XmlUtils.read( file );
        
        this.pack = new ComponentPackage();
        this.pack.setFile( file );
        this.pack.setName( "owl2aadl" );
        
        OwlClassXmlConvert.feel( classes , root.getElementsByTagName( OWL_CLASS ) );
        OwlObjectPropertyXmlConvert.feel( properties , root.getElementsByTagName( OWL_OBJECT_PROPERTY ) );
        OwlNamedIndividualXmlConvert.feel( this , root.getElementsByTagName( OWL_NAMED_INDIVIDUAL ) );
        
        return pack;
    }
    
    // -------------------------------------
    // ------------------------------------- GETTERs
    // -------------------------------------

    public ComponentPackage getPack()
    {
        return pack;
    }

    public File getFile()
    {
        return file;
    }

    public Document getRoot()
    {
        return root;
    }

    public Map<String, OwlClass> getClasses()
    {
        return classes;
    }

    public Map<String, OwlObjectProperty> getProperties()
    {
        return properties;
    }
    
}
