package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import java.io.FileNotFoundException;

//import static org.junit.Assert.*;
//import org.junit.Before;
import org.junit.Test;

public class TestComandoAiuto {

	@Test
	public void test() throws FileNotFoundException {
		ComandoAiuto comandoAiuto = new ComandoAiuto();
		System.out.println("Deve stampare l'elenco dei comandi disponibili");
		comandoAiuto.esegui(new Partita());
		System.out.println();
	}
}