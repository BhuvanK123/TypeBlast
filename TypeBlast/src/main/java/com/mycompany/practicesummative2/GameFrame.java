/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicesummative2;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author chand
 */
public class GameFrame extends JFrame {
    
    // declare all the variables
    ParentGamePanel mainGame;
    GameOverPanel gameOverScreen;
    SetDifficultyPanel difficultyScreen;
    static JPanel p;
    static CardLayout cl;
    HomePanel homeScreen;

    
    GameFrame() {

        // setting up card layout
        mainGame = new ParentGamePanel();
        homeScreen = new HomePanel();
        gameOverScreen = new GameOverPanel();
        difficultyScreen = new SetDifficultyPanel();
        p = new JPanel();
        cl = new CardLayout();
        
        this.add(p);
        p.setLayout(cl);
        
        p.add(mainGame, "Game Screen");
        p.add(homeScreen, "Home Screen");
        p.add(gameOverScreen, "Game Over Screen");
        p.add(difficultyScreen, "Difficulty Screen");
        cl.show(p, "Home Screen");
        
        // setting up jframe
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TypeBlast");
        
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
}

