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
import java.util.TreeMap;
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
        ArrayList<String> numberOfWords = getInputTextList(taInput.getText());

        //Empty output textfield
        taOutput.setText("");

        // Add total number of word to the output field
        taOutput.setText("Totaal aantal woorden: " + numberOfWords.size() + "\n");

        // Add list to treeset
        TreeSet<String> numberOfUniqueWords = getUniqueWords(numberOfWords);

        // Add number of unique words to the output field
        taOutput.setText(taOutput.getText() + "Aantal verschillende woorden: " + numberOfUniqueWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {

        // Create a Treeset with reversed order and add input list
        TreeSet<String> sortedWords = getSortedWords(getInputTextList(taInput.getText()));

        //Empty output textfield
        taOutput.setText("");

        // Display each word
        sortedWords.stream().forEach((word) -> {
            taOutput.setText(taOutput.getText() + word + "\n");
        });
    }

    @FXML
    private void frequentieAction(ActionEvent event) {

        // Get list of words
        ArrayList<String> numberOfWords = getInputTextList(taInput.getText());

        //Empty output textfield
        taOutput.setText("");

        // Generate a list of word frequencies
        Set<String> frequencySet = getWordFrequency(numberOfWords);
        for (String key : frequencySet) {
            taOutput.setText(taOutput.getText() + key + ": " + Collections.frequency(numberOfWords, key) + "\n");
        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {

        // Create new treemap
        TreeMap<String, ArrayList<Integer>> concordance = getConcordanceTreeMap(taInput.getText());

        // Show concordance in the output textfield
        taOutput.setText(concordance.toString());

    }
    
    public TreeSet<String> getUniqueWords(ArrayList<String> numberOfWords){
        
        TreeSet<String> ts = new TreeSet<>();
        ts.addAll(numberOfWords);
        
        return ts;
    }
    
    public TreeSet<String> getSortedWords(ArrayList<String> numberOfWords){
        
        TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder());
        ts.addAll(numberOfWords);
        
        return ts;
    }
    
    public HashSet<String> getWordFrequency(ArrayList<String> numberOfWords){
        
        HashSet<String> hs = new HashSet<>(numberOfWords);
        
        return hs;
    }
    
    public TreeMap<String, ArrayList<Integer>> getConcordanceTreeMap(String text){
        
        TreeMap<String, ArrayList<Integer>> tm = new TreeMap<>();

        // Split text in lines
        String[] inputText = text.split("[\\r\\n]");
        ArrayList<Integer> inputLine = new ArrayList<>();

        // Go through each line 
        for (int i = 0; i < inputText.length; i++) {

            // Split input textbox 
            String[] numberOfWords = inputText[i].split("\\.|\\,|\\s+");

            // Remove empty string from the list
            ArrayList<String> wordList = new ArrayList<>(Arrays.asList(numberOfWords));
            wordList.removeAll(Arrays.asList("", null));

            // Check each word
            for (String word : wordList) {
                if (tm.containsKey(word)) {
                    // If a word already excists in the map then add the current line to the list
                    inputLine = tm.get(word);
                    inputLine.add(i + 1);
                    tm.put(word, inputLine);
                } else {
                    // If its a new word add it to the map
                    inputLine = new ArrayList<>();
                    inputLine.add(i + 1);
                    tm.put(word, inputLine);
                }
            }
        }
        
        return tm;
    }

    public ArrayList<String> getInputTextList(String input) {

        // Split input textbox 
        String[] numberOfWords = input.split("\\.|\\,|[\\r\\n]+|\\s+");

        // Remove empty string from the list
        ArrayList<String> list = new ArrayList<>(Arrays.asList(numberOfWords));
        list.removeAll(Arrays.asList("", null));

        // Return new list
        return list;
    }
}
