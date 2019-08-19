/*package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica test;
	private Comando c;

	@Before
	public void setUp() {
		this.test = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testCostruisciComandoAiuto() {
		this.c = this.test.costruisciComando("aiuto");
		assertEquals("ComandoAiuto", this.c.getNome());
	}

	@Test
	public void testCostruisciComandoFine() {
		this.c = this.test.costruisciComando("fine");
		assertEquals("ComandoFine", this.c.getNome());

	}

	@Test
	public void testCostruisciComandoGuarda() {
		this.c = this.test.costruisciComando("guarda");
		assertEquals("ComandoGuarda", this.c.getNome());
	}

	@Test
	public void testCostruisciComandoNonValido() {
		this.c = this.test.costruisciComando("");
		assertEquals("ComandoNonValido", this.c.getNome());

	}

	@Test
	public void testCostruisciComandoPosa() {
		this.c = this.test.costruisciComando("posa");
		this.c.setParametro("osso");
		assertEquals("osso", this.c.getParametro());
		assertEquals("ComandoPosa", this.c.getNome());
	}

	@Test
	public void testCostruisciComandoPrendi() {
		this.c = this.test.costruisciComando("prendi");
		this.c.setParametro("osso");
		assertEquals("osso", this.c.getParametro());
		assertEquals("ComandoPrendi", this.c.getNome());
	}

	@Test
	public void testCostruisciComandoVai() {
		this.c = this.test.costruisciComando("vai");
		assertEquals("ComandoVai", this.c.getNome());
	}

}
*/