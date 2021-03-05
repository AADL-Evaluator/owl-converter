package org.osate.aadl.owl.xml;

import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;

public class OwlObjectProperty 
{
    private final Node node;
    
    private final List<String> subproperties;
    private String name;
    private String domain;
    private String range;

    public OwlObjectProperty( Node node )
    {
        this.node = node;
        this.subproperties = new LinkedList<>();
    }

    public Node getNode()
    {
        return node;
    }
    
    public boolean isIs()
    {
        return getName().contains( "#is" );
    }
    
    public boolean isHas()
    {
        return getName().contains( "#has" );
    }
    
    public String getName()
    {
        return name == null ? "" : name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
    
    public String getDomain()
    {
        return domain;
    }

    public void setDomain( String domain )
    {
        this.domain = domain;
    }

    public String getRange()
    {
        return range;
    }

    public void setRange( String range )
    {
        this.range = range;
    }

    public List<String> getSubproperties()
    {
        return subproperties;
    }
    
}