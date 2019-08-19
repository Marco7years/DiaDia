package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando implements Comando {
	
	public ComandoFine() {}
	
	@Override
	public String esegui(Partita partita) {
		partita.setFinita();
		String messaggio = "La partita e' finita";
		return messaggio;
	}

	@Override
	public String getNome() {
		this.nomeComando = "ComandoFine";
		return this.nomeComando;
	}
	
}