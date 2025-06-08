package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class StregaTest {
	Strega strega;
	Partita partita;
	@BeforeEach
	void setUp() throws Exception {
		strega= new Strega("pippo","bau");
		partita = new Partita();
	}

	@Test
	void testcostruttore() {
		assertEquals("pippo" ,strega.getNome());
		assertEquals("bau",strega.getPresentazione());
	}
	@Test
	void testagiscisenzasaluto(){
		strega.agisci(partita);
		assertEquals( "Laboratorio Campus", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testagisciconsaluto(){
		strega.saluta();
		strega.agisci(partita);
		assertEquals("Aula N10" , partita.getStanzaCorrente().getNome());
	}
}

