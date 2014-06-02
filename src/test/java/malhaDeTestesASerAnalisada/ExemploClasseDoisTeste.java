package malhaDeTestesASerAnalisada;

import org.junit.Test;

import analisar.ExemploDeClasseDois;

public class ExemploClasseDoisTeste {
	
	@Test
	//Isso cobre 100% do MCDC (para este método)
	public void funcaoTest() {
		ExemploDeClasseDois.funcao(3, 0, 0);
		ExemploDeClasseDois.funcao(-1, 0, 0);
		ExemploDeClasseDois.funcao(3, 1, 0);
		ExemploDeClasseDois.funcao(3, 0, 2);
	}
	
	@Test
	//Isso cobre 100% do MCDC (para este método)
	public void metodoQualquerTest() {
		ExemploDeClasseDois.metodoQualquer(-5, 1, 1);
		ExemploDeClasseDois.metodoQualquer(5, 1, 1);
		ExemploDeClasseDois.metodoQualquer(-1, 0, 1);
		ExemploDeClasseDois.metodoQualquer(-2, 1, 0);
	}
}
