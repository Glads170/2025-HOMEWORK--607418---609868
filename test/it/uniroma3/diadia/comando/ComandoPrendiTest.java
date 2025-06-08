package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.Borsa;


class ComandoPrendiTest {
	
	Comando comandoprendi;
	DiaDia gioco;
	Partita partita;
	Borsa borsa;
	Scanner scannerDiLinee = new Scanner(System.in);
	IO io;
	@BeforeEach
	void setUp(){
		io = new IOConsole(scannerDiLinee);
		partita = new Partita();
		comandoprendi=new ComandoPrendi();
		borsa = partita.getGiocatore().getBorsa();
		((AbstractComando)comandoprendi).setIO(io);
	}

	@Test
	void testossoinatrio() {
		comandoprendi.setParametro("osso");
		comandoprendi.esegui(partita);
		assertTrue(borsa.hasAttrezzo("osso"));
	}
	@Test
	void testlanternainn10() {
		Comando comandovai =new ComandoVai();
		((AbstractComando)comandovai).setIO(io);
		comandovai.setParametro("sud");
		comandovai.esegui(partita);
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita);
		assertTrue(borsa.hasAttrezzo("lanterna"));
	}
	@Test
	void testFalse() {
		Comando comandovai =new ComandoVai();
		((AbstractComando)comandovai).setIO(io);
		comandovai.setParametro("ovest");
		comandovai.esegui(partita); 
		comandoprendi.setParametro("lanterna");
		comandoprendi.esegui(partita);
		assertFalse(borsa.hasAttrezzo("lanterna"));
	}

}


