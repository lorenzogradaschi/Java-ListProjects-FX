package application;

public class Auto {
	private String marca;
	public String tipo;
	private double prezzo;

	public Auto(String marca, String tipo, double prezzo) {
		this.marca = marca;
		this.tipo = tipo;
		this.prezzo = prezzo;
	}

	public Auto() {

	}

	public String getMarca() {
		return marca;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public String toCSV() {
		return marca + ";" + tipo + ";" + prezzo;
	}

	@Override
	public String toString() {
		return "Auto [marca=" + marca + ", tipo=" + tipo + ", prezzo=" + prezzo + "]";
		// prezzo = prezzo1
	}
}
