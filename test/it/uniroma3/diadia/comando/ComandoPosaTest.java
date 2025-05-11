package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	Comando comandoprendi;
	Comando comandoposa;
	DiaDia gioco;
	Partita partita;
	Attrezzo[]contenutoborsa;
	IO io;
	@BeforeEach
	void setUp(){
		io = new IOConsole();
		partita = new Partita();
		comandoprendi=new ComandoPrendi();
		comandoposa= new ComandoPosa();
		contenutoborsa = partita.getGiocatore().getBorsa().getAttrezzi();
		
	}

	@Test
	void testossoinatrio() {
		comandoprendi.setParametro("osso");
		comandoprendi.esegui(partita, io);
		comandoposa.setParametro("osso");
		comandoposa.esegui(partita, io);
		assertEquals("osso",partita.getStanzaCorrente().getAttrezzi()[0].getNome());
	}
	@Test
	void testlanterna_da_n10_a_atrio() {
		Comando comandovai =new ComandoVai();
		comandovai.setParametro("sud");
		comandovai.esegui(partita, io);
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita, io);
		comandovai.setParametro("nord");
		comandovai.esegui(partita, io);
		comandoposa.setParametro("lanterna");
		comandoposa.esegui(partita, io);
		assertEquals("lanterna",partita.getStanzaCorrente().getAttrezzi()[1].getNome());
	}
	@Test
	void testnull() {
		Comando comandovai =new ComandoVai();
		comandovai.setParametro("ovest");
		comandovai.esegui(partita, io); 
		comandoposa.setParametro("lanterna");
		comandoposa.esegui(partita, io);
		assertNull(partita.getStanzaCorrente().getAttrezzi()[1]);
	}
}
