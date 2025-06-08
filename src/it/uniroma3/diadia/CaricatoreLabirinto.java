package it.uniroma3.diadia;
import it.uniroma3.diadia.personaggi.*;

import java.io.*;
import java.net.URL;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	private static final String STANZA_MAGICA_MARKER = "Magica"; 

	private static final String STANZA_BUIA_MARKER = "Buia";  

	private static final String STANZA_BLOCCATA_MARKER = "Bloccata";

	private static final String STANZA_NORMALE_MARKER = "Normale";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String PERSONAGGI_MARKER= "Personaggi:";

	private static final String MAGO_MARKER= "Mago";

	private static final String CANE_MARKER= "Cane";

	private static final String STREGA_MARKER= "Strega";


	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder labinprogress  = new LabirintoBuilder();

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		URL url = getClass().getResource("/" + nomeFile); // oppure NomeClasse.class.getResource(nomeFile)
		if (url == null) {
			throw new FileNotFoundException("File non trovato: " + nomeFile);
		}
		this.reader = new LineNumberReader(new FileReader(url.getFile())); // oppure url.getPath()
	}
	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
	}


	public Labirinto carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaPersonaggi();
			this.leggiEImpostaUscite();
			return this.labirintoFinale();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		String nomeStanza= null;
		String tipoStanza = null;
		for(String nomeStanzacorr : separaStringheAlleVirgole(nomiStanze)) {
			try (Scanner scannerLinea = new Scanner(nomeStanzacorr)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("tipo di stanza inesistente"));
				tipoStanza = scannerLinea.next();
				nomeStanza = scannerLinea.next();
				if(tipoStanza.equals(STANZA_NORMALE_MARKER)) {
					labinprogress.addStanza(nomeStanza);
				}
				if(tipoStanza.equals(STANZA_MAGICA_MARKER)) {
					if(scannerLinea.hasNext()) {
						labinprogress.addStanzaMagica(nomeStanza,Integer.parseInt(scannerLinea.next()));
					}
					else
						labinprogress.addStanzaMagica(nomeStanza);
				}
				if(tipoStanza.equals(STANZA_BUIA_MARKER)) {
					labinprogress.addStanzaBuia(nomeStanza, scannerLinea.next());
				}
				if(tipoStanza.equals(STANZA_BLOCCATA_MARKER)) {
					labinprogress.addStanzaBloccata(nomeStanza, scannerLinea.next(), scannerLinea.next());
				}
			}			
		}

	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while(scanner.hasNext())
			result.add(scanner.next());

		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		labinprogress.addStanzaIniziale(nomeStanzaIniziale);
		labinprogress.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			labinprogress.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {

		return labinprogress.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER).trim();
		if (specificheUscite.isEmpty()) return; // nessuna uscita da elaborare
		for(String s : separaStringheAlleVirgole(specificheUscite))
			try (Scanner scannerDiLinea = new Scanner(s)) {			
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
	}
	private void leggiECreaPersonaggi() throws FormatoFileNonValidoException  {
		String personaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String Personaggio : separaStringheAlleVirgole(personaggi)) {
			try (Scanner scannerLinea = new Scanner(Personaggio)) {
				String tipoPersonaggio = scannerLinea.next();
				String NomeStanza = scannerLinea.next();
				String nomePersonaggio = scannerLinea.next();
				String Descrizione = scannerLinea.next();
				AbstractPersonaggio personaggio=null;
				if(tipoPersonaggio.equals(MAGO_MARKER)) {
					String attrezzo=scannerLinea.next();
					String peso = scannerLinea.next();
					personaggio = new Mago(nomePersonaggio,Descrizione,new Attrezzo(attrezzo,Integer.parseInt(peso)));
				}
				if(tipoPersonaggio.equals(STREGA_MARKER)) {
					personaggio = new Strega(nomePersonaggio,Descrizione);
				}
				if(tipoPersonaggio.equals(CANE_MARKER)) {
					String attrezzo=scannerLinea.next();
					String peso = scannerLinea.next();
					personaggio = new Cane(nomePersonaggio,Descrizione,new Attrezzo (attrezzo,Integer.parseInt(peso)));
				}
				labinprogress.getListaStanze().get(NomeStanza).setPersonaggio(personaggio);
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		labinprogress.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	public Labirinto labirintoFinale() {
		Labirinto lab=labinprogress.getLabirinto();
		return lab;
	}
}