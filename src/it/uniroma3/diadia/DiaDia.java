package it.uniroma3.diadia;



import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.*;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public Partita getPartita() {
		return this.partita;
	}

	public DiaDia(IO console) {
		this.io = console;
		this.partita = new Partita();
	}

	public DiaDia(Labirinto labirinto , IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws Exception{
		String istruzione; 
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   
	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory =new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = factory.costruisciComando(istruzione);
		((AbstractComando) comandoDaEseguire).setIO(io);
		comandoDaEseguire.esegui(this.partita);

		if(this.partita.vinta()) {
			io.mostraMessaggio("hai vinto");
		}
		if(!this.partita.giocatoreIsVIvo()) {
			io.mostraMessaggio("hai esaurito i cfu");
		}
		return this.partita.isFinita();
	}  

	public static void main(String[] argc) throws Exception {
		try(Scanner scannerDiLinee = new Scanner(System.in)) { // Crea e gestisce Scanner con try-with-resource
	        IO io = new IOConsole(scannerDiLinee); // Passa lo Scanner a IOConsole
	        
	         Labirinto labirinto	= new CaricatoreLabirinto("labirinto1.txt").carica();
	        
	        DiaDia gioco = new DiaDia(labirinto, io);
	        gioco.gioca();
	    } // Lo scanner.close() viene chiamato automaticamente qui
		
		
     
				/*
				new LabirintoBuilder()
				.addStanzaIniziale("atrio").addAttrezzo("osso",1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10").addAttrezzo("lanterna",3)
				.addStanzaBloccata("Laboratorio Campus","ovest","osso")
				.addAdiacenza("atrio", "Biblioteca","NORD").addAdiacenza("atrio", "Aula N11","EST").addAdiacenza("atrio", "Laboratorio Campus","OVEST")
				.addAdiacenza("Aula N11","Laboratorio Campus", "EST").addAdiacenza("Aula N10", "Laboratorio Campus", "OVEST").addAdiacenza("Aula N11", "Aula N10", "SUD").getLabirinto();*/

		
	}
}
