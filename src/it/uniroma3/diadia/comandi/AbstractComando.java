package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	protected String nomeComando;
	
	public AbstractComando(String nome) {
		this.nomeComando = nome;
	}
	
	public AbstractComando() {}
	
	public void setParametro(String parametro) {
		this.nomeComando = parametro;
	}
	
	public String getParametro() {
		return null;
	}

	public abstract String esegui(Partita partita);
}
