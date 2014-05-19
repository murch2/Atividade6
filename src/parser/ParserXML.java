package parser;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import modelo.Classe;
import modelo.Decisao;
import modelo.TodasMCDC;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class ParserXML {
	public static void main(String argv[]) throws IOException {
		ParserXML.lerXML();
	}
	
	public static void lerXML() throws IOException {

		XStream xStream = new XStream(new DomDriver());

		/** Lendo o arquivo em disco **/
		File file = new File("/home/digao/workspace/Atividade6/MCDC.xml");
		FileInputStream input = new FileInputStream(file);

		//Aqui vai a classe que eu quero popular. 
		xStream.alias("TodasMCDC", TodasMCDC.class);

		/** Fazendo o cast para o tipo de objeto Pessoa **/
		
		xStream.alias("decision", Decisao.class);
		TodasMCDC xml = (TodasMCDC) xStream.fromXML(input);
		
		
		/** Imprimindo o resultado no console **/
		Vector<Classe> classes =  (Vector<Classe>) xml.getClasses(); 
		
		
		System.out.println("nome de Classes  :" + classes.elementAt(0).getNomeClasse());

	}
}
