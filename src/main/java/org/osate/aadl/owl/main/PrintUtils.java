package org.osate.aadl.owl.main;

public class PrintUtils 
{
    
    private PrintUtils()
    {
        // faz nada
    }
    
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    
    public static void fill( String value , int size )
    {
        left( "" , size , value );
    }
    
    public static void left( Object value , int size )
    {
        left( value , size , " " );
    }
    
    public static void left( Object value , int size , String padding )
    {
        String str = value == null ? "" : value.toString();
        
        while( str.length() < size )
        {
            str += padding;
        }
        
        System.out.print( str.substring( 0 , size ) );
    }
    
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    
    public static void right( Object value , int size )
    {
        right( value , size , " " );
    }
    
    public static void right( Object value , int size , String padding )
    {
        String str = value == null ? "" : value.toString();
        
        while( str.length() < size )
        {
            str = padding + str;
        }
        
        System.out.print( str.substring( 0 , size ) );
    }
    
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    // ---------------------------------------------------- //
    
    public static void center( Object value , int size )
    {
        center( value , size , " " );
    }
    
    public static void center( Object value , int size , String padding )
    {
        String str = value == null ? "" : value.toString();
        
        while( str.length() < size )
        {
            str = padding + str + padding;
        }
        
        System.out.print( str.substring( 0 , size ) );
    }
    
    public static void newLine()
    {
        System.out.println();
    }
    
}