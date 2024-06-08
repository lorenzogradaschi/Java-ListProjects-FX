package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaAuto {
	private ArrayList<Auto> lista;
	private ArchivioAuto mioArchivio;

	public ListaAuto() {
		this.lista = new ArrayList<>();
		this.mioArchivio = new ArchivioAuto();
	}

	public void aggiungi(Auto nuovo) {
		this.lista.add(nuovo);
		System.out.println("Added to list: " + nuovo);
	}


	public void cancella(Auto au) throws AutoNonTrovata {
		boolean removed = false;
		Iterator<Auto> iterator = lista.iterator();
		while (iterator.hasNext()) {
			Auto auto = iterator.next();
			if (auto.getTipo().equalsIgnoreCase(au.getTipo()) || auto.getMarca().equalsIgnoreCase(au.getMarca())) {
				iterator.remove();
				removed = true;
				System.out.println("Removed from list: " + auto);
				break;
			}
		}
		if (!removed) {
			throw new AutoNonTrovata();
		}
	}

	public String salva() {
		try {
			mioArchivio.scriviTutti(lista);
			System.out.println("List saved successfully");
			return "Lista auto salvata con successo";
		} catch (IOException e) {
			System.err.println("Error saving list: " + e.getMessage());
			return "Errore nel salvare la lista delle auto: " + e.getMessage();
		}
	}

	public String leggi() {
		StringBuilder elenco = new StringBuilder("List of Cars:\n");
		for (Auto auto : lista) {
			elenco.append(auto.toString()).append("\n");
		}
		System.out.println("List read successfully from memory: " + elenco.toString());
		return elenco.toString();
	}

	public void caricaDaFile() {
		try {
			lista = mioArchivio.leggiTutti();
			System.out.println("List loaded from file: " + lista);
		} catch (IOException e) {
			System.err.println("Error loading list from file: " + e.getMessage());
		}
	}

	public ArrayList<Auto> getLista() {
		return this.lista;
	}
}
