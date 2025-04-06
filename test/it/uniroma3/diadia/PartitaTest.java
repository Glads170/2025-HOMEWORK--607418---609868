package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

public class PartitaTest {
	private Partita p;
    private Labirinto l;
    private Giocatore g;
    

    @BeforeEach
    public void setUp() {
        l = new Labirinto();
        l.creaStanze();
        p = new Partita();
        p.setLabirinto(l);
        g = p.getGiocatore();
    }
	
	//Test per il metoodo isFinita
	@Test 
	public void testPartitaNonFinitaAllInizio() {
		assertFalse(p.isFinita());
	}
	
	@Test
	public void testPartitaFinitaQuandoVinta() {
		Stanza stanzaVincente = l.getStanzaVincente();
        p.getLabirinto().setStanzaCorrente(stanzaVincente);
        assertTrue(p.isFinita());
	}
	
	@Test
    public void testPartitaFinitaQuandoZeroCfu() {
        g.setCfu(0); 
        assertTrue(p.isFinita());
    }
	// test vinta
	@Test
	public void testVintaDopoCreazione() {
	    assertFalse(p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteNonVincente() {
		Stanza stanza1 = new Stanza("stanza 1");
	    p.getLabirinto().setStanzaCorrente(stanza1);
	    assertFalse(p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteVincente() {
	    p.getLabirinto().setStanzaCorrente(l.getStanzaVincente());
	    assertTrue(p.vinta());
	}
	//Test per il metodo getLabirinto
	@Test
	public void testGetLabirinto() {
		assertEquals(l, p.getLabirinto());
	}
	
	//Test per il metodo getGiocatore
	@Test
	public void tesGetGiocatore() {
		assertEquals(g, p.getGiocatore());
	}
	

}
