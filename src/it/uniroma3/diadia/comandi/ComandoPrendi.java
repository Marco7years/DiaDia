package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPrendi extends AbstractComando implements Comando {

	private String attrezzoDaPrendere;
	private String messaggio;

	@Override
	public String esegui(Partita partita) {
		if(this.attrezzoDaPrendere.equals(null))
			this.messaggio = "Attrezzo non valido";
			
		if(partita.getLabirinto().getStanzaCorrente().getAttrezzi() != null) 
			if(partita.getGiocatore().getBorsa().getPeso() + partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.attrezzoDaPrendere).getPeso() < partita.getGiocatore().getBorsa().getPesoMax())
				if(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(this.attrezzoDaPrendere)) {
					partita.getGiocatore().addAttrezzoToBag(partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.attrezzoDaPrendere));
					partita.getLabirinto().getStanzaCorrente().removeAttrezzo(partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.attrezzoDaPrendere));
					this.messaggio = this.attrezzoDaPrendere + " preso";
				}
				else this.messaggio = "Questo attrezzo non e' in questa stanza.";
			else this.messaggio = "La borsa e' piena";
		else this.messaggio = "Non ci sono attrezzi in questa stanza";
		
		return this.messaggio;

	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzoDaPrendere = parametro;

	}

	@Override
	public String getNome() {
		this.nomeComando = "ComandoPrendi";
		return this.nomeComando;
	}
	
	public String getParametro() {
		return this.attrezzoDaPrendere;
	}

}
