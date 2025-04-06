package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;

	public Partita(){
		labirinto = new Labirinto();
		giocatore = new Giocatore();
		labirinto.creaStanze();
		this.finita = false;
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    public Labirinto getLabirinto(){
		return labirinto;
	}
	public void setLabirinto(Labirinto labirinto){
		this.labirinto = labirinto;
	}
	public Giocatore getGiocatore(){
		return giocatore;
	}
	public void setGiocatore(Giocatore giocatore){
		this.giocatore = giocatore;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getLabirinto().getStanzaCorrente()== this.getLabirinto().getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	public String Tostring() {
		return ("stanza corrente:"+this.getLabirinto().getStanzaCorrente().getNome() + "\nCFU:" + this.giocatore.getCfu()) ;
	}
}
