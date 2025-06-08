package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	String nome="NonValido";
	
	public ComandoNonValido() {
		super("NonValido");
	}

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando non valido inserire un altro comando");
		io.mostraMessaggio("\n");
	}

}
