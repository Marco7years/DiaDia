package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() throws FileNotFoundException {
		this.partita = new Partita();

	}

	@Test
	public void testVintaInizioPartita() {
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testVintaNonInUscita() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente());
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testVinta() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getUscita());
		assertTrue(this.partita.vinta());
	}

	@Test
	public void testFinitaSenzaCfu() {
		this.partita.getGiocatore().setCFU(0);
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testFinitaSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testFinitaVinta() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getUscita());
		assertTrue(this.partita.isFinita());
	}
}
