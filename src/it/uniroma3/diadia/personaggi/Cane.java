package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_MORSO = "hai perso CFU!";
	private static final int CFU_PERSI = 1;
	private static final String CIBO_PREFERITO = "croccantini";
	private Attrezzo attrezzo;
	
	public Cane(String nome, String presentazione,Attrezzo attrezzo) {
		super(nome,presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - CFU_PERSI);
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo,Partita partita) {
		StringBuilder msg = new StringBuilder();
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) { // è il cibo giusto
			msg.append("Bau! Grazie per i " + attrezzo.getNome() + "! Adesso ti do il mio " + this.attrezzo.getNome() + ".");
			//cane mette nella stanza l'oggetto e il cane perde l'ogetto
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
		}
		else { //cibo sbagliato
			msg.append(MESSAGGIO_MORSO);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - CFU_PERSI);
		}
		return msg.toString();
	}
}
