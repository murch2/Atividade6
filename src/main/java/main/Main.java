package main;

import hash.HashTable;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
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
		
		imprimePorcentagens(hashPorcentagens);
	}
	
	//TODO Continuar esse método que imprimirá as coisas no console mesmo.
	
	//O algoritmo desse metodo eh realmente ruim. 
	@SuppressWarnings("unchecked")
	public static void imprimePorcentagens (Map<String, Double> hashPorcentagens) {
		JSONObject result = new JSONObject(); 
		
		Set<String> chaves = hashPorcentagens.keySet();
		
		//Montando o Json que será impresso. (colocar isso em outro metodo. 
		for (String chave : chaves) {
			String[] partes = chave.split("\\.");
			int n = partes.length; 
			
			String decisao = partes[n-1];
			String metodo = partes[n-2];
			String classe = partes[n-3];
		
			if (!result.has(classe)) {
				result.put(classe, new JSONObject()); 
			}
			
			if (!((JSONObject)result.get(classe)).has(metodo)) {
				((JSONObject)result.get(classe)).put(metodo, new JSONObject()); 
			}
			
			//Achei uma decisão e pendura no lugar certo
			((JSONObject)((JSONObject)result.get(classe)).get(metodo)).put(decisao, hashPorcentagens.get(chave)); 
		}
		
		Set<String> chaveClasses = result.keySet();
		
//		for (String chaveClasse : chaveClasses) {
//			Set<String> chaveMetodos = result.getJSONObject(chaveClasse).keySet();
//			
//			for (String string : chaveMetodos) {
//				
//			}
//		}
	
	}
}
