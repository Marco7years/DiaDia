package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

	private static int CFU_INIZIALI = 20;

	private int CFU;
	private Borsa bag;

	public Giocatore() {	
		creaBorsa();
		this.CFU = CFU_INIZIALI;
	}

	public void creaBorsa() {
		this.bag = new Borsa(10);
	}

	public Borsa getBorsa() {
		return this.bag;
	}

	public void addAttrezzoToBag(Attrezzo attrezzo) {
		bag.addAttrezzo(attrezzo);
	}

	public int getCFU() {
		return this.CFU;
	}

	public void setCFU(int cfu) {
		this.CFU = cfu;		
	}

}