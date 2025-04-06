package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

public class BorsaTest {
	private Attrezzo a;
	private Borsa b;
	private Attrezzo ascia ;
	private Attrezzo spadone;
	
	@BeforeEach
	public void setup(){
		a = new Attrezzo("prova", 2);
		b = new Borsa(10);
		ascia = new Attrezzo("ascia", 5);
		spadone = new Attrezzo("spadone", 12);
	}
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(b.addAttrezzo(a));
	}
	
	@Test
	public void testAddAttrezzotroppopesante() {
		assertFalse(b.addAttrezzo(spadone));
	}
	
	@Test
	public void testAddAttrezzoDiversoNull() {
		assertNotNull(b.addAttrezzo(a));
	}
	
	@Test
	public void testGetPesoMax() {
		assertFalse(b.getPesoMax()== 5);		
	}
	
	@Test
	public void testAddAttrezzoPesoMinoreDiDieci() {
		assertTrue(b.addAttrezzo(ascia));
	}
	
	@Test
	public void testAddAttrezzoPesoMaggioreDiDieci() {
		assertFalse(b.addAttrezzo(spadone));
	}
	
	@Test
	public void testremoveAttrezzosi() {
		b.addAttrezzo(ascia);
		assertEquals(ascia,b.removeAttrezzo("ascia"));
	}
	@Test
	public void testremoveAttrezzono() {
		assertNull(b.removeAttrezzo("ascia"));
	}

}

