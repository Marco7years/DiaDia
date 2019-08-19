package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestComandoNonValido {
	private Partita partita;
	private AbstractComando comando;

	@Test
	public void test() throws FileNotFoundException {
		System.out.println("Deve stampare: \"Il comando inserito non � valido\"");
		this.partita = new Partita();
		this.comando = new ComandoNonValido();
		this.comando.esegui(this.partita);
		System.out.println();
	}
}