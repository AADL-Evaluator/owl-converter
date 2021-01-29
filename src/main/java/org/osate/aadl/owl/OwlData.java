package org.osate.aadl.owl;

public class OwlData
{
    // syntax: "value is a string"@en^^type
    private String value;
    private String at;
    private String type;
    
    public OwlData()
    {
        // faz nada
    }

    public OwlData( String value , String at , String type )
    {
        this.value = value;
        this.at = at;
        this.type = type;
    }
    
    public String getValue()
    {
        return value;
    }

    public OwlData setValue( String value )
    {
        this.value = value;
        return this;
    }

    public String getAt()
    {
        return at;
    }

    public OwlData setAt( String at )
    {
        this.at = at;
        return this;
    }

    public String getType()
    {
        return type;
    }

    public OwlData setType( String type )
    {
        this.type = type;
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder()
            .append( "\"" )
            .append( getValue() )
            .append( "\"" );
        
        if( at != null )
        {
            builder.append( "@" )
                .append( at );
        }
        
        if( type != null )
        {
            builder.append( "^^" )
                .append( type );
        }
        
        return builder.toString();
    }
    
}
