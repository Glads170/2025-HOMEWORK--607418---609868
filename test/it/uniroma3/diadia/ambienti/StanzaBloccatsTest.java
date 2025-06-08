package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.*;
class StanzaBloccatsTest {
	
	StanzaBloccata stanza;
	Attrezzo chiave;
	Stanza stanzaadiacente;
	@BeforeEach
	void setUp() throws Exception {
		stanza = new StanzaBloccata("stanza",Direzioni.NORD,"chiave");
		chiave = new Attrezzo("chiave", 1);
		stanzaadiacente = new	Stanza("stanzaadiacente");
		stanza.impostaStanzaAdiacente(Direzioni.NORD, stanzaadiacente);
	}

	@Test
	void testcostruttore() {
		assertEquals(0,stanza.getDirezioneBloccata().getGradi());
		assertEquals(chiave.getNome(),stanza.getChiave());
	}

	@Test 
	void testgetstanzaadiacente_nullo() {
		assertEquals(stanza,stanza.getStanzaAdiacente(Direzioni.NORD));
	}
	@Test
	void testgetstanzaadiacente_buon() {
		stanza.addAttrezzo(chiave);
		assertEquals(stanzaadiacente,stanza.getStanzaAdiacente(Direzioni.NORD));
	}
	@Test
	void direzionenonbloccata() {
		Stanza stanzaadiacentenonbloccata = new Stanza("stanzanonbloccata");
		stanza.impostaStanzaAdiacente(Direzioni.EST, stanzaadiacentenonbloccata);
		assertEquals(stanzaadiacentenonbloccata,stanza.getStanzaAdiacente(Direzioni.EST));
	}
	@Test
	void getdescrizione_false() {
		String descrizione = "\nStanza bloccata nella direzione: "+ stanza.getDirezioneBloccata()+"\nPrendi l'attrezzo " + stanza.getChiave() + " e posalo nella stanza";
		assertEquals(stanza.toString()+descrizione,stanza.getDescrizione());
	}
	@Test
	void getdescrizione_true() {
		stanza.addAttrezzo(chiave);
		assertEquals(stanza.toString(),stanza.getDescrizione());
	}
	
	

}
