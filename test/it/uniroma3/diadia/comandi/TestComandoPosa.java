package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestComandoPosa {

	private ComandoPosa comando;
	private Partita partita;

	@Before
	public void setUp() throws Exception {
		this.comando = new ComandoPosa();
		this.partita = new Partita();
	}

	@Test
	public void testEsegui() {
		Attrezzo attrezzoTest = new Attrezzo("Test", 1);
		this.comando.setParametro(attrezzoTest.getNome());
		this.partita.getGiocatore().addAttrezzoToBag(attrezzoTest);
		this.comando.esegui(this.partita);
		assertEquals(attrezzoTest.toString(),
				this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(attrezzoTest.getNome()).toString());
	}

}