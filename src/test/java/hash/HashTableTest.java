package hash;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest {

	@Test
	public void setHashExecutadosTest() {
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao1", true);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao1", false);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao2", true);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse2.", "Metodo1.", "Decisao.", "condicao1", false);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao1", 0), true);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao1", 1), false);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao.", "condicao2", 0), true);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse2.", "Metodo1.", "Decisao.", "condicao1", 0), false);
	}

}
