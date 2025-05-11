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
		stanza = new StanzaBloccata("stanza","nord","chiave");
		chiave = new Attrezzo("chiave", 1);
		stanzaadiacente = new	Stanza("stanzaadiacente");
		stanza.impostaStanzaAdiacente("nord", stanzaadiacente);
	}

	@Test
	void testcostruttore() {
		assertEquals("nord",stanza.getDirezioneBloccata());
		assertEquals(chiave.getNome(),stanza.getChiave());
	}

	@Test 
	void testgetstanzaadiacente_nullo() {
		assertEquals(stanza,stanza.getStanzaAdiacente("nord"));
	}
	@Test
	void testgetstanzaadiacente_buon() {
		stanza.addAttrezzo(chiave);
		assertEquals(stanzaadiacente,stanza.getStanzaAdiacente("nord"));
	}
	@Test
	void direzionenonbloccata() {
		Stanza stanzaadiacentenonbloccata = new Stanza("stanzanonbloccata");
		stanza.impostaStanzaAdiacente("est", stanzaadiacentenonbloccata);
		assertEquals(stanzaadiacentenonbloccata,stanza.getStanzaAdiacente("est"));
	}
	@Test
	void getdescrizione_false() {
		String descrizione = "Stanza bloccata nella direzione: "+ stanza.getDirezioneBloccata()+"\nPrendi l'attrezzo: " + stanza.getChiave() + " e posalo nella stanza";
		assertEquals(stanza.toString()+descrizione,stanza.getDescrizione());
	}
	@Test
	void getdescrizione_true() {
		stanza.addAttrezzo(chiave);
		assertEquals(stanza.toString(),stanza.getDescrizione());
	}
	
	

}
