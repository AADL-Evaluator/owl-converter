package org.osate.aadl.owl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OwlElement 
{
    private String name;
    private final List<String> a;
    private final Map<String,OwlDeclaration> declarations;

    public OwlElement() 
    {
        declarations = new HashMap<>();
        a = new LinkedList<>();
    }

    public String getName()
    {
        return name;
    }

    public OwlElement setName( String name )
    {
        this.name = name;
        return this;
    }
    
    public OwlElement addA( String a )
    {
        if( a == null || a.trim().isEmpty() )
        {
            return this;
        }
        else if( a.contains( "," ) )
        {
            for( String p : a.split( "," ) )
            {
                getA().add( p.trim() );
            }
        }
        else
        {
            getA().add( a.trim() );
        }
        
        return this;
    }

    public List<String> getA()
    {
        return a;
    }
    
    public OwlElement addDeclaration( OwlDeclaration declaration )
    {
        this.declarations.put( declaration.getName() , declaration );
        return this;
    }
    
    public Map<String,OwlDeclaration> getDeclarations()
    {
        return declarations;
    }
    
}
