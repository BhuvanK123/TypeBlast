/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicesummative2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chand
 */
public class GamePanel extends JPanel implements ActionListener{
    
    // declare the variables
    Image backgroundImage, meteorImage;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int PANEL_WIDTH = (int) screenSize.getWidth();
    final int PANEL_HEIGHT = 400;
    Timer timer;
    int width;
    
    GamePanel() {
        
        // setting up the jpanel
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.yellow);
        
        // getting the images
        backgroundImage = new ImageIcon("src/main/resources/images/space_background.jpg").getImage();
        meteorImage = new ImageIcon("src/main/resources/images/meteor.png").getImage();
        
        // setting up the timer
        timer = new Timer(10, this);
        timer.start();
 
    }
    
    public void paint(Graphics g) {
        
        super.paint(g); // paints background
        
        Graphics g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage, 0, 0, null); // adds the background image
        g2D.setFont(new Font("Arial", Font.BOLD, 20));  
                
        // adds the meteors
        for (int i = 0; i < ParentGamePanel.words.size(); i++) {
            width = (int)(g.getFontMetrics().stringWidth(ParentGamePanel.words.get(i).name))/2;
            g2D.drawImage(meteorImage, ParentGamePanel.words.get(i).x, ParentGamePanel.words.get(i).y, null);
            g2D.drawString(ParentGamePanel.words.get(i).name,ParentGamePanel.words.get(i).x+57-width,ParentGamePanel.words.get(i).y+50);
        }
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
               
        for (int i = 0; i < ParentGamePanel.words.size(); i++) {
            
            // changes the x position
            ParentGamePanel.words.get(i).x += ParentGamePanel.words.get(i).xVelocity;
            
            // checks if meteor touches the edge of the screen
            if (ParentGamePanel.words.get(i).x > (1050-113)) {
                
                ParentGamePanel.words.remove(i); // removes the meteor
                
                // updates the lives
                ParentGamePanel.lives--;
                ParentGamePanel.updateLives();
                
                // checks if lives is over
                if (ParentGamePanel.lives < 1) {
                    // stops the animation
                    GameFrame.cl.show(GameFrame.p, "Game Over Screen");
                    ParentGamePanel.mTimer.stop();
                    ParentGamePanel.words.clear();
                    try {
                        // saves the score
                        ParentGamePanel.recordScore();
                    } catch (IOException ex) {}
                }

            }
            
        }
        
        repaint();
    }
    
    
}
