package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.*;

public class ComandoGuarda extends AbstractComando {
	
	public ComandoGuarda() {
		super("Guarda");
	}
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzacorrente = partita.getStanzaCorrente();
		//stampo il nome
		io.mostraMessaggio(stanzacorrente.getDescrizione());
		
		//stampo stato partita
		io.mostraMessaggio("cfu rimasti:" + partita.getGiocatore().getCfu());
		io.mostraMessaggio("contenuto borsa:" );
		Borsa borsa = partita.getGiocatore().getBorsa();
		io.mostraMessaggio(borsa.toString());
	}

}
