package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutoController {
	private ListaAuto miaLista = new ListaAuto();

	@FXML
	private Button btnIndietro;

	@FXML
	private Button btnMostraAuto;

	@FXML
	private TextField txtMarca;

	@FXML
	private TextField txtModello;

	@FXML
	private Button btnAggiungi;

	@FXML
	private Button btnElimina;

	@FXML
	private Button btnArchivia;

	@FXML
	private Button btnMostraPrezzo;

	@FXML
	private Button btnAcquista;

	@FXML
	private Label lblMsg;

	@FXML
	private TextArea txtArea;

	@FXML
	void azioneMostraCarrello(ActionEvent event) {
		try {
			System.out.println("Mostra Carrello clicked");
			String elencoAuto = miaLista.leggi();
			txtArea.setText(elencoAuto);
			lblMsg.setText("Cars displayed successfully");
			System.out.println("Cars displayed: " + elencoAuto);
		} catch (Exception e) {
			lblMsg.setText("Error displaying cart: " + e.getMessage());
			e.printStackTrace();
			System.err.println("Error displaying cart: " + e.getMessage());
		}
	}

	@FXML
	void azioneAggiungi(ActionEvent event) {
		try {
			String marca = txtMarca.getText();
			String tipo = txtModello.getText();
			double prezzo = 1000.0; // Default price, can be adjusted or made dynamic

			// Logging
			System.out.println("Aggiungi clicked");
			System.out.println("Marca: " + marca);
			System.out.println("Modello: " + tipo);

			if (marca.isEmpty() || tipo.isEmpty()) {
				throw new IllegalArgumentException("Marca e tipo non possono essere vuoti");
			}

			Auto nuovaAuto = new Auto(marca, tipo, prezzo);
			miaLista.aggiungi(nuovaAuto);

			// Concatenate and set the text area
			String autoDetails = "Marca: " + nuovaAuto.getMarca() + ", Modello: " + nuovaAuto.getTipo() + ", Prezzo: " + nuovaAuto.getPrezzo();
			txtArea.setText(autoDetails);

			lblMsg.setText("Auto aggiunta con successo");
			System.out.println("Auto added: " + nuovaAuto);
		} catch (IllegalArgumentException e) {
			lblMsg.setText("Errore: " + e.getMessage());
			System.err.println("Error: " + e.getMessage());
		} catch (Exception e) {
			lblMsg.setText("Errore nell'aggiungere l'auto");
			e.printStackTrace();
			System.err.println("Error adding car: " + e.getMessage());
		}

		//txtMarca.setText("");
		//txtModello.setText("");
	}


	@FXML
	void azioneMostraAuto(ActionEvent event) throws IOException {
		System.out.println("Mostra Auto clicked");
		Stage stage;
		Parent root;
		FXMLLoader loader = new FXMLLoader();
		if (event.getSource() == btnMostraAuto) {
			stage = (Stage) btnMostraAuto.getScene().getWindow();
			loader.setLocation(getClass().getResource("MostraAuto.fxml"));
			root = loader.load();

			// Get the controller and pass the listaAuto
			MostraAutoController mostraAutoController = loader.getController();
			mostraAutoController.setListaAuto(miaLista);
			System.out.println("Passing listaAuto to MostraAutoController");
		} else {
			stage = (Stage) btnIndietro.getScene().getWindow();
			loader.setLocation(getClass().getResource("Auto.fxml"));
			root = loader.load();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void azioneElimina(ActionEvent event) {
		String marca = txtMarca.getText();
		String tipo = txtModello.getText();

		if (marca.isEmpty() || tipo.isEmpty()) {
			lblMsg.setText("Marca e Modello non possono essere vuoti");
			System.err.println("Marca e Modello non possono essere vuoti");
			return;
		}

		Auto autoToRemove = new Auto(marca, tipo, 0); // Price is irrelevant for deletion
		try {
			System.out.println("Elimina clicked for Modello: " + tipo + " and Marca: " + marca);
			miaLista.cancella(autoToRemove);
			txtArea.setText(miaLista.leggi());  // Update the display after deletion
			lblMsg.setText("Auto eliminata con successo");
			System.out.println("Auto deleted: " + autoToRemove);
		} catch (AutoNonTrovata e) {
			lblMsg.setText(e.getMessage());
			System.err.println("Error: " + e.getMessage());
		}
		txtMarca.setText("");
		txtModello.setText("");
	}

	@FXML
	void azioneArchivia(ActionEvent event) {
		System.out.println("Archivia clicked");
		String message = miaLista.salva();
		lblMsg.setText(message);
		System.out.println(message);
	}

	@FXML
	void azioneMostraPrezzo(ActionEvent event) {
		String marca = txtMarca.getText();
		String tipo = txtModello.getText();
		System.out.println("Mostra Prezzo clicked for Marca: " + marca + " Tipo: " + tipo);
		for (Auto auto : miaLista.getLista()) {
			if (auto.getMarca().equalsIgnoreCase(marca) && auto.getTipo().equalsIgnoreCase(tipo)) {
				lblMsg.setText("Prezzo dell'auto: " + auto.getPrezzo());
				System.out.println("Prezzo found: " + auto.getPrezzo());
				return;
			}
		}
		lblMsg.setText("Auto non trovata o prezzo non disponibile.");
		System.out.println("Auto not found or price not available for Marca: " + marca + " Tipo: " + tipo);
	}

	@FXML
	void initialize() {
		// Ensure all FXML elements are properly injected
		System.out.println("Controller initialized");
	}
}
