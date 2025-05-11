package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole.*;

class FabbricaDiComandiFisarmonicaTest {
	
	private final FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();

	@Test
	void testCostruisciComandoVai() {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord",comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("prendi",comando.getNome());
		assertEquals("osso",comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		Comando comando = fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto",comando.getNome());
	}
	
	@Test
	void testComandoNonValido() {
		Comando comando = fabbrica.costruisciComando("pippi");
		 assertEquals(ComandoNonValido.class.getName(), comando.getClass().getName());
	}

}
