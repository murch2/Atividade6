package malhaDeTestesASerAnalisada;

import org.junit.Test;

import analisar.ExemploDeClasseTres;

public class ExemploClasseTresTeste {

	
	@Test
	//Nunca vai ser sem por cento (Acho que 60%)
	public void funcaoTest() {
		ExemploDeClasseTres.funcao(5, 0, 0);
		ExemploDeClasseTres.funcao(-2, 0, 1);
		ExemploDeClasseTres.funcao(1, 0, 1);
		ExemploDeClasseTres.funcao(2, 0, 1);
		ExemploDeClasseTres.funcao(5, 0, 1);
	}
	
	@Test
	//Isso cobre 100% do MCDC
	public void metodoDeTesteTest() {
		ExemploDeClasseTres.metodoDeTeste(true);
		ExemploDeClasseTres.metodoDeTeste(false);
	}

}

