package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;
import it.uniroma3.diadia.personaggi.Mago;

import org.junit.Test;

public class TestComandoInteragisci {

	@Test
	public void testEsegui() throws FileNotFoundException {
		Personaggio pers = new Mago("Mago", "Ciao sono un mago", new Attrezzo("passepartout", 2));
		AbstractComando comando = new ComandoInteragisci();
		Partita partita = new Partita();
		partita.getLabirinto().getStanzaCorrente().setPersonaggio(pers);
		comando.esegui(partita);
		assertEquals("done", pers.agisci(partita));
	}
}
