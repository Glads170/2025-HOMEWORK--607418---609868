package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI =
			"Con chi dovrei interagire?...";
	private String messaggio;
	
	public ComandoInteragisci() {
		super("interagisci");
	}
	public String getMessaggio() {
		return this.messaggio;
	}

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		 AbstractPersonaggio personaggio;
	     personaggio = partita.getStanzaCorrente().getPersonaggio();
	     if (personaggio != null) {
	    	 this.messaggio = personaggio.agisci(partita);
	    	 io.mostraMessaggio(this.messaggio);
	     } else {
	    	 io.mostraMessaggio(MESSAGGIO_CON_CHI);
	     }
	}
}
	