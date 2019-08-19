package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.impostazioni.Direzioni;
import it.uniroma3.diadia.personaggi.*;

public class Labirinto {

	private Stanza uscita;
	private Stanza corrente;
	private CaricatoreLabirinto caricatore;

	public Labirinto(String nomeFile) throws FileNotFoundException {
		creaStanze();
		this.caricatore = new CaricatoreLabirinto(nomeFile);
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo passepartout = new Attrezzo("passepartout", 2);

		Strega strega = new Strega("Strega", "Sono una strega!");
		Mago mago = new Mago("Mago", "Sono un mago!", passepartout);
		Cane cane = new Cane("Cane", "BAU!", lanterna);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		StanzaBuia laboratorio = new StanzaBuia("Laboratorio Campus");
		StanzaBloccata biblioteca = new StanzaBloccata("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzioni.NORD.toString(), biblioteca);
		atrio.impostaStanzaAdiacente(Direzioni.EST.toString(), aulaN11);
		atrio.impostaStanzaAdiacente(Direzioni.SUD.toString(), aulaN10);
		atrio.impostaStanzaAdiacente(Direzioni.OVEST.toString(), laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzioni.EST.toString(), laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzioni.OVEST.toString(), atrio);
		aulaN10.impostaStanzaAdiacente(Direzioni.NORD.toString(), atrio);
		aulaN10.impostaStanzaAdiacente(Direzioni.EST.toString(), aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzioni.OVEST.toString(), laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzioni.EST.toString(), atrio);
		laboratorio.impostaStanzaAdiacente(Direzioni.OVEST.toString(), aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzioni.SUD.toString(), atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		/*aggiungi i personaggi nelle stanze*/
		aulaN11.addPersonaggio(cane);
		laboratorio.addPersonaggio(mago);
		aulaN10.addPersonaggio(strega);

		/* il gioco comincia nell'atrio e finisce nella biblioteca*/
		corrente = atrio;
		uscita = biblioteca;
	}

	public void setStanzaCorrente(Stanza stanza) {
		this.corrente = stanza;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.uscita = stanza;
	}

	public Stanza getStanzaCorrente() {
		return this.corrente;
	}

	public Stanza getUscita() {
		return this.uscita;
	}
	
	public void caricaLabirinto() throws FormatoFileNonValidoException {
		this.caricatore.carica();
	}
	
	public CaricatoreLabirinto getCaricatore() {
		return this.caricatore;
	}

}
