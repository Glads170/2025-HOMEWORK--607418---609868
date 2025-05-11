package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String nome="posa";
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
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeAttrezzo) == true) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
		}
	}

}
