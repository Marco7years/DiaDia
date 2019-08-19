package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class TestPartita {
	private Partita generica;

	@Before
	public void setUp() throws FileNotFoundException {
		this.generica = new Partita();
	}

	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", this.generica.getLabirinto().getStanzaCorrente().getNome());
	}

	@Test
	public void testIsFinita_finitiCfu() {
		this.generica.getGiocatore().setCFU(0);
		assertTrue(this.generica.isFinita());
	}

	@Test
	public void testIsFinita_NuovaPartita() {
		assertFalse(this.generica.isFinita());
	}

	@Test
	public void testIsFinita_TRUE() {
		this.generica.setFinita();
		assertTrue(this.generica.isFinita());
	}

	@Test
	public void testSetStanzaCorrente() {
		this.generica.getLabirinto().setStanzaCorrente(new Stanza("aula"));
		assertEquals("aula", this.generica.getLabirinto().getStanzaCorrente().getNome());
	}
}