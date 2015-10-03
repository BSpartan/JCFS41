/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jurgen van den Berg & Bjork Verstraaten
 */
public class HuffmanGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        TextField input = new TextField();
        
        Button btn = new Button();
        btn.setText("Encode");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(input.getText().length() == 0)
                {
                    System.out.println("No text entered");
                    return;
                }
                    
                
                HuffmanTree huffmanTree = new HuffmanTree();
                
                System.out.println("Input: " + input.getText());
                
                // Build the Huffman tree
                huffmanTree.Create(input.getText());

                // Encode
                Boolean[] encoded = huffmanTree.Encode(input.getText());

                System.out.println("Encoded: ");
                for (Boolean bit : encoded)
                {
                    System.out.print((bit ? 1 : 0) + "");
                }
                
                System.out.println();

                // Decode
                String decoded = huffmanTree.Decode(encoded);

                System.out.println("Decoded: " + decoded);
            }
        });
        
        StackPane root = new StackPane();
        StackPane.setAlignment(btn, Pos.BOTTOM_CENTER);
        StackPane.setMargin(btn, new Insets(8,8,8,8));
        
        StackPane.setAlignment(input, Pos.TOP_CENTER);
        StackPane.setMargin(input, new Insets(8,8,8,8));
        root.getChildren().add(input);
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 75);
        
        primaryStage.setTitle("Huffman Codering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
