package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzioni direzionebloccata;
	private String chiave;

	public StanzaBloccata(String nome, Direzioni Direzionebloccata, String chiave) {
		super(nome);
		this.direzionebloccata= Direzionebloccata;
		this.chiave= chiave;
	}

	public Direzioni getDirezioneBloccata() {
		return this.direzionebloccata;
	}

	public String getChiave() {
		return this.chiave;
	}
	@Override
	public Stanza getStanzaAdiacente(Direzioni direzione) {
		if(direzione.equals(this.direzionebloccata)) {
			if(this.hasAttrezzo(chiave)) {
				return super.getStanzaAdiacente(direzione);
			}
			else {
				return this;
			}
		}
		else
			return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		String descrizione = "\nStanza bloccata nella direzione: "+ direzionebloccata+"\nPrendi l'attrezzo " + chiave + " e posalo nella stanza";

		if(!this.hasAttrezzo(chiave))
			return super.getDescrizione() + descrizione;

		return super.getDescrizione();
	}
}	

