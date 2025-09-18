/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicesummative2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author chand
 */
public class ParentGamePanel extends JPanel implements ActionListener{
    
    // declare all the variables
    GamePanel mainGamePanel;
    static JTextField inp;
    static ArrayList<TextClass> words = new ArrayList<TextClass>();
    static int score, lives;
    static Timer mTimer;
    static JLabel display_score;
    FileReader file;
    BufferedReader input;
    String[] wordFromList;
    
    ParentGamePanel() {
    
        // setting up the panel
        mainGamePanel = new GamePanel();
        this.setPreferredSize(new Dimension(900,500));
        this.setLayout(new BorderLayout());
        this.add(mainGamePanel, BorderLayout.CENTER);
                
        mTimer = new Timer(1000, this); // creating the timer
        
        // setting up the score
        score = 0;
        display_score = new JLabel("Score: " + score + " | Lives: " + lives);
        display_score.setFont(new Font("SansSerif", Font.BOLD, 15));
        display_score.setHorizontalAlignment(JLabel.CENTER);
        this.add(display_score, BorderLayout.NORTH);
        
        // setting up the input
        inp = new JTextField();
        inp.setFont(new Font("SansSerif", Font.BOLD, 20));
        inp.setHorizontalAlignment(JTextField.CENTER);
        this.add(inp, BorderLayout.SOUTH);
        
        // taking in input
        inp.addActionListener((ActionEvent e) -> {
            String word = inp.getText();
            inp.setText("");

            found: {
                for (int i = 0; i < words.size(); i++) {
                    if ((words.get(i).name).equals(word)) {
                        words.remove(i);
                        addScore();
                        break found;
                    }
                }
                if (!word.equals("")) {
                    minusScore();
                }
            }
            
        });
    }
    
    // to add a point
    public void addScore() {
        score++;
        updateLives();
    }
    
    // to subtract a point
    public void minusScore() {
        if (score != 0) {
            score--;
            updateLives();
        }
    }
    
    // to update score and lives
    public static void updateLives() {
        display_score.setText("Score: " + score + " | Lives: " + lives);
    }
    
    // when file starts running
    public static void run(int difficulty) {
        
        lives = difficulty;
        updateLives();
        
        inp.setText("");
        inp.requestFocusInWindow();
        
        mTimer.start();
        
    }
    
    // generating words
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // picks a random word
        int random = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1); 
        
        // reads words file
        try {
            file = new FileReader("src/main/java/com/mycompany/practicesummative2/WordsList.txt");
        } catch (FileNotFoundException ex) {}
        
        input = new BufferedReader(file);
        
        try {
            // linear search        
            for (int i = 0; i < random-1; i++) {
                input.readLine();
            }
            wordFromList = input.readLine().split(",");
        }
        catch (IOException f) {
            f.printStackTrace();
        }
        
        // picks random position
        random = (int) Math.floor(Math.random() * (349 - 10 + 1) + 10);
        
        // places word in random position
        words.add(new TextClass(wordFromList[0], -150, random, Integer.parseInt(wordFromList[1])));
    }
    
    // record score once round is over
    public static void recordScore() throws IOException {
        FileWriter scoreFile = new FileWriter("src/main/java/com/mycompany/practicesummative2/Score.txt", true);
        scoreFile.write(Integer.toString(score) + "\n");
        score = 0;
        scoreFile.close();
    }

}
