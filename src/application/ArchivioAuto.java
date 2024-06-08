package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivioAuto {
	private final static String nomeFile = "lista.csv";

	public void scriviTutti(ArrayList<Auto> lista) throws IOException {
		try (BufferedWriter fout = new BufferedWriter(new FileWriter(nomeFile))) {
			for (Auto a : lista) {
				fout.write(a.toCSV() + "\n");
			}
			System.out.println("File written successfully");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
			throw e;
		}
	}

	public ArrayList<Auto> leggiTutti() throws IOException {
		ArrayList<Auto> tempo = new ArrayList<>();
		try (BufferedReader fin = new BufferedReader(new FileReader(nomeFile))) {
			String tmp;
			while ((tmp = fin.readLine()) != null) {
				String[] col = tmp.split(";");
				if (col.length != 3) {
					System.err.println("Invalid row in CSV file: " + tmp);
					continue;
				}
				try {
					Auto a = new Auto(col[0], col[1], Double.parseDouble(col[2]));
					tempo.add(a);
				} catch (NumberFormatException e) {
					System.err.println("Number format error for row: " + tmp);
				}
			}
			System.out.println("File read successfully");
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			throw e;
		}
		return tempo;
	}
}
