package org.osate.aadl.owl.xml;

import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;

public class OwlClass 
{
    private final Node node;
    
    private String name;
    private String componentType;
    private final List<String> subclasses;
    private final List<String> disjoints;
    
    public OwlClass( Node node )
    {
        this.node = node;
        this.subclasses = new LinkedList<>();
        this.disjoints  = new LinkedList<>();
    }

    public Node getNode()
    {
        return node;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getComponentType()
    {
        return componentType;
    }

    public void setComponentType( String componentType )
    {
        this.componentType = componentType;
    }

    public List<String> getSubclasses() 
    {
        return subclasses;
    }

    public List<String> getDisjoints()
    {
        return disjoints;
    }
    
}