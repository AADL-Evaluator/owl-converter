package org.osate.aadl.owl.file;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.osate.aadl.owl.Owl;
import org.osate.aadl.owl.OwlData;
import org.osate.aadl.owl.OwlDeclaration;
import org.osate.aadl.owl.OwlElement;

public class OwlProcess 
{
    
    private OwlProcess()
    {
        // faz nada
    }
    
    public static Owl process( File file ) throws Exception
    {
        return process( Files.readAllLines( file.toPath() ) );
    }
    
    public static Owl process( List<String> lines ) throws Exception
    {
        Owl owl = new Owl();
        
        for( int i = 0 ; i < lines.size() ; i++ )
        {
            String line = OwlUtils.removeComment( 
                lines.get( i ) 
            );
            
            if( line.isEmpty() )
            {
                //faz nada
            }
            else if( line.startsWith( OwlUtils.PREFIX ) )
            {
                addPrefix( owl , line );
            }
            else
            {
                i = addElement( owl , lines , line , i );
            }
        }
        
        return owl;
    }
    
    private static void addPrefix( Owl owl , String line )
    {
        String name = line.substring( 
            OwlUtils.PREFIX.length() + 1 , 
            line.indexOf( "<" )
        ).trim();
        
        String url = line.substring(
            line.indexOf( "<" ) + 1 ,
            line.lastIndexOf( ">" ) == -1 ? line.length() : line.lastIndexOf( ">" )
        ).trim();
        
        owl.addPrefix( name , url );
    }
    
    private static int addElement( Owl owl , List<String> lines , String line , int start )
    {
        OwlElement element = createElement( line );
        owl.addElement( element );
        
        if( OwlUtils.isEnded( line ) )
        {
            return start;
        }
        
        for( int i = start + 1 ; i < lines.size() ; i++ )
        {
            String l = OwlUtils.removeComment( lines.get( i ) );
            
            if( l.isEmpty() )
            {
                continue ;
            }
            
            element.addDeclaration( createDeclaration( l ) );
            
            if( OwlUtils.isEnded( l ) )
            {
                return i;
            }
        }
        
        return lines.size();
    }
    
    private static OwlElement createElement( String line )
    {
        String name  = line.substring( 0 , line.indexOf( " " ) );
        String value = line.substring( 
            line.indexOf( OwlUtils.ELEMENT_START ) + OwlUtils.ELEMENT_START.length()
        );
        
        OwlElement element = new OwlElement();
        element.setName( name );
        element.addA( value.substring( 0 , value.length() - 1 ) );
        
        return element;
    }
    
    private static OwlDeclaration createDeclaration( String line )
    {
        OwlDeclaration declaration = new OwlDeclaration();
        
        declaration.setName(
            line.substring( 0 , line.indexOf( " " ) )
        );
        
        declaration.setValue( createDeclarationValue( 
            line.substring( line.indexOf( " " ) + 1 , line.length() - 1 ) 
        ) );
        
        return declaration;
    }
    
    private static Object createDeclarationValue( String value )
    {
        if( value.startsWith( "\"" ) )
        {
            int endIndexOf  = value.lastIndexOf( "\"" );
            int atIndexOf   = value.lastIndexOf( "@"  );
            int typeIndexOf = value.lastIndexOf( "^^" );
            
            String text = value.substring( 1 , endIndexOf );
            
            String at = atIndexOf == -1 || atIndexOf < endIndexOf 
                ? null
                : value.substring( atIndexOf + 1 );
            
            String type = typeIndexOf == -1 || typeIndexOf < endIndexOf 
                ? null
                : value.substring( typeIndexOf + 2 );
            
            return new OwlData( text , at , type );
        }
        else
        {
            return value;
        }
    }
    
}
