package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;;

public interface Comando {
	/*
	 * esecuzione del comando
	 */
	public String esegui(Partita partita);

	/*
	 * set parametro del comando
	 */
	public void setParametro(String parametro);
	
	public String getNome();
	
	public String getParametro();
	
}
