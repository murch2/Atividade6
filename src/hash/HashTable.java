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
							hashNivelDois.get(cond.getCodigo()).add(cond.getValor()); 
						}
					} 
					else {
						Vector<Condicao> condicoes = new Vector<Condicao>();
						getCondicoesDaDecisao(decisao, condicoes); 
						
						Map<String, Vector<Boolean>> hashNivelDois = new HashMap<String, Vector<Boolean>>(); 
						
						for (Condicao cond : condicoes) {
							Vector<Boolean> vetor = new Vector<Boolean>();
							vetor.add(cond.getValor()); 
							hashNivelDois.put(cond.getCodigo(), vetor); 
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
	public void printaHash (Map<String, Map<String, Vector<Boolean>>> hash) {
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
}
