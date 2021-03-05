package org.osate.aadl.owl.main;

import java.util.Collection;
import org.osate.aadl.evaluator.project.Component;
import org.osate.aadl.evaluator.project.ComponentPackage;
import org.osate.aadl.evaluator.project.Declaration;

public class ComponentPackageUtils
{
    
    private ComponentPackageUtils()
    {
        // do nothing
    }
    
    public static String convert( ComponentPackage pack ) 
    {
        return convert( new StringBuilder() , pack )
            .toString();
    }
    
    private static StringBuilder convert( StringBuilder builder , ComponentPackage pack ) 
    {
        if( pack == null )
        {
            return builder;
        }
        
        builder.append( "package " )
            .append( pack.getName() )
            .append( System.lineSeparator() )
            .append( "public" )
            .append( System.lineSeparator() );
        
        for( Component component : pack.getComponents().values() )
        {
            builder.append( System.lineSeparator() );
            toString( builder , component );
        }
        
        return builder;
    }
    
    private static StringBuilder toString( StringBuilder builder , Component component )
    {
        if( component == null )
        {
            return builder;
        }
        
        // first line
        builder.append( component.getType() );
        builder.append( component.isImplementation() ? " implementation " : " " );
        builder.append( component.getName() );
        builder.append( component.getExtend() == null || component.getExtend().trim().isEmpty()
            ? System.lineSeparator() 
            : " extend " + component.getExtend() + System.lineSeparator() 
        );
        
        toString( builder , "features"      , component.getFeaturesAll().values()      , ":" );
        toString( builder , "subcomponents" , component.getSubcomponentsAll().values() , ":" );
        toString( builder , "connections"   , component.getConnectionsAll().values()   , ":" );
        toString( builder , "properties"    , component.getPropertiesAll()             , "=>" );
        
        // last line
        builder.append( "end " );
        builder.append( component.getName() );
        builder.append( ";" );
        builder.append( System.lineSeparator() );
        
        return builder;
    }
    
    private static void toString( StringBuilder builder , String name , Collection<? extends Declaration> declarations , String connector )
    {
        if( declarations.isEmpty() )
        {
            return ;
        }
        
        //builder.append( System.lineSeparator() );
        builder.append( "  " );
        builder.append( name );
        builder.append( System.lineSeparator() );
                
        for( Declaration d : declarations )
        {
            builder.append( "    " );
            builder.append( d.getName() );
            builder.append( " " );
            builder.append( connector );
            builder.append( " " );
            builder.append( d.getValue() );
            builder.append( ";" );
            builder.append( System.lineSeparator() );
        }
        
        //builder.append( System.lineSeparator() );
    }
    
}
