package main;

import hash.HashTable;
import java.io.IOException;
import java.util.Map;
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
	
		HashTable.comparaHashTables(hashRequisitos, hashExecutados);
	}
}
