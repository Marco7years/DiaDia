package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.Personaggio;

public class ComandoRegala extends AbstractComando implements Comando {

	private String regalo;
	private String messaggio;

	public ComandoRegala(String parametro) {
		this.regalo = parametro;
	}

	@Override
	public String esegui(Partita partita) {
		Personaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.regalo), partita);
		}
		else this.messaggio = "Non ci sta nessun personaggio...";
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.regalo = parametro;
	}

	public String getParametro() {
		return this.regalo;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
