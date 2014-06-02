package malhaDeTestesASerAnalisada;

import org.junit.Before;
import org.junit.Test;

import analisar.ExemploDeClasseDois;

public class ExemploClasseDoisTeste {
	
	@Test
	public void funcaoTest() {
		ExemploDeClasseDois.funcao(3, 0, 0);
		ExemploDeClasseDois.funcao(-1, 0, 0);
		ExemploDeClasseDois.funcao(3, 1, 0);
		ExemploDeClasseDois.funcao(3, 0, 2);
	}

}
