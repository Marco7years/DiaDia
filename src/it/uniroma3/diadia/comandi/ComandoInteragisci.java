package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.Personaggio;

public class ComandoInteragisci extends AbstractComando implements Comando {

	private static String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		Personaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			return this.messaggio;
		}
		else
			return MESSAGGIO_CON_CHI;
	}


	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public void setParametro(String parametro) {}


	@Override
	public String getNome() {
		return null;
	}
}
