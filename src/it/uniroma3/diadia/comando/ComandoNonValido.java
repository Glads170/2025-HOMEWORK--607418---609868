package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	String nome="NonValido";
			
	@Override
	public String getParametro() {
		return null;
	}
	@Override
	public String getNome() {
		return this.nome;
	} 
	
	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("comando non valido inserire un altro comando");
		io.mostraMessaggio("\n");
	}

}
