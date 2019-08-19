package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private static final String ATTREZZO_TEST = "attrezzoTest";
	private static final String SUD = "sud";
	private static final String STANZA_ADIACENTE_TEST2 = "stanzaAdiacenteTest2";
	private static final String STANZA_ADIACENTE_TEST = "stanzaAdiacenteTest";
	private static final String NORD = "nord";
	private Stanza stanzaTest; // variabile accessibile agli altri metodi
	private Attrezzo ATTREZZO_TEST1 = new Attrezzo("ATTREZZO_TEST1", 2);

	@Before
	public void setUp() {
		this.stanzaTest = new Stanza("stanzaTest");
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneInesistente() {
		Stanza stanzaAdiacente = this.stanzaTest.getStanzaAdiacente("direzioneInesistente");
		assertNull(stanzaAdiacente);
	}
	/*
	 * versione compatta:
	 * assertNull(this.stanzaTest.getStanzaAdiacente("direzioneInesistente"));
	 */

	@Test
	public void testGetStanzaAdiacenteDirezioneNul() {
		assertNull(this.stanzaTest.getStanzaAdiacente(null));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneEsistente() {
		this.stanzaTest.impostaStanzaAdiacente(NORD, new Stanza(STANZA_ADIACENTE_TEST));
		assertEquals(STANZA_ADIACENTE_TEST, this.stanzaTest.getStanzaAdiacente(NORD).getNome());
	}

	@Test
	public void testImpostaStanzaAdiacenteSostiutuzione() {
		this.stanzaTest.impostaStanzaAdiacente(NORD, new Stanza(STANZA_ADIACENTE_TEST));
		this.stanzaTest.impostaStanzaAdiacente(NORD, new Stanza(STANZA_ADIACENTE_TEST2));
		assertEquals(STANZA_ADIACENTE_TEST2, this.stanzaTest.getStanzaAdiacente(NORD).getNome());
	}

	@Test
	public void testImpostaStanzaAdiacenteDueDirezioni() {
		this.stanzaTest.impostaStanzaAdiacente(NORD, new Stanza(STANZA_ADIACENTE_TEST));
		this.stanzaTest.impostaStanzaAdiacente(SUD, new Stanza(STANZA_ADIACENTE_TEST2));
		assertEquals(STANZA_ADIACENTE_TEST, this.stanzaTest.getStanzaAdiacente(NORD).getNome());
	}

	@Test
	public void testImpostaStanzaAdiacenteMassimoDirezioni() {
		for (int i = 1; i < 5; i++)
			this.stanzaTest.impostaStanzaAdiacente("direzioneTest" + i, new Stanza(STANZA_ADIACENTE_TEST));
		assertNull(this.stanzaTest.getStanzaAdiacente("direzioneTest5"));
	}

	@Test
	public void testGetAttrezzoInesistente() {
		assertNull(this.stanzaTest.getAttrezzo("attrezzoInesistente"));
	}

	@Test
	public void testGetAttrezzoEsistente() {
		this.stanzaTest.addAttrezzo(new Attrezzo(ATTREZZO_TEST, 3));
		assertNotNull(this.stanzaTest.getAttrezzo(ATTREZZO_TEST));
	}

	@Test
	public void testGetAttrezzoNonAggiunto() {
		this.stanzaTest.addAttrezzo(new Attrezzo(ATTREZZO_TEST, 3));
		assertNull(this.stanzaTest.getAttrezzo("attrezzoTest2"));
	}

	@Test
	public void testHasAttrezzoInesistente() {
		assertFalse(this.stanzaTest.hasAttrezzo("attrezzoInesistente"));
	}

	@Test
	public void testHasAttrezzoEsistente() {
		this.stanzaTest.addAttrezzo(new Attrezzo(ATTREZZO_TEST, 3));
		assertTrue(this.stanzaTest.hasAttrezzo(ATTREZZO_TEST));
	}

	@Test
	public void testHasAttrezzoNonAggiunto() {
		this.stanzaTest.addAttrezzo(new Attrezzo(ATTREZZO_TEST, 3));
		assertFalse(this.stanzaTest.hasAttrezzo("attrezzoTest2"));
	}

	@Test
	public void testAddAttrezzoSingolo() {
		assertTrue(this.stanzaTest.addAttrezzo(new Attrezzo(ATTREZZO_TEST, 3)));
	}

	@Test
	public void testAddAttrezzoDiTroppo() {
		for (int i = 0; i < 10; i++)
			this.stanzaTest.addAttrezzo(new Attrezzo("attrezzo_test" + i, 1));
		assertFalse(this.stanzaTest.addAttrezzo(new Attrezzo("AttrezzoDiTroppo", 1)));
	}

	@Test
	public void testAddAttrezzoNull() {
		ATTREZZO_TEST1 = null;
		assertFalse(this.stanzaTest.addAttrezzo(ATTREZZO_TEST1));
	}

	@Test
	public void testRemoveAttrezzoEsistente() {
		this.stanzaTest.addAttrezzo(ATTREZZO_TEST1);
		assertTrue(this.stanzaTest.removeAttrezzo(ATTREZZO_TEST1));
	}

	@Test
	public void testRemoveAttrezzoInesistente() {
		assertFalse(this.stanzaTest.removeAttrezzo(ATTREZZO_TEST1));
	}

	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(this.stanzaTest.removeAttrezzo(null));
	}

}
