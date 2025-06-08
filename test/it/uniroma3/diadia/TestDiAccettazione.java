package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.giocatore.Giocatore;

import java.util.*;
class TestDiAccettazione  {
	private Partita p;
    private Labirinto l;
    private Giocatore g;
	@BeforeEach
	public void setup(){
		l = new LabirintoBuilder()
				.addStanzaIniziale("atrio").addAttrezzo("osso",1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10").addAttrezzo("lanterna",3)
				.addStanzaBloccata("Laboratorio Campus","ovest","osso")
				.addAdiacenza("atrio", "Biblioteca","NORD").addAdiacenza("atrio", "Aula N11","EST").addAdiacenza("atrio", "Laboratorio Campus","OVEST")
				.addAdiacenza("Aula N11","Laboratorio Campus", "EST").addAdiacenza("Aula N10", "Laboratorio Campus", "OVEST").addAdiacenza("Aula N11", "Aula N10", "SUD").getLabirinto();
         
	}
	

	@Test
    void testPartitaVittoria() throws Exception {
		List <String> lineedaleggere= Arrays.asList("guarda","prendi osso","vai ovest","vola","posa osso","vai est","vai nord"); 
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(l,io);
		gioco.gioca(); 

        // Verifica che i messaggi siano stati prodotti
        List<String> messaggi = io.getMessaggiProdotti();
 
        // Possiamo cercare un messaggio chiave, ad esempio:
        boolean contieneMessaggioFinale = false;
        for (String m : messaggi) {
            if (m != null && m.toLowerCase().contains("hai vinto")) {
                contieneMessaggioFinale = true;
                break; 
            }
        }
        assertTrue(contieneMessaggioFinale);
    }
	
	@Test
	void testStanzaBloccata() throws Exception {
		List <String> lineedaleggere= Arrays.asList("guarda","prendi osso","vai ovest","fine");
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(l,io);
		gioco.gioca(); 
		
		List <String>  messaggi = io.getMessaggiProdotti();
	
	    boolean contieneBloccata = false;
	    for (String m : messaggi) {
	        if (m != null && m.contains("bloccata")) {
	        	contieneBloccata = true;
	            break;
	        }
	    }
	    assertTrue(contieneBloccata);
	}
	
	@Test
	void testMessaggiodiBenveuto() throws Exception {
		List<String> lineedaleggere= Arrays.asList("fine");
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(l,io);
		gioco.gioca();  
		
	    List <String> messaggi = io.getMessaggiProdotti();
	
	    boolean contieneiniziale = false;
	    for (String m : messaggi) {
	        if (m != null && m.contains("Ti trovi nell'Universita'")) {
	            contieneiniziale = true;
	            break;
	        }
	    }
	    assertTrue(contieneiniziale);
	}
	
	@Test
	void testMessaggioComandononvalido() throws Exception {
		List <String> lineedaleggere= Arrays.asList("vola","fine");
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(l,io);
		gioco.gioca();  
		
		
		List <String> messaggi = io.getMessaggiProdotti();
	   
	    boolean contienecomandononvalido = false;
	    for (String m : messaggi) {
	        if (m != null && m.contains("comando non valido")) {
	        	contienecomandononvalido = true;
	            break;
	        }
	    }
	    assertTrue(contienecomandononvalido);
	}
	
}
	



