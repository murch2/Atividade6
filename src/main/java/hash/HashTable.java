package hash;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import parser.LeituraXML;
import modelo.Classe;
import modelo.Condicao;
import modelo.Decisao;
import modelo.Metodo;
import modelo.TodasMCDC;

public class HashTable {

	private static HashTable singleton = null;
	private Map<String, Map<String, Vector<Boolean>>> hashExecutados; 
	private Map<String, Map<String, Vector<Boolean>>> hashRequisitos;

	/**
	 * 
	 * @return a instância singleton da classe HashTable
	 */
	public static HashTable getInstance() {
		if (HashTable.singleton == null){
			HashTable.singleton = new HashTable();
		}
		return HashTable.singleton;
	}

	public  Map<String, Map<String, Vector<Boolean>>> getHashRequisitos () {
		return this.hashRequisitos; 
	}

	public  Map<String, Map<String, Vector<Boolean>>> getHashExecutados () {
		return this.hashExecutados; 
	}

	private HashTable () {
		LeituraXML leitor = new LeituraXML();
		String path = HashTable.class.getResource("../MCDC.xml").toString();
		TodasMCDC todasMCDC = null; 
		this.hashRequisitos = null;

		try {
			todasMCDC = leitor.getRequisitosMCDC(path.substring(5));
			this.hashRequisitos = criaHashTable(todasMCDC);
			this.hashExecutados = new HashMap<String, Map<String, Vector<Boolean>>>(); 
		} catch (IOException e) {
			System.err.println("Falha ao abrir arquivo XML que contém os requisitos para a cobertura do MCDC");
			System.exit(-1); 
		}	
	}

	/**
	 * Cria um hash com as informações presentes em todasMCDC
	 * @param todasMCDC todasMCDC que será lida pra criação do hash
	 */
	public Map<String, Map<String, Vector<Boolean>>> criaHashTable (TodasMCDC todasMCDC) {
		Map<String, Map<String, Vector<Boolean>>> hashRequisitos = new HashMap<String, Map<String,Vector<Boolean>>>(); 
		String nomeMetodo; 

		for (Classe classe : todasMCDC.getClasses()) {  
			for (Metodo metodo : classe.getMetodos()) { 
				nomeMetodo = metodo.getMetodo() + "."; 
				for (Decisao decisao : metodo.getDecisoes()) {
					String chave = nomeMetodo + decisao.getCodigo();
					String[] partes = chave.split("\\.");
					int n = partes.length; 

					chave = partes[n-3] + "." + partes[n-2] + "." + partes[n-1]; 		
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

	public void getCondicoesDaDecisao (Decisao decisao, Vector<Condicao> condicoes) {
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
	public Map<String, Double> comparaHashTables (Map<String, Map<String, Vector<Boolean>>> hashRequisitos,
			Map<String, Map<String, Vector<Boolean>>> hashExecutados) {

		Map<String, Double> result = new HashMap<String, Double>(); 

		Set<String> chavesNivel1 = hashRequisitos.keySet();

		int contador, sizeRequisitos, sizeExecutados; 
		boolean valorRequisito, valorExecutado; 
		double porcentagem; 

		for (String chave1 : chavesNivel1) {

			Map<String, Vector<Boolean>> hashNivelDoisRequisitos = hashRequisitos.get(chave1);
			Map<String, Vector<Boolean>> hashNivelDoisExecutados = hashExecutados.get(chave1);

			Set<String> chavesNivel2 = hashNivelDoisRequisitos.keySet();

			String chave2 = ""; 
			for (String string : chavesNivel2) {
				chave2 = string; 
				break; 
			}

			contador = 0; 
			sizeRequisitos = hashNivelDoisRequisitos.get(chave2).size();  

			for (int i = 0; i < sizeRequisitos; i++) {
				sizeExecutados = hashNivelDoisExecutados.get(chave2).size();
				for (int j = 0; j < sizeExecutados; j++) {					
					boolean testeOK = true; 

					for (String string : chavesNivel2) {
						valorRequisito = hashNivelDoisRequisitos.get(string).elementAt(i);
						valorExecutado = hashNivelDoisExecutados.get(string).elementAt(j);

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
			porcentagem = (double) contador / hashNivelDoisRequisitos.get(chave2).size();
			result.put(chave1, porcentagem); 
		}
		return result; 
	}

	/**
	 * Método que coloca uma entrada no hashExecutados
	 * @param classe a classe da decisão
	 * @param metodo o método da decisão
	 * @param decisao a decisão inteira
	 * @param condicao a condição a ser valorada
	 * @param valor o valor da condição
	 */
	public void setHashExecutados (String classe, String metodo, String decisao, String condicao, boolean valor) {

		String chave1 = classe + metodo + decisao;
		String chave2 = condicao; 

		if (this.hashExecutados.containsKey(chave1)) {
			if (this.hashExecutados.get(chave1).containsKey(chave2)) {
				this.hashExecutados.get(chave1).get(chave2).add(valor); 
			} 
			else {
				Vector<Boolean> vetor = new Vector<Boolean>();
				vetor.add(valor); 
				this.hashExecutados.get(chave1).put(chave2, vetor); 
			}
		} 
		else {
			Map<String, Vector<Boolean>> hashNivel2 = new HashMap<String, Vector<Boolean>>();
			Vector<Boolean> vetor = new Vector<Boolean>(); 
			vetor.add(valor); 
			hashNivel2.put(chave2, vetor);
			this.hashExecutados.put(chave1, hashNivel2); 
		}
	}

	/**
	 * Devolve o valor de uma execução do hashExecutados
	 * @param classe a classe da decisão
	 * @param metodo o método da decisão
	 * @param decisao a decisão inteira
	 * @param condicao a condição a ser valorada
	 * @param index o indice (numero do teste) a ser retornado
	 * @return
	 */
	
	public boolean getHashExecutados (String classe, String metodo, String decisao, String condicao, int index) {
		String chave1 = classe + metodo + decisao;
		String chave2 = condicao;
		return this.hashExecutados.get(chave1).get(chave2).get(index);
	}

	
	/**
	 * Executa a saída imprimindo na tela as porcentagens de cobertura do MCDC
	 * @param json O json com as informações necessárias para a impressão
	 */
	@SuppressWarnings("unchecked")
	public void imprimePorcentagens (JSONObject json) {
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

	/**
	 * Formatação da String a ser printada na tela
	 * @param prefix Primeira coluna da string
	 * @param value Segunda coluna da string
	 * @return uma string formatada
	 */
	private String print(String prefix, String value){
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

	/**
	 * Monta o json ainda sem as porcentagens corretas
	 * @param hashPorcentagens o hash com as porcentagens cobertas para cada decisão do MCDC
	 * @return jsonObject com porcentagens ainda não acertadas para classe e metodos
	 */
	public JSONObject montaJsonObject (Map<String, Double> hashPorcentagens) {
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

	/**
	 * Um jsonObject pronto para ser printado na tela
	 * @param json o json com as informações do hashExecutados
	 * @return jsonObject com as porcentagens acertadas (classe, metodo e decisão)
	 */
	@SuppressWarnings("unchecked")
	public JSONObject acertaPorcentagens (JSONObject json) {
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