package modelo;

import java.util.List;

public class Metodo {
	private String metodo; 
	private List<Decisao> decisoes;
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public List<Decisao> getDecisoes() {
		return decisoes;
	}
	public void setDecisoes(List<Decisao> decisoes) {
		this.decisoes = decisoes;
	}
	
}
