package malhaDeTestesASerAnalisada;

import java.util.Map;

import hash.HashTable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ExemploClasseDoisTeste.class})

public class AllTests {
	
	@BeforeClass public static void iniciaHash () {
		HashTable.getInstance(); 
	}
	
	
//TODO criar TODAs os testes de TODAS as classes. (É assumido no compara hashes que pelo menos um teste por metodo foi
//	executado. 
	@AfterClass public static void comparaHash () {
		System.out.println("After");
//		Map<String, Double> hashP = HashTable.getInstance().comparaHashTables(HashTable.getInstance().getHashRequisitos(),
//				HashTable.getInstance().getHashExecutados());
//		
////TODO Encapsular esse montaJsonObject
//		HashTable.getInstance().imprimePorcentagens(HashTable.getInstance().montaJsonObject(hashP)); 
	}
	
}
