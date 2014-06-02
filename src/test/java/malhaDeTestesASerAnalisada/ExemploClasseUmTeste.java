package malhaDeTestesASerAnalisada;

import org.junit.Test;

import analisar.ExemploDeClasseUm;

public class ExemploClasseUmTeste {

	@Test
	//Isso cobre 100 % do MCDC
	public void Metodo1Test() {
		ExemploDeClasseUm.Metodo1(5, 2);
		ExemploDeClasseUm.Metodo1(-1, 2);
		ExemploDeClasseUm.Metodo1(5, 10);
            
	}
	
	@Test
	//Isso cobre 100% o MCDC
	public void Metodo2Test() {
		ExemploDeClasseUm.Metodo2(1, 2);
		ExemploDeClasseUm.Metodo2(5, 2);
		ExemploDeClasseUm.Metodo2(2, 5);
	}

}
