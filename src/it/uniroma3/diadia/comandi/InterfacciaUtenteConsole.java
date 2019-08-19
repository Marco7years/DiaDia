package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.Partita;

public class InterfacciaUtenteConsole implements InterfacciaUtente {

	private Scanner scannerDiLinee; 

	private Partita partita; 

	static final private String MESSAGGIO_BENVENUTO = ""+ 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" + 
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+ 
			"I locali sono popolati da strani personaggi, " + 
			"alcuni amici, altri... chissa!\n"+ 
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+ 
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" + 
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+ 
			"Per conoscere le istruzioni usa il comando 'aiuto'."; 


	public InterfacciaUtenteConsole(Partita partita) { 
		System.out.println(MESSAGGIO_BENVENUTO); 
		scannerDiLinee = new Scanner(System.in); 
		this.partita = partita; 
	} 

	@Override 
	public void mostraMessaggio(String messaggio) { 
		System.out.println(messaggio); 
		if (this.partita.vinta()) 
			System.out.println("Hai vinto!"); 
		if (!this.partita.giocatoreIsVivo()) 
			System.out.println("Hai esaurito i CFU..."); 
	} 

	@Override 
	public String prendiIstruzione() { 
		return this.scannerDiLinee.nextLine(); 
	} 
}
