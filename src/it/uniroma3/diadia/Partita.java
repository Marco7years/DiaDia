package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	private Giocatore studente;
	private Labirinto lab;

	public Partita() throws FileNotFoundException {
		this.finita = false;
		creaGiocatore();
		this.lab = new Labirinto("labirinto");
	}

	public Labirinto getLabirinto() {
		return this.lab;
	}

	public void creaGiocatore() {
		this.studente = new Giocatore();
	}

	public Giocatore getGiocatore() {
		return this.studente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.lab.getStanzaCorrente() == this.lab.getUscita();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * o vinta
	 * o si sono esauriti i cfu
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || !giocatoreIsVivo();
	}

	public boolean giocatoreIsVivo() {
		return this.studente.getCFU()!=0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

}