package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.*;

public class ComandoPrendi implements Comando {
	private String nome="prendi";
	private String nomeAttrezzo;
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	@Override
	public String getNome() {
		return this.nome;
	} 
	@Override
	public void esegui(Partita partita, IO io) {
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo) == true) {
		partita.getGiocatore().getBorsa().addAttrezzo(a);
		partita.getStanzaCorrente().removeAttrezzo(a);
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio("\n");
		}
		else {
			io.mostraMessaggio("non c'è questo attrezzo nella stanza corrente");
			io.mostraMessaggio("\n");
		}
	}

}
