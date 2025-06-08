package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	private String nome;
	private String parametro;
	protected IO io;
	
	public AbstractComando(String nome){
		this.nome=nome;
	}
	public void setIO(IO io) {
	        this.io = io;
	}
	public IO getIO() {
        return this.io;
	}
	// va integrato io ma non ho capito come???
	public String getNome() {
		return this.nome;
	}
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	public String getParametro() {
		return this.parametro;
	}
	public abstract void esegui(Partita partita);
}