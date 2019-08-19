package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;

public class ComandoSaluta extends AbstractComando implements Comando {

	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		Personaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio != null) 
			this.messaggio = personaggio.saluta();
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return null;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
