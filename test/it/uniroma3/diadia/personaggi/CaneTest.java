package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
class CaneTest {
	Cane cane;
	Partita partita;
	@BeforeEach
	void setUp() throws Exception {
		Attrezzo osso = new Attrezzo("osso",3);
		cane= new Cane("pippo","bau",osso);
		partita = new Partita();
	}

	@Test
	void testcostruttore() {
		assertEquals("pippo" ,cane.getNome());
		assertEquals("bau",cane.getPresentazione());
	}
	@Test
	void testagisci(){
		int cfuiniz = partita.getGiocatore().getCfu();
		cane.agisci(partita);
		assertEquals(cfuiniz-1, partita.getGiocatore().getCfu());
	}
}
