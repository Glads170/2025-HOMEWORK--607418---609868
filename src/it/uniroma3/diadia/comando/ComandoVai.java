package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.Partita;


public class ComandoVai extends AbstractComando{
	Direzioni direzione;
	
    public ComandoVai() {
		super("vai");
	}
  
    @Override
    public void esegui(Partita partita){
    	try {
    	direzione = Direzioni.valueOf(this.getParametro().toUpperCase());
    	}
    	catch(IllegalArgumentException a) {
    	}
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza=null;
    	if(this.getParametro()==null) {
    		io.mostraMessaggio("dove vuoi andare?"
    							+ 	"devi specificare una direzione");
    		return;
    	}
    	prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
    	if(prossimaStanza==stanzaCorrente)
    		io.mostraMessaggio("ti ho detto che è bloccata");
    	if (prossimaStanza==null) {
    		prossimaStanza=stanzaCorrente;
    		io.mostraMessaggio("direzione inesistente");
    	}
    	partita.setStanzaCorrente(prossimaStanza);
    	partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
    	
    	Stanza stanzacorrente = partita.getStanzaCorrente();
		//stampo il nome
		io.mostraMessaggio(stanzacorrente.getDescrizione());
    	
    	io.mostraMessaggio("cfu rimasti:" + partita.getGiocatore().getCfu());
    	
		Borsa borsa = partita.getGiocatore().getBorsa();
		io.mostraMessaggio(borsa.toString());
		
    }
   
    
 
}
