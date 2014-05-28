/**
 * @author Rodrigo Duarte Louro
 *
 * May 27, 2014
 */
package malhaDeTestes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExemploClasseDoisTeste.class, ExemploClasseTresTeste.class,
		ExemploClasseUmTeste.class })
public class AllTests {

}
