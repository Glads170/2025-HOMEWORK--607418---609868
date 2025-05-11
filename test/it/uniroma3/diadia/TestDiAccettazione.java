package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class TestDiAccettazione {

	@Test
    void testPartitaVittoria() {
		String[] lineedaleggere= {"guarda","prendi osso","vai ovest","vola","posa osso","vai est","vai nord"};
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 

		
		
        // Verifica che i messaggi siano stati prodotti
        String[] messaggi = io.getMessaggiProdotti();
 
        // Possiamo cercare un messaggio chiave, ad esempio:
        boolean contieneMessaggioFinale = false;
        for (String m : messaggi) {
            if (m != null && m.contains("hai vinto")) {
                contieneMessaggioFinale = true;
                break; 
            }
        }
        assertTrue(contieneMessaggioFinale);
    }
	
	@Test
	void testStanzaBloccata() {
		String[] lineedaleggere= {"guarda","prendi osso","vai ovest","fine"};
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		
		
	    String[] messaggi = io.getMessaggiProdotti();
	
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
	void testMessaggiodiBenveuto() {
		String[] lineedaleggere= {"fine"};
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
	    String[] messaggi = io.getMessaggiProdotti();
	
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
	void testMessaggioComandononvalido() {
		String[] lineedaleggere= {"vola","fine"};
		IOSimulator io = new IOSimulator(lineedaleggere); 
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		
	    String[] messaggi = io.getMessaggiProdotti();
	   
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


