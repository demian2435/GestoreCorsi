/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtPeriodo"
	private TextField txtPeriodo; // Value injected by FXMLLoader

	@FXML // fx:id="txtCorso"
	private TextField txtCorso; // Value injected by FXMLLoader

	@FXML // fx:id="btnCorsiPerPeriodo"
	private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

	@FXML // fx:id="btnNumeroStudenti"
	private Button btnNumeroStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="btnStudenti"
	private Button btnStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="btnDivisioneStudenti"
	private Button btnDivisioneStudenti; // Value injected by FXMLLoader

	@FXML // fx:id="txtRisultato"
	private TextArea txtRisultato; // Value injected by FXMLLoader

	@FXML
	void corsiPerPeriodo(ActionEvent event) {
		txtRisultato.clear();
		String input = txtPeriodo.getText();
		Integer pd;

		try {
			pd = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			txtRisultato.setText("Devi inserire un numero (1 o 2)");
			return;
		}

		if (!pd.equals(1) && !pd.equals(2)) {
			txtRisultato.setText("Devi inserire un numero (1 o 2)");
			return;
		}

		List<Corso> result = this.model.getCorsiByPeriodo(pd);
		for (Corso c : result) {
			txtRisultato.appendText(c.toString() + "\n");
		}
	}

	@FXML
	void numeroStudenti(ActionEvent event) {
		txtRisultato.clear();
		String input = txtPeriodo.getText();
		Integer pd;

		try {
			pd = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			txtRisultato.setText("Devi inserire un numero (1 o 2)");
			return;
		}

		if (!pd.equals(1) && !pd.equals(2)) {
			txtRisultato.setText("Devi inserire un numero (1 o 2)");
			return;
		}

		Map<Corso, Integer> result = this.model.getIscrittiByPeriodo(pd);
		for (Corso c : result.keySet()) {
			txtRisultato.appendText(c.getNome() + " = " + result.get(c) + "\n");
		}

	}

	@FXML
	void stampaDivisione(ActionEvent event) {
		txtRisultato.clear();
		String corso = txtCorso.getText();

		if (!model.esisteCorso(corso)) {
			txtRisultato.setText("Il corso non esiste");
			return;
		}

		Map<String, Integer> statistiche = model.getDivisione(corso);

		for (String cds : statistiche.keySet()) {
			txtRisultato.appendText(cds + " = " + statistiche.get(cds) + "\n");
		}
	}

	@FXML
	void stampaStudenti(ActionEvent event) {
		txtRisultato.clear();
		String corso = txtCorso.getText();

		if (!model.esisteCorso(corso)) {
			txtRisultato.setText("Il corso non esiste");
			return;
		}

		List<Studente> result = model.getStudenteByCorso(corso);

		if (result.size() == 0) {
			txtRisultato.setText("Nessuno studente iscritto al corso");
			return;
		}

		for (Studente s : result) {
			txtRisultato.appendText(s.toString() + "\n");
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}

}
