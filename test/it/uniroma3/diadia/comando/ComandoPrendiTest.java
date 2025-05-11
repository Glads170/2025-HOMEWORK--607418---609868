package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;


class ComandoPrendiTest {
	
	Comando comandoprendi;
	DiaDia gioco;
	Partita partita;
	Attrezzo[]contenutoborsa;
	IO io;
	@BeforeEach
	void setUp(){
		io = new IOConsole();
		partita = new Partita();
		comandoprendi=new ComandoPrendi();
		contenutoborsa = partita.getGiocatore().getBorsa().getAttrezzi();
	}

	@Test
	void testossoinatrio() {
		comandoprendi.setParametro("osso");
		comandoprendi.esegui(partita, io);
		assertEquals("osso",contenutoborsa[0].getNome());
	}
	@Test
	void testlanternainn10() {
		Comando comandovai =new ComandoVai();
		comandovai.setParametro("sud");
		comandovai.esegui(partita, io);
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita, io);
		assertEquals("lanterna",contenutoborsa[0].getNome());
	}
	@Test
	void testnull() {
		Comando comandovai =new ComandoVai();
		comandovai.setParametro("ovest");
		comandovai.esegui(partita, io); 
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita, io);
		assertNull(contenutoborsa[0]);
	}

}


