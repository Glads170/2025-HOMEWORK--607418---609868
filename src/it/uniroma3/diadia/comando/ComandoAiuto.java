package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.*;


public class ComandoAiuto extends AbstractComando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda","interagisci","regala","saluta"};
	
	public ComandoAiuto() {
		super("aiuto");
	}
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
			io.mostraMessaggio("");
	}
}
