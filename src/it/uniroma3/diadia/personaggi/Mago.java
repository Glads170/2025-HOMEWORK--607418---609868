package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.*;
public class Mago extends AbstractPersonaggio{
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_REGALO_DIMEZZATO = "Ah, un regalo! Per ringraziarti, ne dimezzo il peso e lo lascio cadere nella stanza.";
	private Attrezzo attrezzo;
	
	public Mago(String nome,String presentazione, Attrezzo attrezzo) {
		super(nome,presentazione);
		this.attrezzo=attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg=MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo,Partita partita) {
		int nuovoPeso = attrezzo.getPeso() / 2;
		if(nuovoPeso == 0 && attrezzo.getPeso()>0) {
			nuovoPeso = 1;
		}
		else {
			if(attrezzo.getPeso()==0) {
				nuovoPeso=0;
			}
		}
		attrezzo.setPeso(nuovoPeso);
		// attrezzo modificato
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return MESSAGGIO_REGALO_DIMEZZATO;
 	}
}