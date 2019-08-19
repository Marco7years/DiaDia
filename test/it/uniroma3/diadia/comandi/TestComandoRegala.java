package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Personaggio;

import org.junit.Test;

public class TestComandoRegala {

	@Test
	public void test() throws FileNotFoundException {
		Partita partita = new Partita();
		Personaggio personaggio = new Mago("Mago", "Ciao sono un mago", new Attrezzo("passepartout", 2));
		partita.getLabirinto().getStanzaCorrente().setPersonaggio(personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello", 1));
		AbstractComando comando = new ComandoRegala("martello");
		comando.setParametro("scalpello");
		assertEquals("Non possiedi questo attrezzo.", comando.esegui(partita));
		comando.setParametro("martello");
		assertEquals("ricevuto", comando.esegui(partita));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
}
