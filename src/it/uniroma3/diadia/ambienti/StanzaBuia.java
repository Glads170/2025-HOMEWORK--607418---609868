package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String attrezzopervedere;
	
	public String getattrezzopervedere() {
		return this.attrezzopervedere;
	}
	public void setattrezzopervedere(String parametro) {
		this.attrezzopervedere=parametro;
	}

	public StanzaBuia(String nome,String parametro) {
		super(nome);
		this.attrezzopervedere=parametro;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzopervedere)) {
			return super.getDescrizione();
		}
		else {
			String a = "qui c'è buio pesto";
			return a;
		}
	}
}
