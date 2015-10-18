/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jurgen van den Berg & Bjork Verstraaten
 */
public class Leaf {
    
    public char Symbol;
    public int Frequency;
    public Leaf Left;
    public Leaf Right;

    public Leaf(char Symbol, int Frequency) {
        this.Symbol = Symbol;
        this.Frequency = Frequency;
    }
    
    public Leaf(char Symbol, int Frequency, Leaf left, Leaf right) {
        this.Symbol = Symbol;
        this.Frequency = Frequency;
        this.Left = left;
        this.Right = right;
    }
    
    public List<Boolean> Traverse(char symbol, List<Boolean> data)
    {
        if(Right == null & Left == null)
        {
            if(symbol == Symbol)
            {
                return data;
            }
            else return null;
        }
        else
        {
            List<Boolean> left = null;
            List<Boolean> right = null;
            
            if(Left != null)
            {
                List<Boolean> leftPath = new ArrayList<>();
                leftPath.addAll(data);
                leftPath.add(false);
                
                left = Left.Traverse(symbol, leftPath);
            }
            
            if(Right != null)
            {
                List<Boolean> rightPath = new ArrayList<>();
                rightPath.addAll(data);
                rightPath.add(true);
                
                right = Right.Traverse(symbol, rightPath);
            }
            
            if(left != null)
            {
                return left;
            }
            else return right;
        }
    }
    
}
