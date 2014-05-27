package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import modelo.Decisao;
import modelo.TodasMCDC;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class LeituraXML {
	

	/** Cria um objeto TodasMCDC a partir de um arquivo XML
	 * @param path Caminho do arquivo XML
	 * @return objeto TodasMCDC com os requisitos de teste para a cobertura do MCDC
	 */
	
	public TodasMCDC getRequisitosMCDC (String path) throws IOException {

		XStream xStream = new XStream(new DomDriver());
		File file = new File(path);
		FileInputStream xmlFile = new FileInputStream(file);
 
		//"Alias" feitos na geração do arquivo precisam ser feitos agora também. 
		xStream.alias("TodasMCDC", TodasMCDC.class);		
		xStream.alias("decision", Decisao.class);
		
		TodasMCDC todasMCDC = (TodasMCDC) xStream.fromXML(xmlFile); 
		
		return todasMCDC; 
	}
}
