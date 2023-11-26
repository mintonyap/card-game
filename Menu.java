/* CSCI213 Assignment 3
 * --------------------
 * File name: Menu.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Menu Page, prompt start button
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu {

	public void run() {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("War of Greek Gods");
		window.setVisible(true);
		window.setSize(500, 500);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		window.add(panel);
		
		JLabel gameTitle = new JLabel();
		gameTitle = new JLabel("War of Greek Gods");
		gameTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
		gameTitle.setForeground(Color.BLUE);
		gameTitle.setBounds(65, 40, 400, 50);
		panel.add(gameTitle);
		
		ImageIcon icon = new ImageIcon("img/background.jpg");
		JLabel shuffleImg = new JLabel(icon);
		shuffleImg.setBounds(75, 100, 340, 220);
		panel.add(shuffleImg);
		
		JButton button = new JButton("Start Game");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		button.setBounds(185, 360, 120, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {
			        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound/ok.wav").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }	
				GameDisplay GD = new GameDisplay();
				JOptionPane.showMessageDialog(null, "Game is starting..");
				window.setVisible(false);
				try {
					GD.game();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(button);
		//playMusic();
	}

	public void playMusic() {
			
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound/music.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }

	}
	
}
