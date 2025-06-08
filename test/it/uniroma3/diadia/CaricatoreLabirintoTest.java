package it.uniroma3.diadia;


import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comando.AbstractComando;
import it.uniroma3.diadia.comando.ComandoPrendi;
import it.uniroma3.diadia.comando.ComandoVai;
class CaricatoreLabirintoTest {

	Partita part;
	ComandoVai comandoVai;
	ComandoPrendi comandoPrendi;
	IO io;
	Scanner scannerDiLinee = new Scanner(System.in);
	String monolocale="""
						Stanze:Normale biblioteca
						Inizio:biblioteca
						Vincente:biblioteca
						Attrezzi:martello 10 biblioteca
						Personaggi:
						Uscite:
						""";
	String bilocale = """
						Stanze:Normale biblioteca,Normale atrio
						Inizio:atrio
						Vincente:biblioteca
						Attrezzi:martello 10 biblioteca,foglio 2 atrio
						Personaggi:
						Uscite:biblioteca nord atrio
						""";
	
	String quadrilocale = """
						Stanze:Normale biblioteca,Normale aulaN11,Normale atrio,Normale salagiochi
						Inizio:biblioteca
						Vincente:salagiochi
						Attrezzi:martello 10 biblioteca,foglio 2 atrio, spada 20 salagiochi
						Personaggi:
						Uscite:biblioteca nord aulaN11,aulaN11 ovest atrio,aulaN11 est salagiochi
						""";
	@BeforeEach
	void setUp() {
		comandoVai = new ComandoVai();
		io = new IOConsole(scannerDiLinee);
		((AbstractComando)comandoVai).setIO(io);
	}
    //test monolocale
	@Test
	public void testmonolocalestanzainizale() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		Labirinto lab = caricatore.carica();
		assertEquals("biblioteca",lab.getStanzaIniziale().getNome());
	}
	@Test 
	public void testmonolocalestanzavincente() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		Labirinto lab = caricatore.carica();
		assertEquals("biblioteca",lab.getStanzaVincente().getNome());
	}
	@Test
	public void testmonolocaleattrezzo() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		Labirinto lab = caricatore.carica();
		assertEquals("martello",lab.getStanzaVincente().getAttrezzi().get(0).getNome());
	}
	
	
	
	//test bilocale
	@Test
	public void testbilocalestanzainizale() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		Labirinto lab = caricatore.carica();
		assertEquals("atrio",lab.getStanzaIniziale().getNome());
	}
	@Test 
	public void testbilocalestanzavincente() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		Labirinto lab = caricatore.carica();
		assertEquals("biblioteca",lab.getStanzaVincente().getNome());
	}
	@Test
	public void testbilocaleattrezzo() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		Labirinto lab = caricatore.carica();
		
		assertEquals("foglio",lab.getStanzaIniziale().getAttrezzi().get(0).getNome());
		assertEquals(2,lab.getStanzaIniziale().getAttrezzi().get(0).getPeso());
	}
	@Test
	public void testbilocaleadiacenza() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		Labirinto lab = caricatore.carica();
		assertEquals("biblioteca",lab.getStanzaIniziale().getStanzaAdiacente(Direzioni.SUD).getNome());
	}
	
	
	//test quadrilocale
	@Test
	public void testquadrilocalestanzainizale() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(quadrilocale));
		Labirinto lab = caricatore.carica();
		assertEquals("biblioteca",lab.getStanzaIniziale().getNome());
	}
	@Test 
	public void testquadrilocalestanzavincente() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(quadrilocale));
		Labirinto lab = caricatore.carica();
		assertEquals("salagiochi",lab.getStanzaVincente().getNome());
	}
	@Test
	public void testquadrilocaleattrezzo() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(quadrilocale));
		Labirinto lab = caricatore.carica();
		Stanza Salagiochi = lab.getStanzaIniziale().getStanzaAdiacente(Direzioni.NORD).getStanzaAdiacente(Direzioni.EST);
		assertEquals("spada",Salagiochi.getAttrezzi().get(0).getNome());
		assertEquals(20,Salagiochi.getAttrezzi().get(0).getPeso());
	}
	@Test
	public void testquadrilocaleadiacenza() throws FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(quadrilocale));
		Labirinto lab = caricatore.carica();
		Stanza stanza = lab.getStanzaIniziale().getStanzaAdiacente(Direzioni.NORD);
		assertEquals("aulaN11",stanza.getNome());
		stanza = stanza.getStanzaAdiacente(Direzioni.EST);
		assertEquals("salagiochi",stanza.getNome());
		stanza = stanza.getStanzaAdiacente(Direzioni.OVEST);
		stanza = stanza.getStanzaAdiacente(Direzioni.OVEST);
		assertEquals("atrio",stanza.getNome());
	}
}
