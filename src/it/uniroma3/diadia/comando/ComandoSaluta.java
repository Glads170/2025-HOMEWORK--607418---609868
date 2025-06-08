package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
public class ComandoSaluta extends AbstractComando{
	private static final String MESSAGGIO_SALUTO =
			"qui non c'è nessuno";
	private String messaggioSaluto;
	
	public ComandoSaluta() {
		super("saluta");
	}
	public String getMessaggio() {
		return this.messaggioSaluto;
	}
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			this.messaggioSaluto = personaggio.saluta();
			io.mostraMessaggio(this.messaggioSaluto);
		}
		else {
			io.mostraMessaggio(MESSAGGIO_SALUTO);
		}
	}
}