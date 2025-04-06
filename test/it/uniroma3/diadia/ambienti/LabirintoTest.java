package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



class LabirintoTest {
	private Labirinto l;
	private Stanza si;
	private Stanza sd;
	private Stanza sv;
	@BeforeEach
	public void setup() {
		l = new Labirinto();
		l.creaStanze();
		si = new Stanza("Atrio");
		sd = new Stanza("Stanza diversa");
		sv = new Stanza("Biblioteca");
	}
	
	@Test
	public void testGetStanzaCorrenteiniziale() {
		assertEquals(si.getNome(), l.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCOrrentediversa() {
		l.setStanzaCorrente(sd);
		assertEquals(sv.getNome(), l.getStanzaVincente().getNome());
	}
	
	@Test
	public void testgetStanzaVincente() {
		assertFalse(l.getStanzaCorrente().getNome()== sd.getNome());
	}
	
}
