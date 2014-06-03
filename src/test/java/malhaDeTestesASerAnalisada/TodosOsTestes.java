package malhaDeTestesASerAnalisada;


import java.util.Map;

import hash.HashTable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ExemploClasseDoisTeste.class, ExemploClasseUmTeste.class, ExemploClasseTresTeste.class })

public class TodosOsTestes {
	
	/**
	 * Cria o hashRequisitos. 
	 */
	@BeforeClass public static void iniciaHash () {
		HashTable.getInstance(); 
	}
	
	/**
	 * Faz imprimir as informações na tela. 
	 */
	@AfterClass public static void comparaHash () {
		Map<String, Double> hashP = HashTable.getInstance().comparaHashTables(HashTable.getInstance().getHashRequisitos(),
				HashTable.getInstance().getHashExecutados());
		
		HashTable.getInstance().imprimePorcentagens(HashTable.getInstance().montaJsonObject(hashP)); 
	}
	
}
