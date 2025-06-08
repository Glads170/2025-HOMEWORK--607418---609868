package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;

import java.util.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_SENZA_SALUTO = "Come osi non salutarmi?! Ti spedisco nella stanza con meno attrezzi!";
	private static final String MESSAGGIO_CON_SALUTO = "Grazie per il saluto, simpaticone! Ti spedisco nella stanza con più attrezzi!";
	private List<Attrezzo> attrezzi;
	private static final String MESSAGGIO_RICEVI_REGALO = "AHHAHAHA un regalo me lo tengo!!";

	public Strega(String nome,String presentazione) {
		super(nome,presentazione);
		this.attrezzi = new ArrayList<>();
	}

	@Override
	public String agisci(Partita partita) {
		// TODO Auto-generated method stub
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		List<Direzioni> direzioni = stanzaCorrente.getDirezioni();
		List<Stanza> stanzeAdiacenti = new ArrayList<Stanza>();
		for(Direzioni s:direzioni) {
			stanzeAdiacenti.add(stanzaCorrente.getStanzaAdiacente(s));
		}

		if(this.haSalutato()) {
			partita.setStanzaCorrente(Collections.max(stanzeAdiacenti));
			return MESSAGGIO_CON_SALUTO; 
		}
		partita.setStanzaCorrente(Collections.min(stanzeAdiacenti));
		return MESSAGGIO_SENZA_SALUTO; 
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo,Partita partita) {
		this.attrezzi.add(attrezzo);
		return MESSAGGIO_RICEVI_REGALO;
	}


}