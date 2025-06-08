package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import java.util.*;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/


public class Stanza implements Comparable<Stanza>{
	
	private String nome;
	private Map<String , Attrezzo> attrezzi;
	private Map<Direzioni,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
    	this.nome = nome;
    	this.stanzeAdiacenti = new HashMap<>();
    	this.attrezzi = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzioni direzione, Stanza stanzaAdiacente) {
       this.stanzeAdiacenti.put(direzione,stanzaAdiacente);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzioni dir) {
		if(this.stanzeAdiacenti.get(dir)!=null)
			return this.stanzeAdiacenti.get(dir);
		return null;
	}
	
	
	public Map<String,Stanza> getStanzeAdiacenti(){
		Map<String,Stanza> map = new HashMap<String,Stanza>();
		for(Direzioni s: this.stanzeAdiacenti.keySet()) {
			map.put(s.toString(), this.stanzeAdiacenti.get(s));
		}
		return map;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi(){
    	List<Attrezzo> list= new ArrayList<Attrezzo>();
    	list.addAll(this.attrezzi.values());
    	return list;
    }
    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo == null) return false; // Aggiunto controllo null
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    @Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	Set<Direzioni> direzioni = this.stanzeAdiacenti.keySet();
    	for (Direzioni direzione : direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	if(this.attrezzi.isEmpty()) {
    		risultato.append("non c'è niente");
    	}
    	else {
    		for (Attrezzo attrezzo : this.attrezzi.values() ) {
    			if(attrezzi!=null) {
    				risultato.append(attrezzo.toString() + " ");
    			}
    		}
    	}
    	risultato.append("\nPersonaggi nella stanza: ");
    	if(this.getPersonaggio()!=null)
    	risultato.append(this.getPersonaggio().getNome());
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
    public boolean hasAttrezzo(String nomeAttrezzo){
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	/*non considera se esistono più attrezzi dello stesso nome*/
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}


	public List<Direzioni> getDirezioni() {
		Set<Direzioni> iniziali= this.stanzeAdiacenti.keySet(); // mi ricavo le direzioni
		/*List<String >direzioni= new ArrayList<String>();
		for(Direzioni s: iniziali) {
			direzioni.add(s.toString());
		}*/
		List<Direzioni> direzioni = new ArrayList<Direzioni>(iniziali);
		return direzioni;
    }
	@Override
	public int compareTo(Stanza o) {
		Stanza that= (Stanza) o;
		return this.attrezzi.size()-that.getAttrezzi().size();
	}
	@Override 
	public boolean equals(Object o) {
		return this.nome.equals(((Stanza)o).getNome());
	}
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

}