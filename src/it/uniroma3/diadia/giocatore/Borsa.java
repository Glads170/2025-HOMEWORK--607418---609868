package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;

public class Borsa{
	public final static int DEFAULT_PESO_MAX_BORSA =  Configuratore.getPesoMax();
	private Map<String , Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private int pesoMax;

	public Borsa(){
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax){
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
		//this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		if(this.getPeso()+attrezzo.getPeso() <= pesoMax) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;			
	}

	public int getPesoMax(){
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso(){
		int peso=0;
		for(Attrezzo a: this.attrezzi.values())
			peso+=a.getPeso();
		return peso;
	}

	public boolean hasAttrezzo(String nomeAttrezzo){
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	//remove con array list
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.attrezzi.isEmpty()) {
			s.append("contenuto borsa(").append(this.getPeso()).append("kg/")
			.append(this.getPesoMax()).append("kg): ");
			// Itera sui valori della Map
			s.append(this.getContenutoOrdinatoPerPeso().toString());
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}

	public Collection<Attrezzo> getAttrezzi(){
		return this.attrezzi.values();
	}
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Comparator<Attrezzo> compperpeso = new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				return o1.getPeso()-o2.getPeso();
			}
		};
		List<Attrezzo> lista = new ArrayList<>(this.attrezzi.values());
		lista.sort(compperpeso);
		return lista;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		Comparator<Attrezzo> comppernome = new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				return o1.getNome().compareToIgnoreCase(o2.getNome());
			}
		};
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(comppernome);
		set.addAll(this.attrezzi.values());
		
		return set;
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> ragrpeso = new TreeMap<Integer,Set<Attrezzo>>();
		for(Attrezzo s : this.attrezzi.values()) {
			Set<Attrezzo> agg =ragrpeso.get(s.getPeso());
			if(agg==null) {
				agg = new HashSet<Attrezzo>();
			}
			agg.add(s);
			ragrpeso.put(s.getPeso(), agg);
		}
		return ragrpeso;
	}
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		Comparator<Attrezzo> compperpeso = new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				int cmp=o1.getPeso()-o2.getPeso();
				if(cmp!=0)
				return cmp;
				return o1.getNome().compareTo(o2.getNome());
			}
		};
		SortedSet<Attrezzo> set = new TreeSet<>(compperpeso);
		set.addAll(this.attrezzi.values());
		return set;
		
	}
}