package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	public AbstractComando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione); // es. �vai sud�
		String nomeComando = null; // es. �vai�
		String parametro = null; // es. �sud�
		AbstractComando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		
		scannerDiParole.close();
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";		//sar� il nome del comando immesso dall'utente
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));		//converti la prima lettera in maiuscolo
			nomeClasse += nomeComando.substring(1);		//aggiungila al nome della classe del comando imesso
			comando = (AbstractComando)Class.forName(nomeClasse).newInstance();			//casting a comando asserendo "comando" a tempo dinamico con la stessa classe di "nomeClasse"
			comando.setParametro(parametro);		//setta il parametro del comando
		} catch (Exception e){
			comando = new ComandoNonValido();
			System.out.println("Comando inesistente");
		}
		
		return comando;
	}
}