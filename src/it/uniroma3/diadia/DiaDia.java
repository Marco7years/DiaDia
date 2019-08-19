package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.comandi.InterfacciaUtente;
import it.uniroma3.diadia.comandi.InterfacciaUtenteConsole;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	private Partita partita;
	private InterfacciaUtente interfaccia;

	public DiaDia() throws FileNotFoundException {
		this.partita = new Partita();
	}

	public void gioca() throws Exception{
		String istruzione; 
		this.interfaccia = new InterfacciaUtenteConsole(this.partita);
		do
			istruzione = this.interfaccia.prendiIstruzione();
		while(!processaIstruzione(istruzione));
	}


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	public boolean processaIstruzione(String istruzione) throws Exception{
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		
		this.interfaccia.mostraMessaggio(comandoDaEseguire.esegui(this.partita));
		
		return this.partita.isFinita();
	} 

	public static void main(String[] argc) throws Exception {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}