package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	String nome="fine";
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	@Override
	public void setParametro(String parametro) {
		return;
	}
	@Override
	public String getParametro() {
		return null;
	}
	@Override
	public String getNome() {
		return this.nome;
	} 
}
