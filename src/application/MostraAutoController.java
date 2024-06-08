package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;


public class MostraAutoController {
    @FXML
    private TextArea txtAreaAuto;

    private ListaAuto miaLista;

    public void setListaAuto(ListaAuto listaAuto) {
        this.miaLista = listaAuto;
        mostraListaAuto();
    }

    private void mostraListaAuto() {
        if (miaLista != null) {
            String elencoAuto = miaLista.leggi();
            txtAreaAuto.setText(elencoAuto);
            System.out.println("List displayed in MostraAutoController: " + elencoAuto);
        }
    }

    @FXML
    public void azioneIndietro(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Auto.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
