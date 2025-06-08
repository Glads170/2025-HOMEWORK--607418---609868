package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.*;

class StanzaMagicaTst {
	StanzaMagica camera;
	Attrezzo candela;
	@BeforeEach
	void setUp() throws Exception {
		camera= new StanzaMagica("camera");
		candela=new Attrezzo("candela",4);
	}

	@Test
	void testcostruttoresenzasoglia() {
		camera= new StanzaMagica("camera");
		assertEquals(camera.getsogliamagica(),3);
	}
	@Test
	void testcostruttoreconsoglia() {
		camera= new StanzaMagica("camera",7);
		assertEquals(camera.getsogliamagica(),7);
	}
	@Test
	void addattrezzodoppotrevolte() {
		 camera.addAttrezzo(candela);
		 camera.removeAttrezzo("candela");
		 camera.addAttrezzo(candela);
		 camera.removeAttrezzo("candela");
		 camera.addAttrezzo(candela);
		 camera.removeAttrezzo("candela");
		 camera.addAttrezzo(candela);
		 assertTrue(camera.hasAttrezzo("alednac"));
		 assertEquals(8,camera.getAttrezzo("alednac").getPeso());
	}
	@Test
	void addattrezzosemplice() {
		 camera.removeAttrezzo("candela");
		 camera.addAttrezzo(candela);
		 assertTrue(camera.hasAttrezzo("candela"));
		 assertEquals(4,camera.getAttrezzo("candela").getPeso());
	}
	

}
