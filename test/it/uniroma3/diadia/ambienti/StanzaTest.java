package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import java.util.*;
public class StanzaTest {
	private Stanza stanzatest1;
	private Stanza stanzatest2;
	private Stanza stanzatest3;
	private Attrezzo osso;
	private Attrezzo arco;
	private Attrezzo spada;
	
	@BeforeEach
	public void setup() {
		this.stanzatest1=new Stanza("stanzatest1");
		this.stanzatest2=new Stanza("stanzatest2");
		this.stanzatest3=new Stanza("stanzatest3");
	    osso=new Attrezzo("osso",3);
		arco=new Attrezzo("arco",4);
		spada=new Attrezzo("spada",7);
	}
	/*test impostastanzaadiacente*/
	@Test
	void testimpostastanzaadiacente1_2vuota() {
		stanzatest1.impostaStanzaAdiacente(Direzioni.NORD, stanzatest2);
		assertEquals(stanzatest2, stanzatest1.getStanzaAdiacente(Direzioni.NORD));
	}

	@Test
	void testimpostastanzaadiacente1_3occupata() {
		final Stanza stanzatest3=new Stanza("stanzatest3");
		stanzatest1.impostaStanzaAdiacente(Direzioni.NORD, stanzatest2);
		stanzatest1.impostaStanzaAdiacente(Direzioni.NORD, stanzatest3);
		assertEquals(stanzatest3, stanzatest1.getStanzaAdiacente(Direzioni.NORD));
	}
	
	/*test hasattrezzo*/
	@Test
	void testhasAttrezzotrue() {
		stanzatest1.addAttrezzo(osso);
		assertEquals(true,stanzatest1.hasAttrezzo("osso"));
	}
	@Test 
	void testhasAttrezzofalse(){
		stanzatest1.addAttrezzo(osso);
		assertEquals(false,stanzatest1.hasAttrezzo("bastone"));
	}
	@Test
	void testhasattrezzocambio() {
		stanzatest1.addAttrezzo(arco);
		stanzatest1.addAttrezzo(spada);
		assertEquals(true,stanzatest1.hasAttrezzo("spada"));
	}
	
	/*test removeAttrezzo*/
	@Test
	void testremoveattrezzopresente() {
		stanzatest1.addAttrezzo(arco);
		stanzatest1.addAttrezzo(osso);
		assertEquals(osso,stanzatest1.removeAttrezzo("osso"));
	}
	@Test
	void testremoveattrezzoassente() {
		stanzatest1.addAttrezzo(spada);
		stanzatest1.addAttrezzo(arco);
		assertEquals(spada,stanzatest1.removeAttrezzo("spada"));
		assertFalse(stanzatest1.getAttrezzi().contains(spada));
	}
	@Test
	void testremoveattrezzovuoto() {
		assertEquals(null,stanzatest1.removeAttrezzo("osso"));
	}
	
	/*test getdirezione*/
	@Test
	void testgetdirezioni1stanza() {
		stanzatest1.impostaStanzaAdiacente(Direzioni.SUD, stanzatest2);
		List<Direzioni> direzioni = stanzatest1.getDirezioni();
		assertTrue(direzioni.contains(Direzioni.SUD));
	}
	@Test
	void testgetdirezioni2stanze() {
		stanzatest1.impostaStanzaAdiacente(Direzioni.SUD, stanzatest2);
		stanzatest1.impostaStanzaAdiacente(Direzioni.SUD, stanzatest3);
		List<Direzioni> direzioni = stanzatest1.getDirezioni();
		assertTrue(direzioni.contains(Direzioni.SUD));
		assertTrue(direzioni.contains(Direzioni.SUD));
		}
	@Test
	void testgetdirezioni0stanze() {
		List<Direzioni> direzioni = stanzatest1.getDirezioni();
	    assertEquals(0, direzioni.size());
	}
}
