package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private StanzaBuia stanza;
	private Attrezzo lanterna;

	@Before
	public void setUp() throws Exception {
		this.stanza = new StanzaBuia("Test");
		this.lanterna = new Attrezzo("lanterna", 3);

	}

	@Test
	public void testGetDescrizioneBuia() {
		assertEquals("Qui c'e' buio pesto", this.stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizioneNonBuia() {
		this.stanza.addAttrezzo(lanterna);
		assertEquals(this.stanza.toString(), this.stanza.getDescrizione());
	}

}
