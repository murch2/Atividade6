package parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import modelo.Classe;
import modelo.Metodo;
import modelo.TodasMCDC;

import org.junit.Test;

public class LeituraXMLTest {

	@Test
	public void getRequisitosMCDCTeste() throws IOException {
		//To fazendo esse teste para um arquivo especifico então acho que não está bom. 
		
		LeituraXML leitor = new LeituraXML(); 
		String path = LeituraXMLTest.class.getResource("../MCDC.xml").toString();
		TodasMCDC todasMCDC = leitor.getRequisitosMCDC(path.substring(5));
		List<Classe> classes = todasMCDC.getClasses(); 
		assertEquals(classes.size(), 3);
		
		List<Metodo> metodos1 = classes.get(0).getMetodos(); 
		List<Metodo> metodos2 = classes.get(1).getMetodos(); 
		List<Metodo> metodos3 = classes.get(2).getMetodos(); 
		
		assertEquals(metodos1.size(), 2);
		assertEquals(metodos2.size(), 2);
		assertEquals(metodos3.size(), 2);
		
		//TODO fixar um arquivo XML especifico e fazer mais testes pra ver se está funcionando. 
		
	}
	
}
