package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertSame;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class TestComandoVai {
	private Partita partita;
	private Stanza adiacente;
	private Stanza corrente;

	@Before
	public void setUp() throws FileNotFoundException {
		this.partita = new Partita();
		corrente = new Stanza("Inizio");
		adiacente = new Stanza("Stanza Adiacente");
		this.partita.getLabirinto().setStanzaCorrente(corrente);
		corrente.impostaStanzaAdiacente("est", adiacente);
	}

	@Test
	public void testVaiDirezioneNulla() {
		Comando comando = new ComandoVai(null);
		System.out.println("Deve stampare: \"Dove vuoi andare? Devi...\"");
		comando.esegui(this.partita);
		System.out.println();
		assertSame(this.corrente, this.partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testVaiStanzaEsistente() {
		Comando comando = new ComandoVai("est");
		System.out.println("Deve stampare le informazioni sulla nuova stanza corrente");
		comando.esegui(partita);
		System.out.println();
		assertSame(this.adiacente, this.partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testVaiStanzaInesistente() {
		Comando comando = new ComandoVai("ovest");
		System.out.println("Deve stampare: \"Direzione inesistente\"");
		comando.esegui(partita);
		System.out.println();
		assertSame(this.corrente, this.partita.getLabirinto().getStanzaCorrente());
	}
}
