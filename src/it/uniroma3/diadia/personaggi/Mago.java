package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends Personaggio{

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " + "con una mia magica azione, troverai un nuovo oggetto" +
			" per il tuo borsone!";

	private static final String MESSAGGIO_SCUSE = "Mi spiace ma non ho piu nulla...";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(attrezzo != null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else msg = MESSAGGIO_SCUSE;

		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String risposta = "Non possiedi questo attrezzo.";
		
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			attrezzo.setPeso(this.attrezzo.getPeso()%2);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			risposta = "Grazie per avermi regalato " + this.attrezzo.getNome() + " ma non ne ho bisogno. Puoi riprenderlo, ti sembrera piu leggero.";
		}
		
		return risposta;
		
	}	

}