package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.*;

public class ComandoRegala extends AbstractComando{
	
	private static final String MESSAGGIO_REGALO = "non c'è nessuno, a chi lo regalo?";
	private static final String MESSAGGIO_NON_TROVATO= "Cosa devo regalare?";
	private static final String ATTREZZO_NON_TROVATO= "non hai l'attrezzo";
	
	public ComandoRegala() {
		super("regala");
	}
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null) {
			io.mostraMessaggio(MESSAGGIO_REGALO);
		}
		
		// controlla parametro
		String nomeAttrezzo = this.getParametro();
		if(nomeAttrezzo == null) {
			io.mostraMessaggio(MESSAGGIO_NON_TROVATO);
		}
		
		//cerca attrezzo in borsa
		Attrezzo attrezzoRegalo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzoRegalo==null) {
			io.mostraMessaggio(ATTREZZO_NON_TROVATO);
		}
		
		// tutto a posto
		String rispostaRegalo = personaggio.riceviRegalo(attrezzoRegalo, partita);
		io.mostraMessaggio(rispostaRegalo);
		
		// rimuovo oggetto da borsa
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		io.mostraMessaggio("hai regalato" + attrezzoRegalo.getNome() + "a" + personaggio.getNome());
	}
	
	
}