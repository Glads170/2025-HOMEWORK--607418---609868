package it.uniroma3.diadia;
import java.util.Scanner;
public class IOConsole implements IO {
	
	private Scanner scanner;
	
	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	
	// prima creava ogni volta dei flussi diversi di input output per ogni leggi riga invocato , ora la responabilità è del main che inizializza
	// il ciclo a inizio partita e lo chiude alla fine
	public String leggiRiga() {
		String riga = scanner.nextLine();
		//scannerDiLinee.close();  metterlo qua vuol dire chiudere il system.in per sempre
		return riga;
	}
}



