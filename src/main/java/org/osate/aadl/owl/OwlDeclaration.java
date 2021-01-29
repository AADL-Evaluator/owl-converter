package org.osate.aadl.owl;

import java.util.List;

public class OwlDeclaration 
{
    private String name;
    private Object value;

    public OwlDeclaration()
    {
        // faz nada
    }
    
    public OwlDeclaration( String name , Object value )
    {
        this.name = name;
        this.value = value;
    }
    
    public String getName()
    {
        return name;
    }

    public OwlDeclaration setName( String name )
    {
        this.name = name;
        return this;
    }

    public Object getValue()
    {
        return value;
    }

    public OwlDeclaration setValue( Object value )
    {
        this.value = value;
        return this;
    }
    
    // --------------------------------------------
    // --------------------------------------------
    // --------------------------------------------
    
    public boolean isValueData()
    {
        return this.value instanceof OwlData;
    }
    
    public boolean isValueString()
    {
        return this.value instanceof String;
    }
    
    public boolean isValueSubelement()
    {
        return this.value instanceof OwlElement;
    }
    
    public boolean isValueList()
    {
        return this.value instanceof List;
    }

    @Override
    public String toString()
    {
        return getName() + " " + getValue().toString();
    }
    
}