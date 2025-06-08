package it.uniroma3.diadia;
import java.util.*;
public class IOSimulator implements IO{
	private List<String> righeLette=new ArrayList<String>();
	int contatore=0;
	private List<String> messaggiProdotti = new ArrayList<String>();;

	public List<String> getMessaggiProdotti() {
		return messaggiProdotti;
	}
	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	public IOSimulator(List <String> righeDaLeggere) {
		this.righeLette.addAll(righeDaLeggere);
	}

	@Override
	public String leggiRiga() {
		if (contatore < righeLette.size()) {
			return righeLette.get(contatore++);
		}
		return null;
	}


	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
	}
}