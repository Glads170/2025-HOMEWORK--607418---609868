package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class FakeComando extends AbstractComando {

	public FakeComando(String nome) {
		super(nome);
		
	}

	@Override
	public void esegui(Partita partita) {
		return;
	}

}
