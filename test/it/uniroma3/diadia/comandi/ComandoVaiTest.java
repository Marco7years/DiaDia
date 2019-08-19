package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {

	private ComandoVai comandoTest;
	private Partita partita;

	@Before
	public void setUp() throws FileNotFoundException {
		this.comandoTest = new ComandoVai("nord");
		this.partita = new Partita();

	}

	@Test
	public void testEsegui() {
		this.comandoTest.esegui(partita);
		assertEquals("Atrio", this.partita.getLabirinto().getStanzaCorrente().getNome());
	}

	@Test
	public void testEseguiCfu() {
		this.partita.getGiocatore().setCFU(17);
		this.comandoTest.esegui(partita);
		assertEquals(17, this.partita.getGiocatore().getCFU());
	}

	@Test
	public void testSetParametro() {
		this.comandoTest.setParametro("test");
		assertEquals("test", this.comandoTest.getParametro());
	}

}
