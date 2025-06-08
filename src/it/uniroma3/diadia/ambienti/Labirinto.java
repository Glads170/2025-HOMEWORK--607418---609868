package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;
public class Labirinto{
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	// creo tutte le stanze i collegamenti fra essi e metto gli attrezzi


	public Stanza getStanzaVincente(){
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanza){
		this.stanzaVincente = stanza;
	}
	public void setStanzaIniziale(Stanza stanza){
		this.stanzaIniziale = stanza;
	}
	public Stanza getStanzaIniziale(){
		return this.stanzaIniziale;
	}


	public static class LabirintoBuilder{
		
		private Labirinto labirinto;
		private Map<String,Stanza> nomeStanze;
		private Stanza ultimaStanzaAggiunta;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nomeStanze = new HashMap<>();
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nomeStanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			StanzaMagica stanza = new StanzaMagica(nomeStanza);
			this.nomeStanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String nomeStanza,int par) {
			StanzaMagica stanza = new StanzaMagica(nomeStanza,par);
			this.nomeStanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzoPerVedere) {
			StanzaBuia stanza = new StanzaBuia(nomeStanza,nomeAttrezzoPerVedere);
			this.nomeStanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzionebloccata, String nomeAttrezzoChiave) {
			Direzioni dirbloccata = Direzioni.valueOf(direzionebloccata.toUpperCase());
			StanzaBloccata stanza = new StanzaBloccata(nomeStanza, dirbloccata, nomeAttrezzoChiave);
			this.nomeStanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			Stanza stanzaIniziale = this.nomeStanze.get(nomeStanzaIniziale);
			if(stanzaIniziale==null) {
				stanzaIniziale = new Stanza(nomeStanzaIniziale);
				this.nomeStanze.put(nomeStanzaIniziale, stanzaIniziale);
			}
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			this.ultimaStanzaAggiunta= stanzaIniziale;
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			Stanza stanzaVincente = this.nomeStanze.get(nomeStanzaVincente);
			if(stanzaVincente==null) {
				stanzaVincente = new Stanza(nomeStanzaVincente);
				this.nomeStanze.put(nomeStanzaVincente, stanzaVincente);
			}
			this.labirinto.setStanzaVincente(stanzaVincente);
			this.ultimaStanzaAggiunta= stanzaVincente;
			return this;
		}

		public LabirintoBuilder addAdiacenza(String partenza , String arrivo, String direzione) {
			Stanza stanzaPartenza = this.nomeStanze.get(partenza);
			Stanza stanzaArrivo = this.nomeStanze.get(arrivo);

			if(stanzaPartenza == null) {
				stanzaPartenza = new Stanza(partenza);
				this.nomeStanze.put(partenza, stanzaPartenza);
			}
			if(stanzaArrivo==null) {
				stanzaArrivo = new Stanza(arrivo);
				this.nomeStanze.put(arrivo, stanzaArrivo);
			}
			try {
			stanzaPartenza.impostaStanzaAdiacente(Direzioni.valueOf(direzione.toUpperCase()), stanzaArrivo);
			stanzaArrivo.impostaStanzaAdiacente(Direzioni.valueOf(direzione.toUpperCase()).opposta(),stanzaPartenza);
			}catch(IllegalArgumentException a) {
			}
		return this;		
}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso) {
			if(this.ultimaStanzaAggiunta!=null) {
				this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
			}
			else {
				System.out.println("nessuna stanza in cui posare l'attrezzo");
			}
			return this;
		}
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo,int peso,String nomeStanza) {
			if(this.nomeStanze.containsKey(nomeStanza)) {
				this.nomeStanze.get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
			}
			else {
				System.out.println("nessuna stanza in cui posare l'attrezzo");
			}
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		public Map<String,Stanza> getListaStanze(){
			return this.nomeStanze;
		}

	};

}