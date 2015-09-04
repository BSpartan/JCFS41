package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;
    private Object HashMultiset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    // Return the total amount of words and the amount of different words
    @FXML
    private void aantalAction(ActionEvent event) {
        
        // Get list of words
        ArrayList<String> numberOfWords = getInputTextList();

        //Empty output textfield
        taOutput.setText("");
        
        // Add total number of word to the output field
        taOutput.setText("Totaal aantal woorden: " + numberOfWords.size() + "\n");
        
        // Add list to treeset 
        TreeSet<String> numberOfUniqueWords = new TreeSet<>();
        numberOfUniqueWords.addAll(numberOfWords);
        
        // Add number of unique words to the output field
        taOutput.setText(taOutput.getText() + "Aantal verschillende woorden: " + numberOfUniqueWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FXML
    private void frequentieAction(ActionEvent event) {

        // Get list of words
        ArrayList<String> numberOfWords = getInputTextList();

        //Empty output textfield
        taOutput.setText("");
        
        // Generate a list of word frequencies
        Set<String> unique = new HashSet<String>(numberOfWords);
        for (String key : unique) {
            taOutput.setText(taOutput.getText() + key + ": " + Collections.frequency(numberOfWords, key) + "\n");
        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private ArrayList<String> getInputTextList() {
        // Split input textbox 
        String[] numberOfWords = taInput.getText().split("\\.|\\,|[\\r\\n]+|\\s+");

        ArrayList<String> list = new ArrayList<>(Arrays.asList(numberOfWords));
        list.removeAll(Arrays.asList("", null));
        
        return list;
    }

}
