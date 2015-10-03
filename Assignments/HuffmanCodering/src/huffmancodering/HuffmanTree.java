/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jurgen van den Berg & Bjork Verstraaten
 */
public class HuffmanTree {

    private List<Leaf> leaves = new ArrayList<>();
    public Leaf Root;
    public HashMap<Character, Integer> Frequencies;

    public HuffmanTree() {
        Frequencies = new HashMap<>();
    }

    public void Create(String source) {
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);

            if (!Frequencies.containsKey(c)) {
                Frequencies.put(c, 0);
            }

            Frequencies.put(c, Frequencies.get(c) + 1);
        }

        for (Map.Entry<Character, Integer> symbol : Frequencies.entrySet()) {
            leaves.add(new Leaf(symbol.getKey(), symbol.getValue()));
        }

        while (leaves.size() > 1) {
            List<Leaf> ordered = new ArrayList<>(leaves);

            Collections.sort(ordered, (Leaf p1, Leaf p2) -> p1.Frequency - p2.Frequency);

            if (ordered.size() >= 2) {
                List<Leaf> next = ordered.stream().limit(2).collect(Collectors.toList());

                Leaf parent = new Leaf('*', next.get(0).Frequency + next.get(1).Frequency, next.get(0), next.get(1));

                leaves.remove(next.get(0));
                leaves.remove(next.get(1));
                leaves.add(parent);
            }

            Root = leaves.get(0);
        }
    }

    public Boolean[] Encode(String source) {
        List<Boolean> encoded = new ArrayList<>();

        for (int i = 0; i < source.length(); i++) {
            List<Boolean> endcodedSymbol = Root.Traverse(source.charAt(i), new ArrayList<>());
            encoded.addAll(endcodedSymbol);
        }

        Boolean[] bits = encoded.toArray(new Boolean[encoded.size()]);

        return bits;
    }

    public String Decode(Boolean[] bits) {
        Leaf current = Root;
        String decoded = "";

        for (Boolean bit : bits) {
            if (bit) {
                if (current.Right != null) {
                    current = current.Right;
                }
            } else {
                if (current.Left != null) {
                    current = current.Left;
                }
            }

            if (IsLeaf(current)) {
                decoded += current.Symbol;
                current = this.Root;
            }
        }
        
        return decoded;
    }

    public Boolean IsLeaf(Leaf leaf) {
        return (leaf.Left == null && leaf.Right == null);
    }
}
