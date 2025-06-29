package it.uniroma3.diadia.comando;

import java.util.Scanner;

import it.uniroma3.diadia.IOConsole.*;
import it.uniroma3.diadia.*;
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi { 
	
	public Comando costruisciComando(String istruzione) throws Exception {
		Scanner scannerDiParole = new Scanner(istruzione);
		// es. ‘vai sud’   
		String nomeComando = null; 
		// es. ‘vai’   
		String parametro = null;
		// es. ‘sud’      
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		try {
			//seconda parola: eventuale parametro
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comando.Comando");
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse.append( nomeComando.substring(1) ) ;
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
		} 
		catch (Exception e) { 
			comando = new ComandoNonValido();
			comando.setParametro(parametro);
		}
		return comando; 
	}  
}
