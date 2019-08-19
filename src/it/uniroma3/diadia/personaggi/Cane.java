package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends Personaggio{

	private static final String ABBAIA = "Woof!";
	private static final String MORSO = "Sei stato morso!";
	private static final Attrezzo TREAT = new Attrezzo("croccantino", 2);
	private Attrezzo attrezzo;


	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(haSalutato) {
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
			msg = MORSO;
		}	
		else
			msg = ABBAIA;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String risposta = "Non possiedi questo attrezzo.";
		
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			
			if(this.attrezzo.getNome().equals(TREAT)) {
				partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
				risposta = "WOOF WOOF (me like this treat, lascio il strumento qui)";
			}
			else {
				risposta = "WOOF WOOF (me don't like this treat)";
				partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			}
		}
		return risposta;
	}

}
