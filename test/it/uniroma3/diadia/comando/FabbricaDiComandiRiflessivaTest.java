package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole.*;

class FabbricaDiComandiRiflessivaTest {
	
	private final FabbricaDiComandiRiflessiva fabbrica = new FabbricaDiComandiRiflessiva();

	@Test
	void testCostruisciComandoVai() throws Exception {
		Comando comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord",comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoPrendi() throws Exception {
		Comando comando = fabbrica.costruisciComando("prendi osso");
		assertEquals("prendi",comando.getNome());
		assertEquals("osso",comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoAiuto() throws Exception {
		Comando comando = fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto",comando.getNome());
	}
	
	@Test
	void testComandoNonValido() throws Exception {
		Comando comando = fabbrica.costruisciComando("pippi");
		 assertEquals(ComandoNonValido.class.getName(), comando.getClass().getName());
	}

}
