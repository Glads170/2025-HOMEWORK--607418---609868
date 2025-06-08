package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.giocatore.*;

public class PartitaTest {
	private Partita p;
    private Labirinto l;
    private Giocatore g;
    

    @BeforeEach
    public void setUp() {
        l = new LabirintoBuilder()
				.addStanzaIniziale("atrio").addAttrezzo("osso",1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10").addAttrezzo("lanterna",3)
				.addStanzaBloccata("Laboratorio Campus","ovest","osso")
				.addAdiacenza("atrio", "Biblioteca","NORD").addAdiacenza("atrio", "Aula N11","EST").addAdiacenza("atrio", "Laboratorio Campus","OVEST")
				.addAdiacenza("Aula N11","Laboratorio Campus", "EST").addAdiacenza("Aula N10", "Laboratorio Campus", "OVEST").addAdiacenza("Aula N11", "Aula N10", "SUD").getLabirinto();
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
        p.setStanzaCorrente(stanzaVincente);
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
	    p.setStanzaCorrente(stanza1);
	    assertFalse(p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteVincente() {
	    p.setStanzaCorrente(l.getStanzaVincente());
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
