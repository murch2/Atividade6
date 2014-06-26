package hash;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;
import org.junit.Test;

public class HashTest {

	private final double DELTA = 0.00001; 
	
	@Test
	public void setAndGetHashExecutadosTest() {
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao1", true);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao1", false);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao2", true);
		HashTable.getInstance().setHashExecutados("ExemploDeClasse2.", "Metodo1.", "Decisao", "condicao1", false);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao1", 0), true);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao1", 1), false);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse1.", "Metodo1.", "Decisao", "condicao2", 0), true);
		
		assertEquals(HashTable.getInstance().getHashExecutados
				("ExemploDeClasse2.", "Metodo1.", "Decisao", "condicao1", 0), false);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	
	//Depois se der tempo fazer mais testes 
	public void comparaHashTablesTest() {
		Map<String, Map<String, Vector<Boolean>>> hash1 = new HashMap<String, Map<String, Vector<Boolean>>>();
		Map<String, Map<String, Vector<Boolean>>> hash2 = new HashMap<String, Map<String, Vector<Boolean>>>();

		Map<String, Vector<Boolean>> hashNivelDois1 = new HashMap<String, Vector<Boolean>>();
		Map<String, Vector<Boolean>> hashNivelDois2 = new HashMap<String, Vector<Boolean>>();

		Vector<Boolean> vetor1 = new Vector<Boolean>(); 
		Vector<Boolean> vetor2 = new Vector<Boolean>(); 
		
		vetor1.add(true); 
		vetor1.add(false); 
		vetor1.add(true); 
		vetor1.add(false); 
		vetor1.add(true); 
		
		
		vetor2 = (Vector<Boolean>) vetor1.clone(); 
		
		hashNivelDois1.put("condicao1", vetor1); 
		hashNivelDois2.put("condicao1", vetor2); 
		
		vetor1 = new Vector<Boolean>(); 
		vetor2 = new Vector<Boolean>();
		
		vetor1.add(false);
		vetor1.add(true);
		vetor1.add(false);
		vetor1.add(true);
		vetor1.add(false);
		
		vetor2 = (Vector<Boolean>) vetor1.clone();
		
		hashNivelDois1.put("condicao2", vetor1); 
		hashNivelDois2.put("condicao2", vetor2);
		
		hash1.put("Chave1", hashNivelDois1); 
		hash2.put("Chave1", hashNivelDois2);
		
		Map<String, Double> hashPorcentagens = HashTable.getInstance().comparaHashTables(hash1, hash2);
		
		Set<String> chaves = hashPorcentagens.keySet(); 
		
		for (String chave : chaves) {
			assertEquals(hashPorcentagens.get(chave), 1.0, DELTA);
		}
	
	}
	
	@Test
	public void montaJsonObjectTest () {
		Map<String, Double> hash = new HashMap<String, Double>();
		
		hash.put("ExemploDeClasseDois.funcao.a > 0 && b == 0 && c == 0", (double) 1.0);
		hash.put("ExemploDeClasseUm.Metodo2.a > b || b > 3", (double) 1.0);
		hash.put("ExemploDeClasseUm.Metodo1.a > 0 && b < 5", (double) 1.0);
		hash.put("ExemploDeClasseTres.funcao.(a > 0 && b == 0  && c != 0)  ||  a < - 5", (double) 0.4);
		hash.put("ExemploDeClasseDois.metodoQualquer.a > 0 || b == 0 || c == 0", (double) 1.0);
		hash.put("ExemploDeClasseTres.metodoDeTeste.a", (double) 1.0);
		
		JSONObject json = HashTable.getInstance().montaJsonObject(hash); 
		
		assertEquals(json.getJSONObject("ExemploDeClasseDois").get("porcentagemClasse"), 1.0);
		assertEquals(json.getJSONObject("ExemploDeClasseTres").get("porcentagemClasse"), 0.7);
		assertEquals(json.getJSONObject("ExemploDeClasseUm").get("porcentagemClasse"), 1.0);
		
		assertEquals(json.getJSONObject("ExemploDeClasseDois").getJSONObject("funcao").get("porcentagemMetodo"), 1.0);
		assertEquals(json.getJSONObject("ExemploDeClasseDois").getJSONObject("metodoQualquer").get("porcentagemMetodo"), 1.0);
		assertEquals(json.getJSONObject("ExemploDeClasseTres").getJSONObject("funcao").get("porcentagemMetodo"), 0.4);
		assertEquals(json.getJSONObject("ExemploDeClasseTres").getJSONObject("metodoDeTeste").get("porcentagemMetodo"), 1.0);
		
	}	
}
