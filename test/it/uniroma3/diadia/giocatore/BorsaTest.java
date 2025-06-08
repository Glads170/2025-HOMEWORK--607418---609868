package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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
	@Test
	public void testgetcontenutoordinatoperpeso() {
		Attrezzo clava = new Attrezzo("clava",2);
		Attrezzo lampada = new Attrezzo("lampada",1);
		ascia.setPeso(2);
		spadone.setPeso(4);
		b.addAttrezzo(ascia);
		b.addAttrezzo(spadone);
		b.addAttrezzo(clava);
		b.addAttrezzo(lampada);
		List<Attrezzo> a=b.getContenutoOrdinatoPerPeso();
		assertEquals(lampada,a.get(0));
		assertEquals(ascia,a.get(1));
		assertEquals(clava,a.get(2));
		assertEquals(spadone,a.get(3));
		}
	@Test
	public void testgetcontenutoordinatopernome() {
		Attrezzo clava = new Attrezzo("clava",2);
		Attrezzo lampada = new Attrezzo("lampada",1);
		ascia.setPeso(2);
		spadone.setPeso(4);
		b.addAttrezzo(ascia);
		b.addAttrezzo(spadone);
		b.addAttrezzo(clava);
		b.addAttrezzo(lampada);
		
		SortedSet<Attrezzo> a=b.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= a.iterator();
		assertEquals(ascia,it.next());
		assertEquals(clava,it.next());
		assertEquals(lampada,it.next());
		assertEquals(spadone,it.next());
		}
	@Test
	public void testgetcontenutoraggruppatoperpeso() {
		Attrezzo clava = new Attrezzo("clava",2);
		Attrezzo lampada = new Attrezzo("lampada",1);
		ascia.setPeso(2);
		spadone.setPeso(4);
		b.addAttrezzo(ascia);
		b.addAttrezzo(spadone);
		b.addAttrezzo(clava);
		b.addAttrezzo(lampada);
		
		Map<Integer,Set<Attrezzo>> a=b.getContenutoRaggruppatoPerPeso();
		assertEquals(3,a.size());
		assertTrue(a.containsKey(1));
		assertTrue(a.containsKey(2));
		assertTrue(a.containsKey(4));
		}
	@Test
	public void testgetSortedSetOrdinatoPerPeso() {
		Attrezzo clava = new Attrezzo("clava",2);
		Attrezzo lampada = new Attrezzo("lampada",1);
		ascia.setPeso(2);
		spadone.setPeso(4);
		b.addAttrezzo(ascia);
		b.addAttrezzo(spadone);
		b.addAttrezzo(clava);
		b.addAttrezzo(lampada);
		
		SortedSet<Attrezzo> a=b.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it= a.iterator();
		assertEquals(lampada,it.next());
		assertEquals(ascia,it.next());
		assertEquals(clava,it.next());
		assertEquals(spadone,it.next());
		}
}

