package org.osate.aadl.owl.file;

import java.util.regex.Pattern;

public class OwlUtils 
{
    public static final String COMMENT = "#";
    public static final String PREFIX = "@prefix";
    
    public static final String ELEMENT_START = " a ";
    
    public static final String DECLARATION_OBJECT_START = "[";
    public static final String DECLARATION_OBJECT_END = "]";
    public static final String DECLARATION_ARRAY_SEPRATOR = ";";
    public static final String DECLARATION_SEPARATOR = ";";
    public static final String DECLARATION_END = ".";
    public static final String DECLARATION_NAME_VALUE_SEPARATOR = " ";
    
    private OwlUtils()
    {
        // do nothing
    }
    
    public static boolean isEnded( String line )
    {
        return line.endsWith(OwlUtils.DECLARATION_END );
    }
    
    public static String removeComment( String line )
    {
        line = line.trim();
        
        return line.startsWith( COMMENT ) 
            ? "" 
            : line;
        
    }
    
    public static String removeAspas( String line )
    {
        if( !line.startsWith( "\"" ) 
            || !line.endsWith( "\"" ) )
        {
            return line;
        }
        
        return line.substring( 1 , line.length() - 1 ).trim();
    }
    
    public static String removeDoubleSpace( String line )
    {
        return line.replaceAll( 
            Pattern.quote( "  " ) , 
            " " 
        );
    }
    
}