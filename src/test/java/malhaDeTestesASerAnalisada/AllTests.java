/**
 * @author Rodrigo Duarte Louro
 *
 * May 31, 2014
 */
package malhaDeTestesASerAnalisada;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExemploClasseDoisTeste.class, ExemploClasseTresTeste.class,
		ExemploClasseUmTeste.class })

public class AllTests {
	
	@Before
	public void iniciaHash () {
		System.out.println("Antes de executar os testes");
	}
	
	@After
	public void comparaHash () {
//		Isso aqui tem que ser chamado quando se acaba a execução dos testes. 
//		Map<String, Double> hashPorcentagens = HashTable.getInstance().comparaHashTables(hashRequisitos, hashExecutados);
//
//		HashTable.getInstance().imprimePorcentagens(HashTable.getInstance().montaJsonObject(hashPorcentagens));
		System.out.println("Depois de executar os testes");
	}
	
}
