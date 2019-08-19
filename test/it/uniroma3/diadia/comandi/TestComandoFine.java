package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.Partita;

import org.junit.Test;

public class TestComandoFine {

	@Test
	public void test() throws FileNotFoundException {
		Partita partita = new Partita();
		AbstractComando comando = new ComandoFine();
		System.out.println("Deve stampare: \"Grazie di aver giocato!\"");
		comando.esegui(partita);
		System.out.println();
		assertTrue(partita.isFinita());
	}
}