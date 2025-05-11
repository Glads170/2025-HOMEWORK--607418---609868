package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.*;


public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};
	private String nome="aiuto";
	
	@Override
	public void esegui(Partita partita, IO io) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
			io.mostraMessaggio("");
			io.mostraMessaggio("\n");
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
