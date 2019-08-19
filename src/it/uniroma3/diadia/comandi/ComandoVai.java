package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;

public class ComandoVai extends AbstractComando implements Comando {

	private String direzione;
	private String messaggio;

	public ComandoVai(String direzione) {
		this.direzione = direzione;
		this.nomeComando = "ComandoVai";
	}

	@Override
	public String esegui(Partita partita) {

		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;

		if(this.direzione == null) {
			this.messaggio = "Dove vuoi andare? Devi specificare una direzione";
			return this.messaggio;
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			this.messaggio = "Non c'e nulla da quella parte";
			return this.messaggio;
		}

		if(stanzaCorrente.getStanzaAdiacente(this.direzione).getDescrizione().equals("Non si passa!")) {
			this.messaggio = stanzaCorrente.getStanzaAdiacente(this.direzione).getDescrizione();
			return this.messaggio;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		this.messaggio = partita.getLabirinto().getStanzaCorrente().getNome();
		partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);

		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;

	}

	public String getParametro() {
		return this.direzione;
	}

	@Override
	public String getNome() {
		return this.nomeComando;
	}

}
