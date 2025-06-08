package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	public ComandoPosa() {
		super("posa");
	}
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()) == true) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			io.mostraMessaggio("hai posato " + this.getParametro());
			io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		}
		else {
			io.mostraMessaggio("non hai niente del genere");
		}
	}

}
