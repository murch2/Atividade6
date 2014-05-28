package hash;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

public class HashTableTeste {
	private static final double DELTA = 1e-15;
	
	@Test
	public void comparaHashTablesTesteCemPorCento() {
		Map<String, Map<String, Vector<Boolean>>> hash1 = new HashMap<String, Map<String, Vector<Boolean>>>();
		Map<String, Map<String, Vector<Boolean>>> hash2 = new HashMap<String, Map<String, Vector<Boolean>>>();
		
		criaHashesCemPorCento(hash1, hash2); 
		
		Map<String, Double> hashPorcentagens = HashTable.comparaHashTables(hash1, hash2);
		Set<String> chaves = hashPorcentagens.keySet(); 
		
		for (String chave : chaves) {
			assertEquals(hashPorcentagens.get(chave).doubleValue(), 1.0, DELTA);
		}
	}
	
	@Test
	public void comparaHashTablesTesteOitentaPorCento() {
		
	}
	
	@Test
	public void comparaHashTablesTesteSessentaPorCento() {
		
	}
	
	@Test
	public void comparaHashTablesTesteZeroPorCento() {
		
	}
	
	@Test
	public void preencheHashTeste () {
		HashTable ht = new HashTable(); 
		HashMap<String, Map<String, Vector<Boolean>>> hash = new HashMap<String, Map<String, Vector<Boolean>>>();
		
		ht.preencheHash(hash, "ExemploDeClasse.", "Metodo.", "decisao", "a > 0", true);
		assertEquals(hash.get("ExemploDeClasse.Metodo.decisao").get("a > 0").get(0), true);
		
		ht.preencheHash(hash, "ExemploDeClasse.", "Metodo.", "decisao", "a > 0", false);
		assertEquals(hash.get("ExemploDeClasse.Metodo.decisao").get("a > 0").get(1), false);
		
		ht.preencheHash(hash, "ExemploDeClasse.", "Metodo.", "decisao", "a > 0", false);
		assertEquals(hash.get("ExemploDeClasse.Metodo.decisao").get("a > 0").get(2), false);
		
		ht.preencheHash(hash, "ExemploDeClasse.", "Metodo.", "decisao", "a > 0", true);
		assertEquals(hash.get("ExemploDeClasse.Metodo.decisao").get("a > 0").get(3), true);
		
		ht.preencheHash(hash, "ExemploDeClasse2.", "Metodo1.", "decisao", "cond1", false);
		assertEquals(hash.get("ExemploDeClasse2.Metodo1.decisao").get("cond1").get(0), false);
		
		ht.preencheHash(hash, "ExemploDeClasse2.", "Metodo2.", "decisao", "cond2", false);
		assertEquals(hash.get("ExemploDeClasse2.Metodo2.decisao").get("cond2").get(0), false);
		
		ht.preencheHash(hash, "ExemploDeClasse2.", "Metodo3.", "decisao", "cond3", true);
		assertEquals(hash.get("ExemploDeClasse2.Metodo3.decisao").get("cond3").get(0), true);
		
		ht.preencheHash(hash, "ExemploDeClasse2.", "Metodo4.", "decisao", "cond3", true);
		assertEquals(hash.get("ExemploDeClasse2.Metodo4.decisao").get("cond3").get(0), true);
		
	}

	//Por enquanto tá só com uma decisão. 
	public void criaHashesCemPorCento (Map<String, Map<String, Vector<Boolean>>> hash1, Map<String, Map<String, Vector<Boolean>>> hash2 ) {
		
		Map<String, Vector<Boolean>> hashNivel1 = new HashMap<String, Vector<Boolean>>(); 
		Map<String, Vector<Boolean>> hashNivel2 = new HashMap<String, Vector<Boolean>>(); 
		
		Vector<Boolean> vetor = new Vector<Boolean>(); 
		vetor.add(true);
		vetor.add(false);
		vetor.add(true);
		vetor.add(false);
		vetor.add(true);
		
		hashNivel1.put("Primeira Chave", vetor);
		hashNivel2.put("Primeira Chave", vetor);
		
		Vector<Boolean> vetor2 = new Vector<Boolean>(); 
		vetor2.add(false);
		vetor2.add(false);
		vetor2.add(false);
		vetor2.add(true);
		vetor2.add(true);
		
		hashNivel1.put("Segunda Chave", vetor2);
		hashNivel2.put("Segunda Chave", vetor2);
		
		Vector<Boolean> vetor3 = new Vector<Boolean>();
		vetor3.add(true);
		vetor3.add(true);
		vetor3.add(false);
		vetor3.add(true);
		vetor3.add(false);
		
		hashNivel1.put("Terceira Chave", vetor3);
		hashNivel2.put("Terceira Chave", vetor3);

		hash1.put("Chave1", hashNivel1); 
		hash2.put("Chave1", hashNivel2); 
		
	}
	
	public void criaHashesOitentaPorCento () {
		
	}
	
	public void criaHashesSessentaPorCento () {
		
	}
	
	public void criaHashesZeroPorCento () {
		
	}
	
}









