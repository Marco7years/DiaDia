package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;
import it.uniroma3.diadia.personaggi.Mago;

import org.junit.Before;
import org.junit.Test;

public class TestComandoSaluta {
	private AbstractComando comando;
	private Partita partita;
	private Personaggio pers;

	@Before
	public void setUp() throws FileNotFoundException {
		this.partita = new Partita();
		this.comando = new ComandoSaluta();
		this.pers = new Mago("Mago", "Ciao sono un mago", new Attrezzo("passeopartout", 2));
	}

	@Test
	public void testSaluta() {
		this.partita.getLabirinto().getStanzaCorrente().setPersonaggio(this.pers);
		this.comando.esegui(this.partita);
		assertTrue(this.pers.haSalutato());
		System.out.println("\n");
	}

	@Test
	public void testSalutaNessuno() {
		System.out.println("Deve stampare: \"Nessno risponde\"");
		this.comando.esegui(this.partita);
		System.out.println("\n");
	}
}
