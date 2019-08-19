package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.AttrezzoNomeComparator;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;
	private Map<String, Attrezzo> attrezzi;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
			return false;

		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		else {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
			
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.containsKey(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);
		return null;
	}

	public int getPeso() {
		int peso = 0;
		for(Attrezzo attrezzo : this.attrezzi.values())
			peso += attrezzo.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List <Attrezzo> attrezziOrdinatiPerNome = new LinkedList<Attrezzo>(this.attrezzi.values());
		attrezziOrdinatiPerNome.addAll(this.attrezzi.values());
		return attrezziOrdinatiPerNome;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> attrezziOrdinatiPerPeso = new TreeSet<Attrezzo>();
		attrezziOrdinatiPerPeso.addAll(this.attrezzi.values());
		return attrezziOrdinatiPerPeso;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> attrezziOrdinatiPerNome = new TreeSet<Attrezzo>(new AttrezzoNomeComparator());
		attrezziOrdinatiPerNome.addAll(this.attrezzi.values());
		return attrezziOrdinatiPerNome;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> contenutoRaggruppatoPerPeso = new HashMap<>();
		Set<Attrezzo> attrezziconLoStessoPeso = null;
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (contenutoRaggruppatoPerPeso.containsKey(new Integer(attrezzo.getPeso())))
				attrezziconLoStessoPeso = contenutoRaggruppatoPerPeso.get(new Integer(attrezzo.getPeso()));
			else
				attrezziconLoStessoPeso = new HashSet<Attrezzo>();
			attrezziconLoStessoPeso.add(attrezzo);
			contenutoRaggruppatoPerPeso.put(new Integer(attrezzo.getPeso()), attrezziconLoStessoPeso);
		}
		return contenutoRaggruppatoPerPeso;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Collection<Attrezzo> att = this.attrezzi.values();
		Iterator<Attrezzo> it = att.iterator();
		while(it.hasNext()) {
			a = it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				it.remove();
				return a;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(attrezzi.values().toString());
		} else
			s.append("La tua borsa è vuota");
		return s.toString();
	}

	/*@Override
	public boolean equals(Object o) {
		Attrezzo that = (Attrezzo)o;
	}*/
}