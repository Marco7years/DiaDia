package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando implements Comando {

	private String attrezzoDaPosare;
	private String messaggio;

	public int contaAttrezzi(Partita partita) {
		int conta=0;
		for(int i = 0; i<partita.getLabirinto().getStanzaCorrente().getAttrezzi().length-1; i++) {
			if(partita.getLabirinto().getStanzaCorrente().getAttrezzi()[i] != null)
				conta++;
		}
		return conta;
	}

	@Override
	public String esegui(Partita partita) {

		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoDaPosare))
			if(!(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(this.attrezzoDaPosare))) {
				if(contaAttrezzi(partita)<partita.getLabirinto().getStanzaCorrente().getAttrezzi().length) {
					partita.getLabirinto().getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(this.attrezzoDaPosare));
					partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPosare);
				}
				this.messaggio = partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.attrezzoDaPosare).getNome()+" posato!";
			}
			else this.messaggio = "Attrezzo gia' presente in questa stanza";
		else this.messaggio = "Non hai questo attrezzo";
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzoDaPosare = parametro;
	}

	@Override
	public String getNome() {
		this.nomeComando = "ComandoPosa";
		return this.nomeComando;
	}

	public String getParametro() {
		return this.attrezzoDaPosare;
	}

}
