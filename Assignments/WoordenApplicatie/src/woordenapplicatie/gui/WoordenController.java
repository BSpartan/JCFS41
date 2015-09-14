package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        taOutput.clear();

        // Add total number of word to the output field
        taOutput.setText("Totaal aantal woorden: " + numberOfWords.size() + "\n");

        // Add list to treeset
        TreeSet<String> numberOfUniqueWords = getUniqueWords(numberOfWords);

        // Add number of unique words to the output field
        taOutput.setText(taOutput.getText() + "Aantal verschillende woorden: " + numberOfUniqueWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        
        //Empty output textfield
        taOutput.clear();
        
        // Create a Treeset with reversed order and add input list
        ArrayList<String> sortedWords = getSortedWords(getInputTextList(taInput.getText()));

        //Display the output
        for(String data : sortedWords){
            taOutput.setText(taOutput.getText() + data);
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        
        //Empty output textfield
        taOutput.clear();
        
        ArrayList<String> frequencyData = getWordFrequency(getInputTextList(taInput.getText()));
        
        //Display the output
        for(String data : frequencyData){
            taOutput.setText(taOutput.getText() + data);
        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {

        // Create new treemap
        TreeMap<String, ArrayList<Integer>> concordance = getConcordanceTreeMap(taInput.getText());

        taOutput.clear();
        
        // Show concordance in the output textfield
        for (Map.Entry<String, ArrayList<Integer>> entrySet : concordance.entrySet()) {
            taOutput.setText(taOutput.getText() + entrySet.getKey() + " = " + entrySet.getValue().toString() + "\n");
        }
    }

    public TreeSet<String> getUniqueWords(ArrayList<String> numberOfWords) {

        TreeSet<String> ts = new TreeSet<>();
        ts.addAll(numberOfWords);

        return ts;
    }

    public ArrayList<String> getSortedWords(ArrayList<String> numberOfWords) {

        ArrayList<String> sortedWords = new ArrayList<>();
        
        TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder());
        ts.addAll(numberOfWords);
        
        ts.stream().forEach((word) -> {
            sortedWords.add(word + "\n");
        });
        
        Collections.sort(sortedWords);
        Collections.reverse(sortedWords);

        return sortedWords;
    }

    public ArrayList<String> getWordFrequency(ArrayList<String> numberOfWords) {

        ArrayList<String> frequencyData = new ArrayList<>();
        HashSet<String> hs = new HashSet<>(numberOfWords);

        // Create a treemap to sortfrequencies
        TreeMap<String, Integer> unsortedFrequencies = new TreeMap<>();

        for (String key : hs) {
            unsortedFrequencies.put(key, Collections.frequency(numberOfWords, key));
        }

        // Create a comparator to sort the values from the map entry
        Comparator<Map.Entry<String, Integer>> byMapValues = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
                return left.getValue().compareTo(right.getValue());
            }
        };

        // A list with sorted map Entrys
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>();

        // add all Entrys
        sortedList.addAll(unsortedFrequencies.entrySet());

        // sort the collection using the comparator we just made
        Collections.sort(sortedList, byMapValues);

        for (Map.Entry<String, Integer> entrySet : sortedList) {
            frequencyData.add(entrySet.getKey() + " = " + entrySet.getValue() + "\n");
        }
        
        return frequencyData;
    }

    public TreeMap<String, ArrayList<Integer>> getConcordanceTreeMap(String text) {

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
