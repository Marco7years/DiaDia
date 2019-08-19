package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando implements Comando {
		
	@Override
	public String esegui(Partita partita) {
		String messaggio = "Non è un comando valido!";
		return messaggio;
	}

	@Override
	public String getNome() {
		this.nomeComando = "ComandoNonValido";
		return this.nomeComando;
	}

}
