package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.IOConsole.*;
public class StanzaBloccata extends Stanza {
	
	private String direzionebloccata;
	private String chiave;
	
	public StanzaBloccata(String nome, String direzionebloccata, String chiave) {
		super(nome);
		this.direzionebloccata= direzionebloccata;
		this.chiave= chiave;
	}
	
	public String getDirezioneBloccata() {
		return this.direzionebloccata;
	}

	    public String getChiave() {
	        return this.chiave;
	    }
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
	
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

