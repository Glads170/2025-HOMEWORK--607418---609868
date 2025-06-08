package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configuratore {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static Properties propieta = null;
	
	public static int getCFU() {
		if(propieta == null)
			carica();
		return Integer.parseInt(propieta.getProperty(CFU));
	}
	
	public static int getPesoMax() {
		if(propieta == null)
			carica();
		return Integer.parseInt(propieta.getProperty(PESO_MAX));
	}

	private static void carica() {
		propieta = new Properties();
		try {
			InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES);
			propieta.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}