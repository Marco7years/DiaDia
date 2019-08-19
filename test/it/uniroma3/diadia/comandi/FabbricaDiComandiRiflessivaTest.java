package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
	
	@Test
	public void testCostruisciComandoVai() {
		Comando comandoTest = new ComandoVai("sud");
		assertSame(comandoTest.getClass(), this.factory.costruisciComando("vai sud").getClass());
	}
	
	@Test
	public void testCostruisciComandoGuarda() {
		Comando comandoTest = new ComandoGuarda();
		assertSame(comandoTest.getClass(), this.factory.costruisciComando("guarda").getClass());
	}

}
