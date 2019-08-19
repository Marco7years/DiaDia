package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private ComandoPrendi comando;
	private Partita partita;

	@Before
	public void setUp() throws Exception {
		this.comando = new ComandoPrendi();
		this.partita = new Partita();
	}

	@Test
	public void testEsegui() {
		Attrezzo attrezzoTest = new Attrezzo("Test", 1);
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoTest);
		this.comando.setParametro(attrezzoTest.getNome());
		this.comando.esegui(this.partita);
		assertEquals(attrezzoTest.toString(),
				this.partita.getGiocatore().getBorsa().getAttrezzo(attrezzoTest.getNome()).toString());
	}

}
