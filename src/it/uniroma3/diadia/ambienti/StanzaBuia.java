package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	String nomeAttrezzo;

	public StanzaBuia(String nome) {
		super(nome);
		
	}

	@Override
	public String getDescrizione() {
		String risultato;
		if(!(hasAttrezzo("lanterna")))
			risultato = "Qui c'e' buio pesto";
		else
			risultato = toString();
		return risultato;
	}
}
