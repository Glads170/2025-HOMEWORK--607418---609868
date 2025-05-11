package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.*;
class StanzaBuiaTest {
	
	StanzaBuia buia;
	Attrezzo lanterna;
	Attrezzo osso;
	@BeforeEach
	void setUp() throws Exception {
		buia= new StanzaBuia("buia","lanterna");
		lanterna= new Attrezzo("lanterna",2);
		osso= new Attrezzo("osso",3);
		
	}

	@Test
	void testcostruttorevero() {
		assertEquals("lanterna",buia.getattrezzopervedere());
	}
	@Test
	void testcostruttorefalso() {
		assertFalse(buia.getattrezzopervedere().equals("osso"));
	}
	@Test
	void testgetdescrzionesenzaoggetti() {
		assertEquals("qui c'è buio pesto", buia.getDescrizione());
	}
	@Test
	void testgetdescrzioneconoggettosbagliato() {
		buia.addAttrezzo(osso);
		assertEquals("qui c'è buio pesto", buia.getDescrizione());
	}
	@Test
	void testGetDescrizioneQuandoIlluminata() {
		buia.addAttrezzo(lanterna);
		assertFalse(buia.getDescrizione().equals("qui c'è buio pesto"));
	}
	

}