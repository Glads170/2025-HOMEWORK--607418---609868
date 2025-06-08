package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;;
class ComandoVaiTest {
	Partita part;
	ComandoVai comandoVai;
	ComandoPrendi comandoPrendi;
	IO io;
	Labirinto bilocale;
	Labirinto trilocale;
	Labirinto quadrilocale;
	Scanner scannerDiLinee = new Scanner(System.in);
	@BeforeEach
	void setUp() {
		comandoVai = new ComandoVai();
		io = new IOConsole(scannerDiLinee);
		((AbstractComando)comandoVai).setIO(io);
		bilocale =new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("sala giochi")
				.addAttrezzo("candela", 3)
				.addAdiacenza("atrio", "sala giochi", "nord")
				.getLabirinto();
		trilocale = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("sala giochi")
				.addStanzaMagica("magica", 1)
				.addAttrezzo("candela", 3)
				.addAdiacenza("atrio", "magica", "nord")
				.addAdiacenza("magica","sala giochi" ,"est")
				.getLabirinto();
		quadrilocale = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("sala giochi")
				.addStanza("intermedia")
				.addStanzaMagica("magica", 1)
				.addAttrezzo("candela", 3)
				.addAdiacenza("atrio", "magica", "nord")
				.addAdiacenza("magica","sala giochi" ,"est")
				.addAdiacenza("sala giochi", "intermedia", "sud")
				.addAdiacenza("intermedia","atrio" ,"ovest")
				.getLabirinto();	
	}
	@Test
	void testmonolocale() {
		part= new Partita(bilocale);
		comandoVai.setParametro("nord");
		comandoVai.esegui(part);
		assertEquals("sala giochi",part.getStanzaCorrente().getNome());
	}

	@Test
	void testnordest() {
		part= new Partita(trilocale );
		part.setLabirinto(trilocale );
		comandoVai.setParametro("nord");
		comandoVai.esegui(part);
		comandoVai.setParametro("est");
		comandoVai.esegui(part);
		assertEquals("sala giochi",part.getStanzaCorrente().getNome());
	}
	@Test
	void testnordestsud() {
		part= new Partita(quadrilocale);
		part.setLabirinto(quadrilocale);
		comandoVai.setParametro("nord");
		comandoVai.esegui(part);
		comandoVai.setParametro("est");
		comandoVai.esegui(part);
		comandoVai.setParametro("sud");
		comandoVai.esegui(part);
		assertEquals("intermedia",part.getStanzaCorrente().getNome());
	}
	@Test
	void testnordestovestsud() {
		part= new Partita(quadrilocale);
		part.setLabirinto(quadrilocale);
		comandoVai.setParametro("nord");
		comandoVai.esegui(part);
		comandoVai.setParametro("est");
		comandoVai.esegui(part);
		comandoVai.setParametro("ovest");
		comandoVai.esegui(part);
		comandoVai.setParametro("sud");
		comandoVai.esegui(part);
		assertEquals("atrio",part.getStanzaCorrente().getNome());
	}
}
