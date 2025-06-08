package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.*;
class AbstractComandoTest {
	AbstractComando comando;
	Scanner scannerDiLinee = new Scanner(System.in);
	@BeforeEach
	void setUp()  {
		comando = new FakeComando("fake");
	}
	@Test 
	public void costruttore() {
		assertEquals("fake",comando.getNome());
	}
	@Test
	public void testSetio() {
		 IO io = new IOConsole(scannerDiLinee);
		 comando.setIO(io);
		 assertTrue(comando.getIO()!=null);
	}
	@Test
	public void parametrotest() {
		comando.setParametro("fake");
		assertEquals("fake",comando.getParametro());
	}

}
