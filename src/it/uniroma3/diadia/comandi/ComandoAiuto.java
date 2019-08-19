package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando implements Comando  {

	//static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "interagisci", "regala" };
	
	@Override
	public String esegui(Partita partita) {
		
		String messaggio = "vai aiuto fine prendi posa guarda saluta interagisci regala";
		return messaggio;
	}
	
	@Override
	public String getNome() {
		this.nomeComando = "ComandoAiuto";
		return this.nomeComando;
	}

}
