package it.uniroma3.diadia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.impostazioni.Iniziali;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Personaggio;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	//private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEAggiungiPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	/*
	 * Gestione Stanze
	 */
	
	/*
	 * Crea le stanze ricevute dall'enum Iniziali
	 */
	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String stanze = this.leggiRigaCheCominciaPer(Iniziali.Stanze.toString()+":");
		for(String stanza : separaStringheAlleVirgole(stanze)) {		//il metodo "separa" restituisce una lista di stringhe separate da una virgola e da uno spazio
			String tipo = null;
			String name = null;
			String direzione = null;
			String nomeAttrezzo = null;
			try(Scanner scannerLinea = new Scanner(stanza)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il tipo di una stanza"));
				tipo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza"));
				name = scannerLinea.next();
				name = this.separaTrattini(name);
				if(tipo.contentEquals("StanzaBuia") || tipo.contentEquals("StanzaBloccata")) {
					check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
					nomeAttrezzo = scannerLinea.next();
					if(tipo.equals("StanzaBloccata")) {
						check(scannerLinea.hasNext(), msgTerminazionePrecoce("la direzione di una stanza bloccata."));
						direzione = scannerLinea.next();
					}
				}
			}
			if(tipo.equals("StanzaBloccata"))
				this.creaStanza(tipo, name, nomeAttrezzo, direzione);
			else if(tipo.equals("StanzaBuia"))
				this.creaStanza(tipo, name, nomeAttrezzo);
			else
				this.creaStanza(tipo, name);
		}
	}

	private void creaStanza(String tipo, String name, String nomeAttrezzo, String direzione) {
		// TODO Auto-generated method stub
		try {
			Stanza tmp = (Stanza) this.creaClasseStanza(tipo).getDeclaredConstructor(String.class, String.class, String.class).newInstance(name, direzione, nomeAttrezzo);
			this.nome2stanza.put(name, tmp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

	private void creaStanza(String tipo, String nome, String nomeAttrezzo) {
		try {
			Stanza tmp = (Stanza) this.creaClasseStanza(tipo).getDeclaredConstructor(String.class, String.class).newInstance(nome, nomeAttrezzo);
			this.nome2stanza.put(nome, tmp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void creaStanza(String tipo, String name) {
		try {
			Stanza tmp = (Stanza) this.creaClasseStanza(tipo).getDeclaredConstructor(String.class).newInstance(name);
			this.nome2stanza.put(name, tmp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Class<?> creaClasseStanza(String tipo) {
		// TODO Auto-generated method stub
		try {
			return Class.forName("it.uniroma3.diadia.ambienti"+tipo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * FINE GESTIONE STANZE
	 */

	/*
	 * Gestione Personaggi
	 */
	
	private void leggiEAggiungiPersonaggi() throws FormatoFileNonValidoException{
		String personaggi = this.leggiRigaCheCominciaPer(Iniziali.Personaggi.toString()+":");
		for(String Personaggio : separaStringheAlleVirgole(personaggi)) {
			String tipo = null;
			String name = null;
			String descrizione = null;
			String attrezzo = null; 
			String pesoAttrezzo = null;
			String stanza = null;
			try (Scanner scannerLinea = new Scanner(Personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio."));
				tipo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				name = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la descrizione di un personaggio."));
				descrizione = scannerLinea.next();
				descrizione = this.separaTrattini(descrizione);
				if(tipo.equals("Mago")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo di un personaggio mago."));
					attrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo di un personaggio mago."));
					pesoAttrezzo = scannerLinea.next();
				}
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza di un personaggio."));
				stanza = scannerLinea.next();
				stanza = this.separaTrattini(stanza);
			}				
			if(tipo.equals("Mago"))
				this.creaPersonaggio(tipo, name, descrizione, attrezzo, pesoAttrezzo, stanza);
			else
				this.creaPersonaggio(tipo, name, descrizione, stanza);
		}
	}
	private void creaPersonaggio(String tipo, String name, String descrizione, String stanza) throws FormatoFileNonValidoException {
		// TODO Auto-generated method stub
		Personaggio personaggio = null;
		try {
			personaggio = (Personaggio)Class.forName("it.uniroma3.diadia.personaggi."+tipo).getDeclaredConstructor(String.class, String.class).newInstance(name, descrizione);
			check(isStanzaValida(stanza),"Stanza " + stanza + " sconosciuta");
			this.nome2stanza.get(stanza).setPersonaggio(personaggio);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void creaPersonaggio(String tipo, String name, String descrizione, String attrezzo, String pesoAttrezzo, String stanza) throws FormatoFileNonValidoException {
		// TODO Auto-generated method stub
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo item = new Attrezzo(attrezzo, peso);
			Mago wizard = new Mago(name, descrizione, item);
			check(isStanzaValida(stanza),"Attrezzo "+ attrezzo+" non collocabile: stanza " +stanza+" inesistente");
			this.nome2stanza.get(stanza).setPersonaggio(wizard);
		}catch(NumberFormatException e) {
			check(false, "Peso attrezzo "+attrezzo+" non valido");
		}
	}
	
	/*
	 * FINE GESTIONE PERSONAGGI
	 */

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		Scanner scannerDiParole = scanner;
		while (scannerDiParole == scanner) {
			result.add(scannerDiParole.next());
		}
		scanner.close();
		return result;
	}
	
	private String separaTrattini(String stringa) {
		StringBuilder results = new StringBuilder();
		String[] name = stringa.split("-");
		for(String item : name) {		//scandisci l'array di nomi
			results.append(" ");		//separa ogni nome da uno spazio
			if(!item.equals(name[name.length-1]))		//se l'item non è uguale all'ultima parola
				results.append(" ");		//aggiungi uno spazio
		}
		return results.toString();
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

}
