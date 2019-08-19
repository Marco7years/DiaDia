package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends Personaggio{
	
	private static String[] direzioni = {"nord", "ovest", "sud", "est"};
	private static final String MESSAGGIO_NON_SALUTA = "Cosi impari a non salutare!";
	private static final String MESSAGGIO_SALUTA = "Si vede che sei molto educato!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(!haSalutato) {
			int conta = 10;
			Stanza stanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("nord");

			for(String a : direzioni) {
				if(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(a) != null)
					if(conta < partita.getLabirinto().getStanzaCorrente().getAttrezzi().length) {
						conta = partita.getLabirinto().getStanzaCorrente().getAttrezzi().length;
						stanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(a);
					}
			}
			partita.getLabirinto().setStanzaCorrente(stanza);
			msg = MESSAGGIO_NON_SALUTA;
		}
		else {
			int conta = 0;
			Stanza stanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("nord");

			for(String a : direzioni) {
				if(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(a) != null)
					if(conta > partita.getLabirinto().getStanzaCorrente().getAttrezzi().length) {
						conta = partita.getLabirinto().getStanzaCorrente().getAttrezzi().length;
						stanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(a);
					}
			}
			partita.getLabirinto().setStanzaCorrente(stanza);
			msg = MESSAGGIO_SALUTA;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String risposta = "Non possiedi questo attrezzo.";
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			risposta = "AHAHAH. Mi piace, me lo tengo";
		}
		return risposta;
	}

}
