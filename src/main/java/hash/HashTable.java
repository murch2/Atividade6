package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import modelo.Classe;
import modelo.Condicao;
import modelo.Decisao;
import modelo.Metodo;
import modelo.TodasMCDC;

public class HashTable {
	
	/**
	 * Cria um hash com as informações presentes em todasMCDC
	 * @param todasMCDC todasMCDC que será lida pra criação do hash
	 */
	public static Map<String, Map<String, Vector<Boolean>>> criaHashTable (TodasMCDC todasMCDC) {
		Map<String, Map<String, Vector<Boolean>>> hashRequisitos = new HashMap<String, Map<String,Vector<Boolean>>>(); 
		String nomeMetodo; 
		
		for (Classe classe : todasMCDC.getClasses()) {  
			for (Metodo metodo : classe.getMetodos()) { 
				nomeMetodo = metodo.getMetodo() + "."; 
				for (Decisao decisao : metodo.getDecisoes()) {
					String chave = nomeMetodo + decisao.getCodigo(); 
					if (hashRequisitos.containsKey(chave)) {
						Vector<Condicao> condicoes = new Vector<Condicao>();
						getCondicoesDaDecisao(decisao, condicoes); 
						
						Map<String, Vector<Boolean>> hashNivelDois = hashRequisitos.get(chave); 
						
						for (Condicao cond : condicoes) {
							hashNivelDois.get(cond.getCodigo().trim()).add(cond.getValor()); 
						}
					} 
					else {
						Vector<Condicao> condicoes = new Vector<Condicao>();
						getCondicoesDaDecisao(decisao, condicoes); 
						
						Map<String, Vector<Boolean>> hashNivelDois = new HashMap<String, Vector<Boolean>>(); 
						
						for (Condicao cond : condicoes) {
							Vector<Boolean> vetor = new Vector<Boolean>();
							vetor.add(cond.getValor()); 
							hashNivelDois.put(cond.getCodigo().trim(), vetor); 
						}
						
						hashRequisitos.put(chave, hashNivelDois);
					}
				}
			}
		}
		return hashRequisitos;
	}
	
	/**
	 * Função auxiliar que imprime o que tem no hash
	 * @param hash O hash a ser impresso
	 */
	public static void printaHash (Map<String, Map<String, Vector<Boolean>>> hash) {
		Set<String> strings = hash.keySet(); 
		
		for (String string : strings) {
			System.out.println("Chave = " + string);
			System.out.println("---Valor : ");
			System.out.println(hash.get(string));
		}
	}
	
	/**
	 * Dada uma decisão retorna as condições presentes. 
	 * @param decisao A decisão a ser analisada 
	 * @param condicoes As condições presentes na decisão 
	 */
	
	public static void getCondicoesDaDecisao (Decisao decisao, Vector<Condicao> condicoes) {
		if (decisao.getCondicao() != null) {
			condicoes.add(decisao.getCondicao()); 
		} else {
			getCondicoesDaDecisao(decisao.getDecisaoDireita(), condicoes); 
			getCondicoesDaDecisao(decisao.getDecisaoEsquerda(), condicoes); 
		}
	}

	/**
	 * Dados dois hashes, um de requisitos e outro de valores executados pela malha de testes devolve um terceiro hash com que cada decisão cobriu o MCDC
	 * @param hashRequisitos hash que representa os valores necessários para a cobertura do MCDC
	 * @param hashExecutados hash que representa os valores executados pela malha de testes
	 * @return um hash <String, double> onde as chaves são as decisões e o valor double é a procentagem que foi coberta do MCDC
	 */
	public static Map<String, Double> comparaHashTables (Map<String, Map<String, Vector<Boolean>>> hashRequisitos,
														 Map<String, Map<String, Vector<Boolean>>> hashExecutados) {
		
		Map<String, Double> result = new HashMap<String, Double>(); 
		
		Set<String> chavesNivel1 = hashRequisitos.keySet();
		
		for (String chave1 : chavesNivel1) {
			Map<String, Vector<Boolean>> hashNivelDoisRequisitos = hashRequisitos.get(chave1);
			Map<String, Vector<Boolean>> hashNivelDoisExecutados = hashExecutados.get(chave1);
			
			Set<String> chavesNivel2 = hashNivelDoisRequisitos.keySet();
			
			//Ver se da pra mudar depois
			
			String chave2 = ""; 
			for (String string : chavesNivel2) {
				chave2 = string; 
				break; 
			}
			
			
			int contador = 0; 
			int sizeRequisitos = hashNivelDoisRequisitos.get(chave2).size();  
			
			for (int i = 0; i < sizeRequisitos; i++) {
				
				int sizeExecutados = hashNivelDoisExecutados.get(chave2).size();  
				for (int j = 0; j < sizeExecutados; j++) {					
					
					boolean testeOK = true; 
					for (String string : chavesNivel2) {
						boolean valorRequisito = hashNivelDoisRequisitos.get(string).elementAt(i);
						boolean valorExecutado = hashNivelDoisExecutados.get(string).elementAt(j);
						
						if (valorRequisito != valorExecutado) {
							testeOK = false; 
							break; 
						}
					}
			
					if (testeOK) {
						contador++;  
						break; 
					}
				} 
			}
			//Isso aqui eh a procentagem de cada decisão 
			double porcentagem = (double) contador / hashNivelDoisRequisitos.get(chave2).size();
			result.put(chave1, porcentagem); 
		}
		return result; 
	}
	
	
	//TODO escrever javaDOC disso e talvez passar a chave 1 e 2 por argumento já. 
	public Map<String, Map<String, Vector<Boolean>>> preencheHash (Map<String, Map<String, Vector<Boolean>>> hash, String classe, String metodo, 
			String decisao, String condicao, boolean valor) {
		
		String chave1 = classe + metodo + decisao;
		String chave2 = condicao; 
		
		if (hash.containsKey(chave1)) {
			if (hash.get(chave1).containsKey(chave2)) {
				hash.get(chave1).get(chave2).add(valor); 
			} 
			else {
				Vector<Boolean> vetor = new Vector<Boolean>(); 
				hash.get(chave1).put(chave2, vetor); 
			}
		} 
		else {
			Map<String, Vector<Boolean>> hashNivel2 = new HashMap<String, Vector<Boolean>>();
			Vector<Boolean> vetor = new Vector<Boolean>(); 
			vetor.add(valor); 
			hashNivel2.put(chave2, vetor);
			hash.put(chave1, hashNivel2); 
		}
		return hash; 
	}
	
	
	
}







