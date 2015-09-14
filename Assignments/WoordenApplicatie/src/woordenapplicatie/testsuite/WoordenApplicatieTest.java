/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.testsuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.*;
import woordenapplicatie.gui.WoordenController;

/**
 *
 * @author Bjork & Jurgen
 */

public class WoordenApplicatieTest {

    @Test
    public void testAantal() {
        WoordenController w = new WoordenController();
        
        String inputString = "Lientje leerde Lotje lopen\n" +
                             "langs de lange Lindenlaan\n" +
                             "maar toen Lotje niet wou lopen\n" +
                             "toen liet Lientje Lotje staan. ";
        
        ArrayList<String> inputList = w.getInputTextList(inputString);
        TreeSet<String> uniqueWords = w.getUniqueWords(inputList);
        
        int expectedLength = 19;
        int expectedUnique = 14;
        
        Assert.assertEquals("ArrayList length is incorrect: " + inputList.size() + " (Expected: " + expectedLength + ")", expectedLength, inputList.size());
        
        Assert.assertEquals("Unique word count is incorrect: " + uniqueWords.size() + " (Expected: " + expectedUnique + ")", expectedUnique, uniqueWords.size());
    }
    
    @Test
    public void testSorteer() {
        WoordenController w = new WoordenController();
        
        String inputString = "Lientje leerde Lotje lopen\n" +
                             "langs de lange Lindenlaan\n" +
                             "maar toen Lotje niet wou lopen\n" +
                             "toen liet Lientje Lotje staan. ";
        
        ArrayList<String> inputList = w.getInputTextList(inputString);
        
        String expectedOutput = "wou\n" +
                                "toen\n" +
                                "staan\n" +
                                "niet\n" +
                                "maar\n" +
                                "lopen\n" +
                                "liet\n" +
                                "leerde\n" +
                                "langs\n" +
                                "lange\n" +
                                "de\n" +
                                "Lotje\n" +
                                "Lindenlaan\n" +
                                "Lientje\n";
        
        TreeSet<String> sortedWords = w.getSortedWords(inputList);
        
        ArrayList<String> actualOutputList = new ArrayList<>();
        String actualOutput = "";
        
        sortedWords.stream().forEach((word) -> {
            actualOutputList.add(word + "\n");
        });
        
        for(String line : actualOutputList){
            actualOutput += line;
        }
        
        Assert.assertEquals("Given sorted output does not equal expected output", expectedOutput, actualOutput);
    }
    
    @Test
    public void testFrequentie() {
        WoordenController w = new WoordenController();
        
        String inputString = "Lientje leerde Lotje lopen\n" +
                             "langs de lange Lindenlaan\n" +
                             "maar toen Lotje niet wou lopen\n" +
                             "toen liet Lientje Lotje staan. ";
        
        ArrayList<String> inputList = w.getInputTextList(inputString);
        
        String expectedOutput = "Lientje: 2\n" +
                                "de: 1\n" +
                                "staan: 1\n" +
                                "lange: 1\n" +
                                "leerde: 1\n" +
                                "Lotje: 3\n" +
                                "lopen: 2\n" +
                                "toen: 2\n" +
                                "maar: 1\n" +
                                "Lindenlaan: 1\n" +
                                "niet: 1\n" +
                                "langs: 1\n" +
                                "wou: 1\n" +
                                "liet: 1\n";
        
        String actualOutput = "";
        
        Set<String> frequencySet = w.getWordFrequency(inputList);
        for (String key : frequencySet) {
            actualOutput += key + ": " + Collections.frequency(inputList, key) + "\n";
        }
        
        Assert.assertEquals("Given frequency output does not equal expected output", expectedOutput, actualOutput);
    }
    
    @Test
    public void testConcordantie() {
        WoordenController w = new WoordenController();
        
        String inputString = "Lientje leerde Lotje lopen\n" +
                             "langs de lange Lindenlaan\n" +
                             "maar toen Lotje niet wou lopen\n" +
                             "toen liet Lientje Lotje staan. ";
        
        ArrayList<String> inputList = w.getInputTextList(inputString);
        
        String expectedOutput = "{Lientje=[1, 4], Lindenlaan=[2], Lotje=[1, 3, 4], de=[2], lange=[2], langs=[2], leerde=[1], liet=[4], lopen=[1, 3], maar=[3], niet=[3], staan=[4], toen=[3, 4], wou=[3]}";
        
        TreeMap<String, ArrayList<Integer>> concordance = w.getConcordanceTreeMap(inputString);
        
        Assert.assertEquals("Given concordance output does not equal expected output", expectedOutput, concordance.toString());
    }
}
