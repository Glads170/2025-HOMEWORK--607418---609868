package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.*;

public class ComandoPrendi extends AbstractComando{
	
	
	public ComandoPrendi() {
		super("prendi");
		
	}
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
		if(partita.getStanzaCorrente().hasAttrezzo(this.getParametro()) == true) {
		partita.getGiocatore().getBorsa().addAttrezzo(a);
		partita.getStanzaCorrente().removeAttrezzo(this.getParametro());
		io.mostraMessaggio("hai preso " + this.getParametro());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		}
		else {
			io.mostraMessaggio("non c'è questo attrezzo nella stanza corrente");
		}
	}

}
