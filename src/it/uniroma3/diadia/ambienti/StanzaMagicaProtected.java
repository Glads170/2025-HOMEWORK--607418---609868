package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends Stanzaprotected {

final static private int SOGLIA_MAGICA_DEFAULT = 3;
	
	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;
	
	
	public int getcontatoreAttrezziposati() {
		return this.contatoreAttrezziPosati;
	}
	public void setcontatoreAttrezziposati(int parametro) {
		this.contatoreAttrezziPosati=parametro;
	}
	
	public int getsogliamagica() {
		return this.sogliaMagica;
	}
	public void setsogliaMagica(int parametro) {
		this.sogliaMagica=parametro;
	}
	
	
	public StanzaMagicaProtected(String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;
	}
	
	
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
		pesoX2);
		return attrezzo;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
		attrezzo = this.modificaAttrezzo(attrezzo);
		if (this.numeroAttrezzi<this.attrezzi.length) {
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;

		}
		else return false;
	}

}



