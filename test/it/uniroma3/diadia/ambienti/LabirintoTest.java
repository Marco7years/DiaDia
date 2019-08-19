package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {

	private Labirinto lab;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaX;

	@Before
	public void setUp() throws Exception {
		this.lab = new Labirinto("labirintoTest");
		this.stanzaCorrente = new Stanza("Atrio");
		this.stanzaVincente = new Stanza("Biblioteca");
		this.stanzaX = new Stanza("N10");
		this.lab.setStanzaCorrente(stanzaCorrente);
		this.lab.setStanzaVincente(stanzaVincente);
	}

	@Test
	public void testGetEntrata() {
		assertEquals(this.lab.getStanzaCorrente(), this.lab.getStanzaCorrente());

	}

	@Test
	public void testGetStanzaCorrente() {
		assertSame(this.stanzaCorrente, this.lab.getStanzaCorrente());
	}

	@Test
	public void testGetUscita() {
		assertSame(this.stanzaVincente, this.lab.getUscita());
	}

	@Test
	public void testSetStanzaCorrente() {
		this.lab.setStanzaCorrente(stanzaX);
		assertSame(this.stanzaX, this.lab.getStanzaCorrente());
	}

	@Test
	public void testSetStanzaVincente() {
		this.lab.setStanzaVincente(stanzaX);
		assertSame(this.stanzaX, this.lab.getUscita());
	}

}
