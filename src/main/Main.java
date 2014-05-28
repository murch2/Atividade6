package main;

import hash.HashTable;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

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
	public static void imprimePorcentagens (Map<String, Double> hashPorcentagens) {
		Set<String> chaves = hashPorcentagens.keySet(); 
		
		for (String string : chaves) {
			String[] partes = string.split("\\.");
			int n = partes.length; 
			
			String decisao = partes[n-1];
			String metodo = partes[n-2];
			String classe = partes[n-3];
			String pack; 
			if (n >= 4)
				pack = partes[n-4]; 

			
		}
	}
}
