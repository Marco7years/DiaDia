package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String nomeAttrezzo;

	public StanzaBloccata(String nome) {
		super(nome);
		this.nomeAttrezzo = "passepartout";
		this.direzioneBloccata = "nord";
	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
		Stanza prossimaStanza = super.getStanzaAdiacente(dir);
		if(!(dir.equals(this.direzioneBloccata)))
			if(hasAttrezzo(this.nomeAttrezzo))
				prossimaStanza = super.getStanzaAdiacente(dir);
		prossimaStanza = this;
		return prossimaStanza;
	}

	@Override
	public String getDescrizione() {
		String risultato;
		if(!(hasAttrezzo(this.nomeAttrezzo)))
			risultato = "Non si passa!";
		else
			risultato = toString();
		return risultato;
	}
}
