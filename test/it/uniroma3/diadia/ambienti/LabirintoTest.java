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
	
	private Labirinto labirinto;
	
	@BeforeEach
	public void setup() {
		labirinto = new Labirinto.LabirintoBuilder()
		.addStanzaIniziale("atrio").addAttrezzo("osso",1)
		.addStanzaVincente("Biblioteca")
		.addStanza("Aula N11")
		.addStanza("Aula N10").addAttrezzo("lanterna",3)
		.addStanzaBloccata("Laboratorio Campus","ovest","osso")
		.addAdiacenza("atrio", "Biblioteca","NORD").addAdiacenza("atrio", "Aula N11","EST").addAdiacenza("atrio", "Laboratorio Campus","OVEST")
		.addAdiacenza("Aula N11","Laboratorio Campus", "EST").addAdiacenza("Aula N10", "Laboratorio Campus", "OVEST").addAdiacenza("Aula N11", "Aula N10", "SUD").getLabirinto();

	}

	@Test
	public void testGetStanzaInizialeDopoCreazione() {
		assertEquals("atrio", labirinto.getStanzaIniziale().getNome());//get descrizione non va  bene non so  che mettere
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

    // Test sui collegamenti tra le stanze
    @Test
    void testCollegamentiAtrio() {
        Stanza atrio = labirinto.getStanzaIniziale();
        assertNotNull(atrio, "L'atrio non è stato trovato.");
        assertEquals("Biblioteca", atrio.getStanzaAdiacente(Direzioni.NORD).getNome(), "Atrio Nord dovrebbe essere Biblioteca.");
        assertEquals("Aula N11", atrio.getStanzaAdiacente(Direzioni.EST).getNome(), "Atrio Est dovrebbe essere Aula N11.");
        assertEquals("Laboratorio Campus", atrio.getStanzaAdiacente(Direzioni.OVEST).getNome(), "Atrio Ovest dovrebbe essere Laboratorio Campus.");
    }
    @Test
    void testCollegamentiAulaN11() {
        // Per raggiungere Aula N11 dall'atrio
        Stanza aulaN11 = labirinto.getStanzaIniziale().getStanzaAdiacente(Direzioni.EST);
        assertNotNull(aulaN11, "Aula N11 non trovata partendo da Atrio.");
        assertEquals("Aula N11", aulaN11.getNome()); // Verifica aggiuntiva
        assertEquals("Laboratorio Campus", aulaN11.getStanzaAdiacente(Direzioni.EST).getNome(), "Aula N11 Est dovrebbe essere Laboratorio Campus.");
        assertEquals("atrio", aulaN11.getStanzaAdiacente(Direzioni.OVEST).getNome(), "Aula N11 Ovest dovrebbe essere Atrio.");
         // Aula N11 non ha uscite a nord o sud secondo la tua implementazione
        assertNull(aulaN11.getStanzaAdiacente(Direzioni.NORD), "Aula N11 Nord dovrebbe essere null.");
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
        Stanza campus = labirinto.getStanzaIniziale().getStanzaAdiacente(Direzioni.OVEST);
        Stanza aulaN10= campus.getStanzaAdiacente(Direzioni.EST);
        assertTrue(aulaN10.hasAttrezzo("lanterna"), "L'Aula N10 dovrebbe contenere 'lanterna'.");
        assertNotNull(aulaN10.getAttrezzo("lanterna"), "'lanterna' non trovata in Aula N10.");
        assertEquals(3, aulaN10.getAttrezzo("lanterna").getPeso(), "Il peso della lanterna dovrebbe essere 3.");
        assertFalse(aulaN10.hasAttrezzo("osso"), "L'Aula N10 non dovrebbe contenere 'osso'.");
    }
    @Test
    void testAssenzaAttrezziLaboratorio() {
        Stanza laboratorio = labirinto.getStanzaIniziale().getStanzaAdiacente(Direzioni.OVEST);
        assertNotNull(laboratorio, "Laboratorio non trovato.");
        assertFalse(laboratorio.hasAttrezzo("osso"), "Il Laboratorio non dovrebbe contenere 'osso'.");
        assertFalse(laboratorio.hasAttrezzo("lanterna"), "Il Laboratorio non dovrebbe contenere 'lanterna'.");
    }
}
