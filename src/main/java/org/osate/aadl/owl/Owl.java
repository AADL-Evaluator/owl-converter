package org.osate.aadl.owl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Owl 
{
    private File file;
    private final Map<String,String> prefixes;
    private final Map<String,OwlElement> elements;

    public Owl() 
    {
        this.prefixes = new HashMap<>();
        this.elements = new HashMap<>();
    }

    public File getFile()
    {
        return file;
    }

    public Owl setFile( File file )
    {
        this.file = file;
        return this;
    }

    public Owl addPrefix( String name , String url )
    {
        this.prefixes.put( name , url );
        return this;
    }

    public Owl addElement( OwlElement element )
    {
        this.elements.put( element.getName() , element );
        return this;
    }

    public Map<String,String> getPrefixes()
    {
        return prefixes;
    }

    public Map<String, OwlElement> getElements()
    {
        return elements;
    }
    
}
