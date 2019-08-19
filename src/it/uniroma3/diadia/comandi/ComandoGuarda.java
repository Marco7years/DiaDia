package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando implements Comando {

	@Override
	public String esegui(Partita partita) {
		String messaggio;
		messaggio = "\nStanza corrente: " + partita.getLabirinto().getStanzaCorrente().getDescrizione()
				+ "\nCfu rimanenti: "+ partita.getGiocatore().getCFU()
				+ "\n"
				+ partita.getGiocatore().getBorsa();
		return messaggio;
	}

	@Override
	public String getNome() {
		this.nomeComando = "ComandoGuarda";
		return this.nomeComando;
	}

}