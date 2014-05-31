package main;

import hash.HashTable;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import modelo.TodasMCDC;
import parser.LeituraXML;

public class Main {	
	public static void main(String[] args) {	
		LeituraXML leitor = new LeituraXML();
		String path = Main.class.getResource("../MCDC.xml").toString();
		TodasMCDC todasMCDC = null; 
		try {
			todasMCDC = leitor.getRequisitosMCDC(path.substring(5));
		} catch (IOException e) {
			System.err.println("Falha ao abrir arquivo XML que contém os requisitos para a cobertura do MCDC");
			System.exit(-1); 
		}
		Map<String, Map<String, Vector<Boolean>>> hashRequisitos = HashTable.criaHashTable(todasMCDC);
		Map<String, Map<String, Vector<Boolean>>> hashExecutados = HashTable.criaHashTable(todasMCDC);

		Map<String, Double> hashPorcentagens = HashTable.comparaHashTables(hashRequisitos, hashExecutados);

		imprimePorcentagens(montaJsonObject(hashPorcentagens));
	}
	
	@SuppressWarnings("unchecked")
	public static void imprimePorcentagens (JSONObject json) {
		
		Set<String> chavesClasse = json.keySet(); 
		
		for (String chaveClasse : chavesClasse) {
			
			JSONObject metodosJson = json.getJSONObject(chaveClasse);
			System.out.println(print("-> Classe : " + chaveClasse, metodosJson.getDouble("porcentagemClasse") * 100 + "%"));
			Set<String> chavesMetodos = metodosJson.keySet(); 
			
			for (String chaveMetodo : chavesMetodos) {
				if (chaveMetodo.equals("porcentagemClasse")) {
					continue; 
				}
				JSONObject decisoesJson = metodosJson.getJSONObject(chaveMetodo);
				System.out.println(print("--> Método : " + chaveMetodo, decisoesJson.getDouble("porcentagemMetodo") * 100 + "%"));
				
				Set<String> chavesDecisoes = decisoesJson.keySet(); 
				
				for (String chaveDecisao : chavesDecisoes) {
					if (chaveDecisao.equals("porcentagemMetodo")) {
						continue; 
					}
					System.out.println(print("---> Decisão : " + chaveDecisao, decisoesJson.getDouble(chaveDecisao) * 100 + "%"));
				}
			}
		}
	}
	
	private static String print(String prefix, String value){
	    StringBuilder buff = new StringBuilder();

	    int length = prefix.length();

	    int mLength = 75;

	    buff.append(prefix);

	    while(length <= mLength){
	        buff.append(" ");
	        length++;
	    }

	    buff.append(value);

	    return buff.toString();
	}

	public static JSONObject montaJsonObject (Map<String, Double> hashPorcentagens) {
		JSONObject json = new JSONObject(); 

		Set<String> chaves = hashPorcentagens.keySet();

		for (String chave : chaves) {
			String[] partes = chave.split("\\.");
			int n = partes.length; 

			String decisao = partes[n-1];
			String metodo = partes[n-2];
			String classe = partes[n-3];

			if (!json.has(classe)) {
				json.put(classe, new JSONObject()); 
			}

			if (!((JSONObject)json.get(classe)).has(metodo)) {
				json.getJSONObject(classe).put(metodo, new JSONObject()); 
			}

			json.getJSONObject(classe).getJSONObject(metodo).put(decisao, hashPorcentagens.get(chave)); 
		}
		
		return acertaPorcentagens(json); 

	}

	@SuppressWarnings("unchecked")
	public static JSONObject acertaPorcentagens (JSONObject json) {
		Set<String> chaveClasses = json.keySet();

		double somaMetodo, somaClasse, porcentagem;  
		int nDecisoesClasse; 

		for (String chaveClasse : chaveClasses) {
			Set<String> chaveMetodos = json.getJSONObject(chaveClasse).keySet();

			somaClasse = 0; 
			nDecisoesClasse = 0; 

			for (String chaveMetodo : chaveMetodos) {
				Set<String> chaveDecisoes = json.getJSONObject(chaveClasse).getJSONObject(chaveMetodo).keySet(); 		

				nDecisoesClasse += chaveDecisoes.size(); 
				somaMetodo = 0; 

				for (String chaveDecisao : chaveDecisoes) {
					porcentagem = json.getJSONObject(chaveClasse).getJSONObject(chaveMetodo).getDouble(chaveDecisao);
					somaMetodo += porcentagem; 
					somaClasse += porcentagem;
				}

				json.getJSONObject(chaveClasse).getJSONObject(chaveMetodo).put("porcentagemMetodo", somaMetodo / chaveDecisoes.size()); 

			}
			porcentagem = somaClasse / nDecisoesClasse; 
			json.getJSONObject(chaveClasse).put("porcentagemClasse", porcentagem); 
		}

		return json; 
	}
}
