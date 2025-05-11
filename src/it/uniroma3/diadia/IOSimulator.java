package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] righeLette;
	private int indiceRigheLette;
	
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	
	public String[] getMessaggiProdotti() {
		return messaggiProdotti;
	}
	public void setMessaggiProdotti(String[] messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}
	
	public IOSimulator(String[] righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.messaggiProdotti = new String[42*23];
		this.indiceRigheLette = 0;
		this.indiceMessaggiProdotti=0;
	}
	
	@Override
	public String leggiRiga() {
	    if (indiceRigheLette >= righeLette.length) {
	        return ""; 
	    }
	    return righeLette[indiceRigheLette++];
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
	    if (indiceMessaggiProdotti >= messaggiProdotti.length) {
	        return;
	    }
	    this.messaggiProdotti[indiceMessaggiProdotti++] = messaggio;
	}
	
	
	
}