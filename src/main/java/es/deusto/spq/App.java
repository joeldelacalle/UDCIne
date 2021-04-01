package es.deusto.spq;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Hello world!
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
