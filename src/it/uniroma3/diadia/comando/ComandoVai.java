package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.*;

public class ComandoVai implements Comando{
	private String nome= "vai";
    private String direzione;
    
    @Override
	public String getParametro() {
		return direzione;
	}
	@Override
	public String getNome() {
		return this.nome;
	} 
	 @Override
    public void setParametro(String parametro) {
    	this.direzione=parametro;
    }
	
    @Override
    public void esegui(Partita partita, IO io) {
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza=null;
    	if(direzione==null) {
    		io.mostraMessaggio("dove vuoi andare?"
    							+ 	"devi specificare una direzione");
    		return;
    	}
    	prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
    	if (prossimaStanza==null) {
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
		io.mostraMessaggio("\n");
    }
   
    
 
}
