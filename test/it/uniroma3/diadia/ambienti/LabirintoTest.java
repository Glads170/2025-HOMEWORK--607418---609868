package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import it.uniroma3.diadia.*;


class LabirintoTest {
	private Partita p;
	private Labirinto labirinto;
	private Stanza si;
	private Stanza sd;
	private Stanza sv;
	@BeforeEach
	public void setup() {
		p=new Partita();
		labirinto = new Labirinto();
		labirinto.creaStanze();
	}

	@Test
	public void testGetStanzaInizialeDopoCreazione() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	@Test
	void testGetStanzaVincenteDopoCreazione() {
		assertNotNull(labirinto.getStanzaVincente(), "La stanza vincente non dovrebbe essere null.");
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome(), "La stanza vincente dovrebbe essere 'Biblioteca'.");
	}
    @Test
    void testSetStanzaIniziale() {
        Stanza nuovaStanzaIniziale = new Stanza("Ingresso Segreto");
        labirinto.setStanzaIniziale(nuovaStanzaIniziale);
        assertEquals(nuovaStanzaIniziale, labirinto.getStanzaIniziale(), "La stanza iniziale impostata non corrisponde.");
        assertEquals("Ingresso Segreto", labirinto.getStanzaIniziale().getNome(), "Il nome della nuova stanza iniziale non corrisponde.");
    }
	@Test
	public void testGetStanzaCorrentediversa() {
		p.setStanzaCorrente(sd);
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testgetStanzaVincente() {
		assertFalse(p.getStanzaCorrente().getNome()== "Stanza");
	}
    // Test sui collegamenti tra le stanze
    @Test
    void testCollegamentiAtrio() {
        Stanza atrio = labirinto.getStanzaIniziale();
        assertNotNull(atrio, "L'atrio non è stato trovato.");
        assertEquals("Biblioteca", atrio.getStanzaAdiacente("nord").getNome(), "Atrio Nord dovrebbe essere Biblioteca.");
        assertEquals("Aula N11", atrio.getStanzaAdiacente("est").getNome(), "Atrio Est dovrebbe essere Aula N11.");
        assertEquals("Aula N10", atrio.getStanzaAdiacente("sud").getNome(), "Atrio Sud dovrebbe essere Aula N10.");
        assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente("ovest").getNome(), "Atrio Ovest dovrebbe essere Laboratorio Campus.");
    }
    @Test
    void testCollegamentiAulaN11() {
        // Per raggiungere Aula N11 dall'atrio
        Stanza aulaN11 = labirinto.getStanzaIniziale().getStanzaAdiacente("est");
        assertNotNull(aulaN11, "Aula N11 non trovata partendo da Atrio.");
        assertEquals("Aula N11", aulaN11.getNome()); // Verifica aggiuntiva
        assertEquals("Laboratorio Campus", aulaN11.getStanzaAdiacente("est").getNome(), "Aula N11 Est dovrebbe essere Laboratorio Campus.");
        assertEquals("Atrio", aulaN11.getStanzaAdiacente("ovest").getNome(), "Aula N11 Ovest dovrebbe essere Atrio.");
         // Aula N11 non ha uscite a nord o sud secondo la tua implementazione
        assertNull(aulaN11.getStanzaAdiacente("nord"), "Aula N11 Nord dovrebbe essere null.");
        assertNull(aulaN11.getStanzaAdiacente("sud"), "Aula N11 Sud dovrebbe essere null.");
    }
    @Test
    void testPresenzaAttrezziAtrio() {
        Stanza atrio = labirinto.getStanzaIniziale();
        assertTrue(atrio.hasAttrezzo("osso"), "L'atrio dovrebbe contenere 'osso'.");
        assertNotNull(atrio.getAttrezzo("osso"), "'osso' non trovato nell'atrio.");
        assertEquals(1, atrio.getAttrezzo("osso").getPeso(), "Il peso dell'osso dovrebbe essere 1.");
        assertFalse(atrio.hasAttrezzo("lanterna"), "L'atrio non dovrebbe contenere 'lanterna'.");
    }
    
    @Test
    void testPresenzaAttrezziAulaN10() {
        Stanza aulaN10 = labirinto.getStanzaIniziale().getStanzaAdiacente("sud");
        assertNotNull(aulaN10, "Aula N10 non trovata."); // Assicurati che la stanza esista
        assertTrue(aulaN10.hasAttrezzo("lanterna"), "L'Aula N10 dovrebbe contenere 'lanterna'.");
        assertNotNull(aulaN10.getAttrezzo("lanterna"), "'lanterna' non trovata in Aula N10.");
        assertEquals(3, aulaN10.getAttrezzo("lanterna").getPeso(), "Il peso della lanterna dovrebbe essere 3.");
        assertFalse(aulaN10.hasAttrezzo("osso"), "L'Aula N10 non dovrebbe contenere 'osso'.");
    }
    @Test
    void testAssenzaAttrezziLaboratorio() {
        Stanza laboratorio = labirinto.getStanzaIniziale().getStanzaAdiacente("ovest");
        assertNotNull(laboratorio, "Laboratorio non trovato.");
        assertFalse(laboratorio.hasAttrezzo("osso"), "Il Laboratorio non dovrebbe contenere 'osso'.");
        assertFalse(laboratorio.hasAttrezzo("lanterna"), "Il Laboratorio non dovrebbe contenere 'lanterna'.");
    }
}
