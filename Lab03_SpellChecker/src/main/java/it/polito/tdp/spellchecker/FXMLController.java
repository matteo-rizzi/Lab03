/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	ObservableList<String> list = FXCollections.observableArrayList();

	private Dictionary dictionary;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="choiceLang"
	private ChoiceBox<String> choiceLang; // Value injected by FXMLLoader

	@FXML // fx:id="txtInput"
	private TextArea txtInput; // Value injected by FXMLLoader

	@FXML // fx:id="btnControlla"
	private Button btnControlla; // Value injected by FXMLLoader

	@FXML // fx:id="txtRisposta"
	private TextArea txtRisposta; // Value injected by FXMLLoader

	@FXML // fx:id="lblErrore"
	private Label lblErrore; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancella"
	private Button btnCancella; // Value injected by FXMLLoader

	@FXML // fx:id="lblTempo"
	private Label lblTempo; // Value injected by FXMLLoader

	@FXML
	void doClearText(ActionEvent event) {
		txtInput.clear();
		txtRisposta.clear();
		lblErrore.setText("");
	}

	@FXML
	void doSpellCheck(ActionEvent event) {
		String selezione = choiceLang.getValue();
		dictionary.loadDictionary(selezione);

		List<String> input = new ArrayList<>();
	//	List<String> input = new LinkedList<>();

		String inserita = txtInput.getText().toLowerCase();
		inserita.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
	//	String arr[] = inserita.split(" "); 
	//	input = trasformaArrayLista(arr);
		StringTokenizer st = new StringTokenizer(inserita);
		while(st.hasMoreTokens())
			input.add(st.nextToken());

		double start = System.nanoTime();
		dictionary.spellCheckTextDichotomic(input);
	//	dictionary.spellCheckTextLinear(input);
		double stop = System.nanoTime();
		double tempo = (stop - start) / 1000000000;

		txtRisposta.setText(dictionary.toString());
		
		String sbagliate = Integer.toString(dictionary.numeroParoleSbagliate());
		if(sbagliate.equals("1"))
			lblErrore.setText("The text contains " + Integer.toString(dictionary.numeroParoleSbagliate()) + " error");
		else
			lblErrore.setText("The text contains " + Integer.toString(dictionary.numeroParoleSbagliate()) + " errors");

		lblTempo.setText("Spell check completed in " + tempo + " seconds");
	}

/*	private List<String> trasformaArrayLista(String[] arr) {
		List<String> lista = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			lista.add(arr[i]);
		}
		return lista;
	}
*/
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert choiceLang != null : "fx:id=\"choiceLang\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnControlla != null : "fx:id=\"btnControlla\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lblErrore != null : "fx:id=\"lblErrore\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

		list.addAll("Italian", "English");
		choiceLang.setItems(list);
		choiceLang.setValue("Italian");
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
}
