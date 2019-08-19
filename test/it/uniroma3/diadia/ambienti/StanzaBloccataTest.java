package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private StanzaBloccata stanza;
	private String direzioneBloccata;

	@Before
	public void setUp() throws Exception {
		this.stanza = new StanzaBloccata("Test");
		this.direzioneBloccata = "nord";
	}

	@Test
	public void testGetStanzaAdiacenteBloccata() {
		assertSame("Test", this.stanza.getStanzaAdiacente(this.direzioneBloccata).getNome()); // ritorna la stanza
																								// corrente
	}

	@Test
	public void testGetDescrizioneNotHasAttrezzo() {
		assertEquals("Non si passa!", this.stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizioneHasAttrezzo() {
		Attrezzo passepartout = new Attrezzo("passepartout", 2);
		this.stanza.addAttrezzo(passepartout);
		assertNotEquals("Non si passa!", this.stanza.getDescrizione());
	}

}
