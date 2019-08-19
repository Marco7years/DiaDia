package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {

	private Giocatore studente;
	private Attrezzo strumento;

	@Before
	public void setUp() throws Exception {
		this.studente = new Giocatore();
		this.strumento = new Attrezzo("Test", 1);
	}

	@Test
	public void testAddAttrezzoToBag() {
		this.studente.addAttrezzoToBag(strumento);
		assertTrue(this.studente.getBorsa().hasAttrezzo(this.strumento.getNome()));
	}

	@Test
	public void testAddAttrezzoNull() {
		this.studente.addAttrezzoToBag(null);
		assertFalse(this.studente.getBorsa().hasAttrezzo(null));
	}

	@Test
	public void testGetCfu() {
		assertEquals(20, this.studente.getCFU());
	}

	@Test
	public void testSetCfu() {
		this.studente.setCFU(0);
		assertEquals(0, this.studente.getCFU());
	}

}
