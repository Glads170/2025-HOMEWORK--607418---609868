package it.uniroma3.diadia.comando;
import it.uniroma3.diadia.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.*;
class ComandoPosaTest {

	Comando comandoprendi;
	Comando comandoposa;
	DiaDia gioco;
	Partita partita;
	Collection<Attrezzo> contenutoborsa;
	IO io;
	Scanner scannerDiLinee = new Scanner(System.in);
	@BeforeEach
	void setUp(){
		
		io = new IOConsole(scannerDiLinee);
		partita = new Partita();
		comandoprendi = new ComandoPrendi();
		comandoposa = new ComandoPosa();
		contenutoborsa = partita.getGiocatore().getBorsa().getAttrezzi();
		((AbstractComando)comandoprendi).setIO(io);
		((AbstractComando)comandoposa).setIO(io);
	}

	@Test
	void testossoinatrio() {
		comandoprendi.setParametro("osso");
		comandoprendi.esegui(partita);
		comandoposa.setParametro("osso");
		comandoposa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	@Test
	void testlanterna_da_n10_a_atrio() {
		Comando comandovai =new ComandoVai();
		((AbstractComando)comandovai).setIO(io);
		comandovai.setParametro("sud");
		comandovai.esegui(partita);
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita);
		comandovai.setParametro("nord");
		comandovai.esegui(partita);
		comandoposa.setParametro("lanterna");
		comandoposa.esegui(partita);
		assertEquals("lanterna",partita.getStanzaCorrente().getAttrezzo("lanterna").getNome());
	}
	@Test
	void testNull() {
		Comando comandovai =new ComandoVai();
		((AbstractComando)comandovai).setIO(io);
		comandovai.setParametro("ovest");
		comandovai.esegui(partita); 
		comandoposa.setParametro("lanterna");
		comandoposa.esegui(partita);
		assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"));
	}
}
