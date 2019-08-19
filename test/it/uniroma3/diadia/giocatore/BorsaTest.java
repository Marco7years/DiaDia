package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

	private Borsa bag;
	private Attrezzo attrezzoTest;
	private Attrezzo attrezzoNull;
	private static Attrezzo SMERALDO = new Attrezzo("smeraldo", 1);
	private static Attrezzo RAME = new Attrezzo("rame", 4);
	private static Attrezzo ORO = new Attrezzo("oro", 1);

	@Before
	public void setUp() throws Exception {
		this.bag = new Borsa();
		this.attrezzoTest = new Attrezzo("Test", 1);
		this.attrezzoNull = null;
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(this.bag.addAttrezzo(attrezzoTest));
	}

	@Test
	public void testAddAttrezzoNull() {
		assertFalse(this.bag.addAttrezzo(attrezzoNull));
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(10, this.bag.getPesoMax());
	}

	@Test
	public void testGetAttrezzo() {
		this.bag.addAttrezzo(attrezzoTest);
		assertSame(this.attrezzoTest, this.bag.getAttrezzo("Test"));
	}

	@Test
	public void testGetPeso() {
		this.bag.addAttrezzo(attrezzoTest);
		assertEquals(1, this.bag.getAttrezzo("Test").getPeso());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.bag.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		this.bag.addAttrezzo(attrezzoTest);
		assertTrue(this.bag.hasAttrezzo("Test"));
	}

	@Test
	public void testRemoveAttrezzo() {
		this.bag.addAttrezzo(attrezzoTest);
		assertSame(this.attrezzoTest, this.bag.removeAttrezzo("Test"));
	}

	@Test
	public void testGetContenutoPerPeso() {
		this.bag.addAttrezzo(RAME);
		this.bag.addAttrezzo(SMERALDO);
		this.bag.addAttrezzo(ORO);
		List<Attrezzo> confronto = new ArrayList<>();
		confronto.add(SMERALDO);
		confronto.add(ORO);
		confronto.add(RAME);
		assertEquals(this.bag.toString(), confronto, this.bag.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testGetContentutoPerNome() {
		this.bag.addAttrezzo(RAME);
		this.bag.addAttrezzo(SMERALDO);
		this.bag.addAttrezzo(ORO);
		List<Attrezzo> confronto = new ArrayList<>();
		confronto.add(ORO);
		confronto.add(RAME);
		confronto.add(SMERALDO);
		assertEquals(this.bag.toString(), confronto, this.bag.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> confronto = new HashMap<>();
		Set<Attrezzo> peso1 = new HashSet<>();
		Set<Attrezzo> peso2 = new HashSet<>();

		confronto.put(new Integer(1), peso1);
		confronto.put(new Integer(2), peso2);

		peso1.add(new Attrezzo("cavolo", 1));
		peso1.add(new Attrezzo("cavolo", 1));

		peso2.add(new Attrezzo("sasso", 2));
		peso2.add(new Attrezzo("mattone", 2));

		this.bag.addAttrezzo(new Attrezzo("cavolo", 1));
		this.bag.addAttrezzo(new Attrezzo("cavolo", 1));
		this.bag.addAttrezzo(new Attrezzo("sasso", 2));
		this.bag.addAttrezzo(new Attrezzo("mattone", 2));

		assertEquals(this.bag.toString(), confronto, this.bag.getContenutoRaggruppatoPerPeso());
	}

}
