/* CSCI213 Assignment 3
 * --------------------
 * File name: GameDisplay.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Displays Game GUI
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;

public class GameDisplay{ // extends JFrame
	
	boolean gameEnd = false;
	static boolean playerTurn=true;
	int turn = 1;
	Player p;
	Dealer d;
	JFrame window;
	JPanel panel;
	JLabel shuffle, pName1, pName2, pName3, dName1, dName2, dName3, pEle1, pEle2, pEle3, dEle1, dEle2, dEle3, pa1, pa2, pa3, da1, da2, da3;
	JPanel playerCard1, playerCard2, playerCard3,dealerCard1, dealerCard2, dealerCard3, pcn1, pcn2, pcn3, dcn1, dcn2, dcn3, pce1, pce2, pce3, dce1, dce2, dce3, pcp1, pcp2, pcp3, dcp1, dcp2, dcp3;
	GamePlay take;
	
	String buf = System.getProperty("line.separator");
	String sp = "|";
	
	//private GamePlay gamePlay;
	
	public void game() throws FileNotFoundException { 
		gameEnd = false;
		//playerTurn = true;
		
		// TODO Auto-generated method stub
		window = new JFrame("War of Greek Gods Game");
		window.setVisible(true);
		window.setSize(1200, 800);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		window.add(panel);
		
		//BufferedImage image = ImageIO.read(new File("img/Zeus.jpg"));
		//panel.setIconImage(image);
		
		ImageIcon icon = new ImageIcon("img/deck.png");//deck2.jpg //buffimg,image
		shuffle = new JLabel(icon);  // NOT A PANEL ! ! ;
		shuffle.setBounds(80, 275, 200, 280);//x , y, width, height
		panel.add(shuffle);
		
		JLabel dealerTitle = new JLabel();
		dealerTitle = new JLabel("Dealer's Game Cards");
		dealerTitle.setFont(new Font("Verdana", Font.PLAIN, 20));//Impact
		dealerTitle.setBounds(615, 40, 400, 50);
		panel.add(dealerTitle);
		
		//JLabel playerTitle = new JLabel("<HTML><U>YOUR TEXT HERE</U></HTML>");
		//playerTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel playerTitle = new JLabel("Player's Game Cards");
		//playerTitle = new JLabel("Player's Game Cards");
		playerTitle.setFont(new Font("Verdana", Font.PLAIN, 20));
		playerTitle.setBounds(615, 390, 400, 50);
		panel.add(playerTitle);
		
		Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel, empty;
		blackline = BorderFactory.createLineBorder(Color.BLACK);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEmptyBorder();
		
		Border card;
		card = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);	
		Border textBox = raisedetched;//BorderFactory.createLineBorder(Color.GRAY, 35)
		
		 p = new Player();
		 d = new Dealer();
		
		d.shuffleCards();
		for (int i=0; i < 3; i++) {
			p.addCard(d.dealCard());
			d.addCard(d.dealCard());
			}
		
		playerCard1 = new JPanel();
		playerCard1.setBorder(card);
		playerCard1.setBounds(350, 450, 200, 280);//x , y, width, height
		playerCard1.setBackground(new Color(255, 215, 0));
		
		pcn1 = new JPanel();//name & desc dcnd1
		pcn1.setBorder(textBox);//paneEdge
		playerCard1.add(pcn1);
		
		pName1 = new JLabel(p.cardsOnHand.get(0).getName() + " - " + p.cardsOnHand.get(0).getDescription(), SwingConstants.CENTER);
		pName1.setFont(new Font("Serif", Font.BOLD, 12));
		pName1.setPreferredSize(new Dimension(165, 20));
		pcn1.setBackground(new Color(255, 215, 0));
		pcn1.add(pName1);
		
		pcp1 = new JPanel();//picture
		pcp1.setBorder(textBox);//paneEdge
		//dce1.setLayout(new GridLayout(2, 2, 3, 3));
		playerCard1.add(pcp1);
			
		ImageIcon iconp1;
		
		String pin1 = "img/"+p.cardsOnHand.get(0).getName()+".jpg";
		iconp1 = new ImageIcon(pin1);//deck2.jpg
		pEle1 = new JLabel(iconp1);
		//shuffle.setBounds(80, 275, 200, 280);//x , y, width, height
		//panel.add(shuffle);
		pEle1.setPreferredSize(new Dimension(165, 150));
		pEle1.setVerticalAlignment(SwingConstants.CENTER);
		pcp1.add(pEle1);
		
		pce1 = new JPanel();//element
		pce1.setBorder(textBox);//paneEdge
		pce1.setBackground(new Color(255, 215, 0));
		playerCard1.add(pce1);
		
		int pTotal1 = p.cardsOnHand.get(0).getEarth() + p.cardsOnHand.get(0).getWater() + p.cardsOnHand.get(0).getAir() + p.cardsOnHand.get(0).getFire();
		String pe1 = "E: " + p.cardsOnHand.get(0).getEarth() + " W: " + p.cardsOnHand.get(0).getWater() +
				" A: " + p.cardsOnHand.get(0).getAir() + " F: " + p.cardsOnHand.get(0).getFire() + " T: " + pTotal1;
		
		pa1 = new JLabel(pe1, SwingConstants.CENTER);
		pa1.setFont(new Font("Serif", Font.PLAIN, 12));
		pa1.setPreferredSize(new Dimension(165, 40));
		pa1.setVerticalAlignment(SwingConstants.CENTER);
		pce1.add(pa1);
		
		panel.add(playerCard1);
		
		playerCard2 = new JPanel();
		playerCard2.setBorder(card);
		playerCard2.setBounds(620, 450, 200, 280);//x , y, width, height
		
		playerCard2.setBackground(new Color(255, 215, 0));
		
		pcn2 = new JPanel();//name & desc dcnd1
		pcn2.setBorder(textBox);//paneEdge
		playerCard2.add(pcn2);
		
		pName2 = new JLabel(p.cardsOnHand.get(1).getName() + " - " + p.cardsOnHand.get(1).getDescription(), SwingConstants.CENTER);
		pName2.setFont(new Font("Serif", Font.BOLD, 12));
		pName2.setPreferredSize(new Dimension(165, 20));
		pcn2.setBackground(new Color(255, 215, 0));
		pcn2.add(pName2);
		
		pcp2 = new JPanel();//picture
		pcp2.setBorder(textBox);//paneEdge
		//dce1.setLayout(new GridLayout(2, 2, 3, 3));
		playerCard2.add(pcp2);
		
		String pin2 = "img/"+p.cardsOnHand.get(1).getName()+".jpg";
		ImageIcon iconp2 = new ImageIcon(pin2);
		pEle2 = new JLabel(iconp2);
		pEle2.setPreferredSize(new Dimension(165, 150));
		pEle2.setVerticalAlignment(SwingConstants.CENTER);
		pcp2.add(pEle2);
		
		pce2 = new JPanel();//element
		pce2.setBorder(textBox);//paneEdge
		pce2.setBackground(new Color(255, 215, 0));
		playerCard2.add(pce2);
		
		int pTotal2 = p.cardsOnHand.get(1).getEarth() + p.cardsOnHand.get(1).getWater() + p.cardsOnHand.get(1).getAir() + p.cardsOnHand.get(1).getFire();
		String pe2 = "E: " + p.cardsOnHand.get(1).getEarth() + " W: " + p.cardsOnHand.get(1).getWater() +
				" A: " + p.cardsOnHand.get(1).getAir() + " F: " + p.cardsOnHand.get(1).getFire() + " T: " + pTotal2;
		
		pa2 = new JLabel(pe2, SwingConstants.CENTER);
		pa2.setFont(new Font("Serif", Font.PLAIN, 12));
		pa2.setPreferredSize(new Dimension(165, 40));
		pa2.setVerticalAlignment(SwingConstants.CENTER);
		pce2.add(pa2);
		
		panel.add(playerCard2);
		
		playerCard3 = new JPanel();
		playerCard3.setBorder(card);
		playerCard3.setBounds(890, 450, 200, 280);//x , y, width, height
		
		playerCard3.setBackground(new Color(255, 215, 0));
		
		pcn3 = new JPanel();//name & desc dcnd1
		pcn3.setBorder(textBox);//paneEdge
		pcn3.setBackground(new Color(255, 215, 0));
		playerCard3.add(pcn3);
		
		pName3 = new JLabel(p.cardsOnHand.get(2).getName() + " - " + p.cardsOnHand.get(2).getDescription(), SwingConstants.CENTER);
		pName3.setFont(new Font("Serif", Font.BOLD, 12));
		pName3.setPreferredSize(new Dimension(165, 20));
		pcn3.add(pName3);
		
		pcp3 = new JPanel();//picture
		pcp3.setBorder(textBox);//paneEdge
		playerCard3.add(pcp3);
		
		String pin3 = "img/"+p.cardsOnHand.get(2).getName()+".jpg";
		ImageIcon iconp3 = new ImageIcon(pin3);
		pEle3 = new JLabel(iconp3);
		pEle3.setPreferredSize(new Dimension(165, 150));
		pEle3.setVerticalAlignment(SwingConstants.CENTER);
		pcp3.add(pEle3);
		
		pce3 = new JPanel();//element
		pce3.setBorder(textBox);//paneEdge
		pce3.setBackground(new Color(255, 215, 0));
		playerCard3.add(pce3);
		
		int pTotal3 = p.cardsOnHand.get(2).getEarth() + p.cardsOnHand.get(2).getWater() + p.cardsOnHand.get(2).getAir() + p.cardsOnHand.get(2).getFire();
		String pe3 = "E: " + p.cardsOnHand.get(2).getEarth() + " W: " + p.cardsOnHand.get(2).getWater() +
				" A: " + p.cardsOnHand.get(2).getAir() + " F: " + p.cardsOnHand.get(2).getFire() + " T: " + pTotal3;
		
		pa3 = new JLabel(pe3, SwingConstants.CENTER);
		pa3.setFont(new Font("Serif", Font.PLAIN, 12));
		pa3.setPreferredSize(new Dimension(165, 40));
		pa3.setVerticalAlignment(SwingConstants.CENTER);
		pce3.add(pa3);
		
		panel.add(playerCard3);	
		
		dealerCard1 = new JPanel();
		dealerCard1.setBorder(card);//270
		dealerCard1.setBounds(350, 100, 200, 280);//x , y, width, height
		dealerCard1.setBackground(new Color(255, 215, 0));
		
		dcn1 = new JPanel();//name & desc dcnd1
		dcn1.setBorder(textBox);//paneEdge
		dcn1.setBackground(new Color(255, 215, 0));
		dealerCard1.add(dcn1);
		
		dName1 = new JLabel(d.cardsOnHand.get(0).getName() + " - " + d.cardsOnHand.get(0).getDescription(), SwingConstants.CENTER);
		dName1.setFont(new Font("Serif", Font.BOLD, 12));
		dName1.setPreferredSize(new Dimension(165, 20));
		dcn1.add(dName1);
		
		dcp1 = new JPanel();//picture
		dcp1.setBorder(textBox);//paneEdge
		//dce1.setLayout(new GridLayout(2, 2, 3, 3));
		dealerCard1.add(dcp1);
		
		String din1 = "img/"+d.cardsOnHand.get(0).getName()+".jpg";
		ImageIcon icond1 = new ImageIcon(din1);
		dEle1 = new JLabel(icond1);
		dEle1.setPreferredSize(new Dimension(165, 150));
		dEle1.setVerticalAlignment(SwingConstants.CENTER);
		dcp1.add(dEle1);
		
		dce1 = new JPanel();//element
		dce1.setBorder(textBox);//paneEdge
		dce1.setBackground(new Color(255, 215, 0));
		dealerCard1.add(dce1);
		
		int dTotal1 = d.cardsOnHand.get(0).getEarth() + d.cardsOnHand.get(0).getWater() + d.cardsOnHand.get(0).getAir() + d.cardsOnHand.get(0).getFire();
		String de1 = "E: " + d.cardsOnHand.get(0).getEarth() + " W: " + d.cardsOnHand.get(0).getWater() +
				" A: " + d.cardsOnHand.get(0).getAir() + " F: " + d.cardsOnHand.get(0).getFire() + " T: " + dTotal1;
		
		da1 = new JLabel(de1, SwingConstants.CENTER);
		da1.setFont(new Font("Serif", Font.PLAIN, 12));
		da1.setPreferredSize(new Dimension(165, 40));
		da1.setVerticalAlignment(SwingConstants.CENTER);
		dce1.add(da1);
		
		panel.add(dealerCard1);//end
		
		dealerCard2 = new JPanel();
		dealerCard2.setBorder(card);
		dealerCard2.setBounds(620, 100, 200, 280);//x , y, width, height
		
		dealerCard2.setBackground(new Color(255, 215, 0));
		
		dcn2 = new JPanel();//name & desc dcnd1
		dcn2.setBorder(textBox);//paneEdge
		dcn2.setBackground(new Color(255, 215, 0));
		dealerCard2.add(dcn2);
		
		dName2 = new JLabel(d.cardsOnHand.get(1).getName() + " - " + d.cardsOnHand.get(1).getDescription(), SwingConstants.CENTER);
		dName2.setFont(new Font("Serif", Font.BOLD, 12));
		dName2.setPreferredSize(new Dimension(165, 20));
		dcn2.add(dName2);
		
		dcp2 = new JPanel();//picture
		dcp2.setBorder(textBox);//paneEdge
		dealerCard2.add(dcp2);
		
		String din2 = "img/"+d.cardsOnHand.get(1).getName()+".jpg";
		ImageIcon icond2 = new ImageIcon(din2);
		dEle2 = new JLabel(icond2);
		dEle2.setPreferredSize(new Dimension(165, 150));
		dEle2.setVerticalAlignment(SwingConstants.CENTER);
		dcp2.add(dEle2);
		
		dce2 = new JPanel();//element
		dce2.setBorder(textBox);//paneEdge
		dce2.setBackground(new Color(255, 215, 0));
		dealerCard2.add(dce2);
		
		int dTotal2 = d.cardsOnHand.get(1).getEarth() + d.cardsOnHand.get(1).getWater() + d.cardsOnHand.get(1).getAir() + d.cardsOnHand.get(1).getFire();
		String de2 = "E: " + d.cardsOnHand.get(1).getEarth() + " W: " + d.cardsOnHand.get(1).getWater() +
				" A: " + d.cardsOnHand.get(1).getAir() + " F: " + d.cardsOnHand.get(1).getFire() + " T: " + dTotal2;
		
		da2 = new JLabel(de2, SwingConstants.CENTER);
		da2.setFont(new Font("Serif", Font.PLAIN, 12));
		da2.setPreferredSize(new Dimension(165, 40));
		da2.setVerticalAlignment(SwingConstants.CENTER);
		dce2.add(da2);
		
		panel.add(dealerCard2);
		
		dealerCard3 = new JPanel();
		dealerCard3.setBorder(card);
		dealerCard3.setBounds(890, 100, 200, 280);//x , y, width, height
		
		dealerCard3.setBackground(new Color(255, 215, 0));
		
		dcn3 = new JPanel();//name & desc dcnd1
		dcn3.setBorder(textBox);//paneEdge
		dcn3.setBackground(new Color(255, 215, 0));
		dealerCard3.add(dcn3);
		
		dName3 = new JLabel(d.cardsOnHand.get(2).getName() + " - " + d.cardsOnHand.get(2).getDescription(), SwingConstants.CENTER);
		dName3.setFont(new Font("Serif", Font.BOLD, 12));
		dName3.setPreferredSize(new Dimension(165, 20));
		dcn3.add(dName3);
		
		dcp3 = new JPanel();//picture
		dcp3.setBorder(textBox);//paneEdge
		dealerCard3.add(dcp3);
		
		String din3 = "img/"+d.cardsOnHand.get(2).getName()+".jpg";
		ImageIcon icond3 = new ImageIcon(din3);
		dEle3 = new JLabel(icond3);
		dEle3.setPreferredSize(new Dimension(165, 150));
		dEle3.setVerticalAlignment(SwingConstants.CENTER);
		dcp3.add(dEle3);
		
		dce3 = new JPanel();//element
		dce3.setBorder(textBox);//paneEdge
		dce3.setBackground(new Color(255, 215, 0));
		dealerCard3.add(dce3);
		
		int dTotal3 = d.cardsOnHand.get(2).getEarth() + d.cardsOnHand.get(2).getWater() + d.cardsOnHand.get(2).getAir() + d.cardsOnHand.get(2).getFire();
		String de3 = "E: " + d.cardsOnHand.get(2).getEarth() + " W: " + d.cardsOnHand.get(2).getWater() +
				" A: " + d.cardsOnHand.get(2).getAir() + " F: " + d.cardsOnHand.get(2).getFire() + " T: " + dTotal3;
		
		da3 = new JLabel(de3, SwingConstants.CENTER);
		da3.setFont(new Font("Serif", Font.PLAIN, 12));
		da3.setPreferredSize(new Dimension(165, 40));
		da3.setVerticalAlignment(SwingConstants.CENTER);
		dce3.add(da3);
		
		panel.add(dealerCard3);
		
		//earth=green water=blue air=yellow/white fire=red orange=outline
		
		JLabel copyRight = new JLabel("Copyright © 2018 Minton Yap All Rights Reserved. ");
		copyRight.setFont(new Font("SansSerif", Font.PLAIN, 10));
		copyRight.setBounds(900, 740, 400, 30);
		panel.add(copyRight);
		
		
		GamePlay gp = new GamePlay(this);
		gp.pp(p.getCardsOnHand(), d.getCardsOnHand());
			
		playMusic();

		playerCard1.addMouseListener(gp);
		playerCard2.addMouseListener(gp);
		playerCard3.addMouseListener(gp);
		dealerCard1.addMouseListener(gp);
		dealerCard2.addMouseListener(gp);
		dealerCard3.addMouseListener(gp);
		
		playerCard1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playerCard2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playerCard3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dealerCard1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dealerCard2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dealerCard3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
	
	String pillar;
	String playercardname;
	int playerdice;
	int dealerdice;
	int dealercard;
	int trans;
	int diff;
	String dealercardname;
	//private String cardChoice;
	String cardChoice;
	
	public void promptCallBack(String pillar, String playercardname, int dealercard, int dice1 , int dice2, int difference, int transfer) throws FileNotFoundException
	{
		gameEnd=false;
		//boolean playerTurn=true;
		
		this.pillar = pillar;
		this.playercardname = playercardname;
		this.dealercard = dealercard;
	    dealercardname = d.cardsOnHand.get(dealercard).getName();
		playerdice = dice1;
		dealerdice = dice2;
		trans = transfer;
		diff = difference;	
		
		Card playertemp = p.removeCard(playercardname);
		Card dealertemp = d.removeCard(dealercardname);
		String message = "";
		
		if(playerTurn==true) {
		
		if(pillar.equals("Earth"))
		{
			//panel.setBackground(Color.GREEN);
			//panel.repaint();
			if(dice2>dice1) {
			int reduce = trans-playertemp.getEarth();
			if (playertemp.getEarth() <= trans && reduce != 0) {
				message += playertemp.getEarth() + " Earth element power points transferred from " + playertemp.getName()
				+ " to " + dealertemp.getName()+buf;
				
				dealertemp.setEarth(dealertemp.getEarth() + playertemp.getEarth());
				playertemp.setEarth(playertemp.getEarth() - playertemp.getEarth());
				
				message+="Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth()+buf;
				message+="Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth();
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getEarth() == 0 || dealertemp.getEarth() == 0) {
					gameEnd=true;
				}
			}
			else if (playertemp.getEarth() <= transfer && reduce == 0) {
				
				message+=(playertemp.getEarth() + " Earth element power points transferred from "
						+ playertemp.getName() + " to " + dealertemp.getName())+buf;

				dealertemp.setEarth(dealertemp.getEarth() + playertemp.getEarth());
				playertemp.setEarth(0);

				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;
			}
			else if (playertemp.getEarth() > transfer) {

				message+=(trans + " Earth element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				playertemp.setEarth(playertemp.getEarth() - transfer);
				dealertemp.setEarth(dealertemp.getEarth() + transfer);
				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}
		}	
			if(dice1>dice2) {
				
				int reduce = trans-dealertemp.getEarth();
				
				if (dealertemp.getEarth() <= trans && reduce != 0) {

					message+=(dealertemp.getEarth() + " Earth element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setEarth(playertemp.getEarth() + dealertemp.getEarth());
					dealertemp.setEarth(dealertemp.getEarth() - dealertemp.getEarth());
					message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					if(playertemp.getEarth() == 0 || dealertemp.getEarth() == 0) {
						gameEnd=true;
					}
				}
				
					else if (dealertemp.getEarth() <= trans && reduce == 0) {

					message+=(dealertemp.getEarth() + " Earth element power points transferred from "
							+ dealertemp.getName() + " to " + playertemp.getName())+buf;

					playertemp.setEarth(playertemp.getEarth() + dealertemp.getEarth());
					dealertemp.setEarth(0);

					message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}

				else if (dealertemp.getEarth() > trans) {

					message+=(transfer + " Earth element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setEarth(playertemp.getEarth() + trans);
					dealertemp.setEarth(dealertemp.getEarth() - trans);
					message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}
			JOptionPane.showMessageDialog(null, message);
			repaintEle();
			if(gameEnd==true) {
				try {
					saveData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dealertemp.getEarth()==0) {
				promptGGP();
				}
				if(playertemp.getEarth()==0) {
					promptGGD();
					}
			}
			else {playerTurn=false;}
		}//end of Earth 2018
			
		if(pillar.equals("Water"))
		{
			if(dice2>dice1) {
			int reduce = trans-playertemp.getWater();
			if (playertemp.getWater() <= trans && reduce != 0) {
				message += playertemp.getWater() + " Water element power points transferred from " + playertemp.getName()
				+ " to " + dealertemp.getName()+buf;
				
				dealertemp.setWater(dealertemp.getWater() + playertemp.getWater());
				playertemp.setWater(playertemp.getWater() - playertemp.getWater());
				
				message+="Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater()+buf;
				message+="Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater();
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getWater() == 0 || dealertemp.getWater() == 0) {
					gameEnd=true;
				}
			}
			else if (playertemp.getWater() <= transfer && reduce == 0) {
				
				message+=(playertemp.getWater() + " Water element power points transferred from "
						+ playertemp.getName() + " to " + dealertemp.getName())+buf;

				dealertemp.setWater(dealertemp.getWater() + playertemp.getWater());
				playertemp.setWater(0);

				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;
			}
			else if (playertemp.getWater() > transfer) {

				message+=(trans + " Water element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				playertemp.setWater(playertemp.getWater() - transfer);
				dealertemp.setWater(dealertemp.getWater() + transfer);
				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}
		}	
			if(dice1>dice2) {
				
				int reduce = trans-dealertemp.getWater();
				
				if (dealertemp.getWater() <= trans && reduce != 0) {

					message+=(dealertemp.getWater() + " Water element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setWater(playertemp.getWater() + dealertemp.getWater());
					dealertemp.setWater(dealertemp.getWater() - dealertemp.getWater());
					message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					if(playertemp.getWater() == 0 || dealertemp.getWater() == 0) {
						gameEnd=true;
					}
				}
				
					else if (dealertemp.getWater() <= trans && reduce == 0) {

					message+=(dealertemp.getWater() + " Water element power points transferred from "
							+ dealertemp.getName() + " to " + playertemp.getName())+buf;

					playertemp.setWater(playertemp.getWater() + dealertemp.getWater());
					dealertemp.setWater(0);

					message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}

				else if (dealertemp.getWater() > trans) {

					message+=(transfer + " Water element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setWater(playertemp.getWater() + trans);
					dealertemp.setWater(dealertemp.getWater() - trans);
					message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}
			JOptionPane.showMessageDialog(null, message);
			repaintEle();
			if(gameEnd==true) {
				try {
					saveData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dealertemp.getWater()==0) {
					promptGGP();
					}
					if(playertemp.getWater()==0) {
						promptGGD();
						}
			}
			else {playerTurn=false;}
		}//end of W
		
		if(pillar.equals("Air"))
		{
			if(dice2>dice1) {
			int reduce = trans-playertemp.getAir();
			if (playertemp.getAir() <= trans && reduce != 0) {
				message += playertemp.getAir() + " Air element power points transferred from " + playertemp.getName()
				+ " to " + dealertemp.getName()+buf;
				
				dealertemp.setAir(dealertemp.getAir() + playertemp.getAir());
				playertemp.setAir(playertemp.getAir() - playertemp.getAir());
				
				message+="Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir()+buf;
				message+="Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir();
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getAir() == 0 || dealertemp.getAir() == 0) {
					gameEnd=true;
				}
			}
			else if (playertemp.getAir() <= transfer && reduce == 0) {
				
				message+=(playertemp.getAir() + " Air element power points transferred from "
						+ playertemp.getName() + " to " + dealertemp.getName())+buf;

				dealertemp.setAir(dealertemp.getAir() + playertemp.getAir());
				playertemp.setAir(0);

				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;
			}
			else if (playertemp.getAir() > transfer) {

				message+=(trans + " Air element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				playertemp.setAir(playertemp.getAir() - transfer);
				dealertemp.setAir(dealertemp.getAir() + transfer);
				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}
		}	
			if(dice1>dice2) {
				
				int reduce = trans-dealertemp.getAir();
				
				if (dealertemp.getAir() <= trans && reduce != 0) {

					message+=(dealertemp.getAir() + " Air element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setAir(playertemp.getAir() + dealertemp.getAir());
					dealertemp.setAir(dealertemp.getAir() - dealertemp.getAir());
					message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					if(playertemp.getAir() == 0 || dealertemp.getAir() == 0) {
						gameEnd=true;
					}
				}
				
					else if (dealertemp.getAir() <= trans && reduce == 0) {

					message+=(dealertemp.getAir() + " Air element power points transferred from "
							+ dealertemp.getName() + " to " + playertemp.getName())+buf;

					playertemp.setAir(playertemp.getAir() + dealertemp.getAir());
					dealertemp.setAir(0);

					message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}

				else if (dealertemp.getAir() > trans) {

					message+=(transfer + " Air element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setAir(playertemp.getAir() + trans);
					dealertemp.setAir(dealertemp.getAir() - trans);
					message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}
			JOptionPane.showMessageDialog(null, message);
			repaintEle();
			if(gameEnd==true) {
				try {
					saveData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dealertemp.getAir()==0) {
					promptGGP();
					}
					if(playertemp.getAir()==0) {
						promptGGD();
						}
			}
			else {playerTurn=false;}
		}//end of A
		
		if(pillar.equals("Fire"))
		{
			if(dice2>dice1) {
			int reduce = trans-playertemp.getFire();
			if (playertemp.getFire() <= trans && reduce != 0) {
				message += playertemp.getFire() + " Fire element power points transferred from " + playertemp.getName()
				+ " to " + dealertemp.getName()+buf;
				
				dealertemp.setFire(dealertemp.getFire() + playertemp.getFire());
				playertemp.setFire(playertemp.getFire() - playertemp.getFire());
				
				message+="Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire()+buf;
				message+="Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire();
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getFire() == 0 || dealertemp.getFire() == 0) {
					gameEnd=true;
				}
			}
			else if (playertemp.getFire() <= transfer && reduce == 0) {
				
				message+=(playertemp.getFire() + " Fire element power points transferred from "
						+ playertemp.getName() + " to " + dealertemp.getName())+buf;

				dealertemp.setFire(dealertemp.getFire() + playertemp.getFire());
				playertemp.setFire(0);

				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;
			}
			else if (playertemp.getFire() > transfer) {

				message+=(trans + " Fire element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				playertemp.setFire(playertemp.getFire() - transfer);
				dealertemp.setFire(dealertemp.getFire() + transfer);
				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire())+buf;
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}
		}	
			if(dice1>dice2) {
				
				int reduce = trans-dealertemp.getFire();
				
				if (dealertemp.getFire() <= trans && reduce != 0) {

					message+=(dealertemp.getFire() + " Fire element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setFire(playertemp.getFire() + dealertemp.getFire());
					dealertemp.setFire(dealertemp.getFire() - dealertemp.getFire());
					message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					if(playertemp.getFire() == 0 || dealertemp.getFire() == 0) {
						gameEnd=true;
					}
				}
				
					else if (dealertemp.getFire() <= trans && reduce == 0) {

					message+=(dealertemp.getFire() + " Fire element power points transferred from "
							+ dealertemp.getName() + " to " + playertemp.getName())+buf;

					playertemp.setFire(playertemp.getFire() + dealertemp.getFire());
					dealertemp.setFire(0);

					message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}

				else if (dealertemp.getFire() > trans) {

					message+=(transfer + " Fire element power points transferred from " + dealertemp.getName()
							+ " to " + playertemp.getName())+buf;

					playertemp.setFire(playertemp.getFire() + trans);
					dealertemp.setFire(dealertemp.getFire() - trans);
					message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire())+buf;
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}
			JOptionPane.showMessageDialog(null, message);
			//promptGG();
			repaintEle();
			
			/*if(gameEnd==true && playertemp.getFire() == 0) {
				JOptionPane.showMessageDialog(null, "Dealer wins!");
				promptGG();
			}*/
			if(gameEnd==true) {
				try {
					saveData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(dealertemp.getFire()==0) {
					promptGGP();
					}
					if(playertemp.getFire()==0) {
						promptGGD();
						}
			}
			else {playerTurn=false;}
		}//end of F

	}
		if (playerTurn==false) {
		JOptionPane.showMessageDialog(null,"Dealer's Turn");
		GamePlay gp = new GamePlay(this);
		gp.dp(p.getCardsOnHand(), d.getCardsOnHand());
		}
		
	}
	
	public void promptCallBack2(String pillar, String cardChoice, String playercardname, int dealercard, int dice1 , int dice2, int difference, int transfer) throws FileNotFoundException
	{ 
		//panel.setBackground(Color.PINK);
		this.pillar = pillar;
		this.cardChoice = cardChoice;
		this.playercardname = playercardname;
		this.dealercard = dealercard;
	    dealercardname = d.cardsOnHand.get(dealercard).getName();
	    playercardname = this.cardChoice; //this.
		playerdice = dice1;
		dealerdice = dice2;
		trans = transfer;
		diff = difference;	
		Card playertemp = p.removeCard(playercardname);
		Card dealertemp = d.removeCard(dealercardname);
		String message = "";

		if(playerTurn==false) { //1450
		
		if(pillar.equals("Earth"))
		{
		//int reduce;
		//if(PlayerwinDice==false) {//dealer wins
		if(dice2>dice1) {
			int reduce = trans-playertemp.getEarth();
			
			if (playertemp.getEarth() <= trans && reduce != 0) {

				message+=(playertemp.getEarth() + " Earth element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				dealertemp.setEarth(dealertemp.getEarth() + playertemp.getEarth());
				playertemp.setEarth(playertemp.getEarth() - playertemp.getEarth());
				
				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getEarth() == 0 || dealertemp.getEarth() == 0) {
					gameEnd=true;
				}
			}
			
			else if(playertemp.getEarth()<=trans && reduce == 0) {
				
				message+=(playertemp.getEarth() + " Earth element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;
				
				dealertemp.setEarth(dealertemp.getEarth()+playertemp.getEarth());
				playertemp.setEarth(0);
				
				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;	
			}
			
			else if(playertemp.getEarth()>trans) {
				
				message+=(trans + " Earth element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;

				playertemp.setEarth(playertemp.getEarth()-trans);
				dealertemp.setEarth(dealertemp.getEarth()+trans);
				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}	
		}

		//if(PlayerwinDice==true) {//player wins
		if(dice1>dice2) {
			int reduce = trans-dealertemp.getEarth();
			
			if (dealertemp.getEarth() <= trans && reduce != 0) {

				message+=(dealertemp.getEarth() + " Earth element power points transferred from " + dealertemp.getName()
						+ " to " + playertemp.getName())+buf;

				playertemp.setEarth(playertemp.getEarth() + dealertemp.getEarth());
				dealertemp.setEarth(dealertemp.getEarth() - dealertemp.getEarth());
				message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getEarth() == 0 || dealertemp.getEarth() == 0) {
					gameEnd=true;
				}
			}
			
		else if(dealertemp.getEarth()<=trans && reduce == 0) {
					
			message+=(dealertemp.getEarth() + " Earth element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
					
					playertemp.setEarth(playertemp.getEarth()+dealertemp.getEarth());
					dealertemp.setEarth(0);
					
					message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}
				
				else if(dealertemp.getEarth()>trans) {
					
					message+=(trans + " Earth element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
			
					playertemp.setEarth(playertemp.getEarth()+trans);
					dealertemp.setEarth(dealertemp.getEarth()-trans);
					message+=("Player: " + playertemp.getName() + " Earth element power - " + playertemp.getEarth())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Earth element power - " + dealertemp.getEarth());
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}	
		JOptionPane.showMessageDialog(null, message);
		repaintEle();
		if(gameEnd==true) {
			try {
				saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dealertemp.getEarth()==0) {
				promptGGP();
				}
				if(playertemp.getEarth()==0) {
					promptGGD();
					}
		}
		else {playerTurn=true;}	
		}//end of Earth 2018

		if(pillar.equals("Water"))
		{	
		//int reduce;
		//if(PlayerwinDice==false) {//dealer wins
		if(dice2>dice1) {
			int reduce = trans-playertemp.getWater();
			
			if (playertemp.getWater() <= trans && reduce != 0) {

				message+=(playertemp.getWater() + " Water element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				dealertemp.setWater(dealertemp.getWater() + playertemp.getWater());
				playertemp.setWater(playertemp.getWater() - playertemp.getWater());
				
				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getWater() == 0 || dealertemp.getWater() == 0) {
					gameEnd=true;
				}
			}
			
			else if(playertemp.getWater()<=trans && reduce == 0) {
				
				message+=(playertemp.getWater() + " Water element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;
				
				dealertemp.setWater(dealertemp.getWater()+playertemp.getWater());
				playertemp.setWater(0);
				
				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;	
			}
			
			else if(playertemp.getWater()>trans) {
				
				message+=(trans + " Water element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;

				playertemp.setWater(playertemp.getWater()-trans);
				dealertemp.setWater(dealertemp.getWater()+trans);
				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}	
		}

		//if(PlayerwinDice==true) {//player wins
		if(dice1>dice2) {
			int reduce = trans-dealertemp.getWater();
			
			if (dealertemp.getWater() <= trans && reduce != 0) {

				message+=(dealertemp.getWater() + " Water element power points transferred from " + dealertemp.getName()
						+ " to " + playertemp.getName())+buf;

				playertemp.setWater(playertemp.getWater() + dealertemp.getWater());
				dealertemp.setWater(dealertemp.getWater() - dealertemp.getWater());
				message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getWater() == 0 || dealertemp.getWater() == 0) {
					gameEnd=true;
				}
			}
			
		else if(dealertemp.getWater()<=trans && reduce == 0) {
					
			message+=(dealertemp.getWater() + " Water element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
					
					playertemp.setWater(playertemp.getWater()+dealertemp.getWater());
					dealertemp.setWater(0);
					
					message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}
				
				else if(dealertemp.getWater()>trans) {
					
					message+=(trans + " Water element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
			
					playertemp.setWater(playertemp.getWater()+trans);
					dealertemp.setWater(dealertemp.getWater()-trans);
					message+=("Player: " + playertemp.getName() + " Water element power - " + playertemp.getWater())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Water element power - " + dealertemp.getWater());
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}	
		JOptionPane.showMessageDialog(null, message);
		repaintEle();
		if(gameEnd==true) {
			try {
				saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dealertemp.getWater()==0) {
				promptGGP();
				}
				if(playertemp.getWater()==0) {
					promptGGD();
					}
		}
		else {playerTurn=true;}	
		}//end of Water

		if(pillar.equals("Air"))
		{	
		//int reduce;
		//if(PlayerwinDice==false) {//dealer wins
		if(dice2>dice1) {
			int reduce = trans-playertemp.getAir();
			
			if (playertemp.getAir() <= trans && reduce != 0) {

				message+=(playertemp.getAir() + " Air element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				dealertemp.setAir(dealertemp.getAir() + playertemp.getAir());
				playertemp.setAir(playertemp.getAir() - playertemp.getAir());
				
				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getAir() == 0 || dealertemp.getAir() == 0) {
					gameEnd=true;
				}
			}
			
			else if(playertemp.getAir()<=trans && reduce == 0) {
				
				message+=(playertemp.getAir() + " Air element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;
				
				dealertemp.setAir(dealertemp.getAir()+playertemp.getAir());
				playertemp.setAir(0);
				
				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;	
			}
			
			else if(playertemp.getAir()>trans) {
				
				message+=(trans + " Air element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;

				playertemp.setAir(playertemp.getAir()-trans);
				dealertemp.setAir(dealertemp.getAir()+trans);
				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}	
		}

		//if(PlayerwinDice==true) {//player wins
		if(dice1>dice2) {
			int reduce = trans-dealertemp.getAir();
			
			if (dealertemp.getAir() <= trans && reduce != 0) {

				message+=(dealertemp.getAir() + " Air element power points transferred from " + dealertemp.getName()
						+ " to " + playertemp.getName())+buf;

				playertemp.setAir(playertemp.getAir() + dealertemp.getAir());
				dealertemp.setAir(dealertemp.getAir() - dealertemp.getAir());
				message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getAir() == 0 || dealertemp.getAir() == 0) {
					gameEnd=true;
				}
			}
			
		else if(dealertemp.getAir()<=trans && reduce == 0) {
					
			message+=(dealertemp.getAir() + " Air element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
					
					playertemp.setAir(playertemp.getAir()+dealertemp.getAir());
					dealertemp.setAir(0);
					
					message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}
				
				else if(dealertemp.getAir()>trans) {
					
					message+=(trans + " Air element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
			
					playertemp.setAir(playertemp.getAir()+trans);
					dealertemp.setAir(dealertemp.getAir()-trans);
					message+=("Player: " + playertemp.getName() + " Air element power - " + playertemp.getAir())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Air element power - " + dealertemp.getAir());
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}	
		JOptionPane.showMessageDialog(null, message);
		repaintEle();
		if(gameEnd==true) {
			try {
				saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dealertemp.getAir()==0) {
				promptGGP();
				}
				if(playertemp.getAir()==0) {
					promptGGD();
					}
		}
		else {playerTurn=true;}	
		}//end of Air

		if(pillar.equals("Fire"))
		{	
		//int reduce;
		//if(PlayerwinDice==false) {//dealer wins
		if(dice2>dice1) {
			int reduce = trans-playertemp.getFire();
			
			if (playertemp.getFire() <= trans && reduce != 0) {

				message+=(playertemp.getFire() + " Fire element power points transferred from " + playertemp.getName()
						+ " to " + dealertemp.getName())+buf;

				dealertemp.setFire(dealertemp.getFire() + playertemp.getFire());
				playertemp.setFire(playertemp.getFire() - playertemp.getFire());
				
				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getFire() == 0 || dealertemp.getFire() == 0) {
					gameEnd=true;
				}
			}
			
			else if(playertemp.getFire()<=trans && reduce == 0) {
				
				message+=(playertemp.getFire() + " Fire element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;
				
				dealertemp.setFire(dealertemp.getFire()+playertemp.getFire());
				playertemp.setFire(0);
				
				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				gameEnd = true;	
			}
			
			else if(playertemp.getFire()>trans) {
				
				message+=(trans + " Fire element power points transferred from " + playertemp.getName() + " to " + dealertemp.getName())+buf;

				playertemp.setFire(playertemp.getFire()-trans);
				dealertemp.setFire(dealertemp.getFire()+trans);
				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
				p.addCard(playertemp);
				d.addCard(dealertemp);
			}	
		}

		//if(PlayerwinDice==true) {//player wins
		if(dice1>dice2) {
			int reduce = trans-dealertemp.getFire();
			
			if (dealertemp.getFire() <= trans && reduce != 0) {

				message+=(dealertemp.getFire() + " Fire element power points transferred from " + dealertemp.getName()
						+ " to " + playertemp.getName())+buf;

				playertemp.setFire(playertemp.getFire() + dealertemp.getFire());
				dealertemp.setFire(dealertemp.getFire() - dealertemp.getFire());
				message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
				message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
				p.addCard(playertemp);
				d.addCard(dealertemp);
				if(playertemp.getFire() == 0 || dealertemp.getFire() == 0) {
					gameEnd=true;
				}
			}
			
		else if(dealertemp.getFire()<=trans && reduce == 0) {
					
			message+=(dealertemp.getFire() + " Fire element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
					
					playertemp.setFire(playertemp.getFire()+dealertemp.getFire());
					dealertemp.setFire(0);
					
					message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
					p.addCard(playertemp);
					d.addCard(dealertemp);
					gameEnd = true;
				}
				
				else if(dealertemp.getFire()>trans) {
					
					message+=(trans + " Fire element power points transferred from " + dealertemp.getName() + " to " + playertemp.getName())+buf;
			
					playertemp.setFire(playertemp.getFire()+trans);
					dealertemp.setFire(dealertemp.getFire()-trans);
					message+=("Player: " + playertemp.getName() + " Fire element power - " + playertemp.getFire())+buf;
					message+=("Dealer: " + dealertemp.getName() + " Fire element power - " + dealertemp.getFire());
					p.addCard(playertemp);
					d.addCard(dealertemp);
				}		
			}	
		JOptionPane.showMessageDialog(null, message);
		repaintEle();
		if(gameEnd==true) {
			try {
				saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dealertemp.getFire()==0) {
				promptGGP();
				}
				if(playertemp.getFire()==0) {
					promptGGD();
					}
		}
		else {playerTurn=true;}	
		}//end of Fire
			
	}
		if (playerTurn==true) {
			JOptionPane.showMessageDialog(null,"Player's Turn");
			GamePlay gp = new GamePlay(this);
			gp.pp(p.getCardsOnHand(), d.getCardsOnHand());
			}
		
	}
	
	public void repaintAll() {
		for(int i = 0 ; i<3 ; i++)
		{
			String pntxt = (p.cardsOnHand.get(i).getName() + " - " + p.cardsOnHand.get(i).getDescription());
			String dntxt = (d.cardsOnHand.get(i).getName() + " - " + d.cardsOnHand.get(i).getDescription());
			String pptxt = "img/"+p.cardsOnHand.get(i).getName()+".jpg";
			String dptxt = "img/"+d.cardsOnHand.get(i).getName()+".jpg";
			ImageIcon pi = new ImageIcon(pptxt);
			ImageIcon di = new ImageIcon(dptxt);
			if(i == 0) {
				pName1.setText(pntxt);
				pName1.repaint();
				pcn1.repaint();
				
				pEle1.setIcon(pi);
				pEle1.repaint();
				pcp1.repaint();
				
				dName1.setText(dntxt);
				dName1.repaint();
				dcn1.repaint();
				
				dEle1.setIcon(di);
				dEle1.repaint();
				dcp1.repaint();
				}
				if(i == 1) {
				pName2.setText(pntxt);
				pName2.repaint();
				pcn2.repaint();
				dName2.setText(dntxt);
				dName2.repaint();
				dcn2.repaint();
				pEle2.setIcon(pi);
				pEle2.repaint();
				pcp2.repaint();
				dEle2.setIcon(di);
				dEle2.repaint();
				dcp2.repaint();
				}
				if(i == 2) {
				pName3.setText(pntxt);
				pName3.repaint();
				pcn3.repaint();	
				dName3.setText(dntxt);
				dName3.repaint();
				dcn3.repaint();
				pEle3.setIcon(pi);
				pEle3.repaint();
				pcp3.repaint();
				dEle3.setIcon(di);
				dEle3.repaint();
				dcp3.repaint();
				}
		}
		repaintEle();
	}
	
	public void repaintEle() {
		for(int i = 0 ; i<3 ; i++)
		{
			int pTotal1 = p.cardsOnHand.get(i).getEarth() + p.cardsOnHand.get(i).getWater() + p.cardsOnHand.get(i).getAir() + p.cardsOnHand.get(i).getFire();
			String pa1txt = "E: " + p.cardsOnHand.get(i).getEarth() + " W: " + p.cardsOnHand.get(i).getWater() +
				" A: " + p.cardsOnHand.get(i).getAir() + " F: " + p.cardsOnHand.get(i).getFire() + " T :" +pTotal1;
			int dTotal1 = d.cardsOnHand.get(i).getEarth() + d.cardsOnHand.get(i).getWater() + d.cardsOnHand.get(i).getAir() + d.cardsOnHand.get(i).getFire();
			String da1txt = "E: " + d.cardsOnHand.get(i).getEarth() + " W: " + d.cardsOnHand.get(i).getWater() +
				" A: " + d.cardsOnHand.get(i).getAir() + " F: " + d.cardsOnHand.get(i).getFire() + " T :" +dTotal1;
		if(i == 0) {
			pa1.setText(pa1txt);
			pa1.repaint();
			pce1.repaint();	
			da1.setText(da1txt);
			da1.repaint();
			dce1.repaint();	
			}
			if(i == 1) {
			pa2.setText(pa1txt);
			pa2.repaint();
			pce2.repaint();
			da2.setText(da1txt);
			da2.repaint();
			dce2.repaint();
			}
			if(i == 2) {
			pa3.setText(pa1txt);
			pa3.repaint();
			pce3.repaint();	
			da3.setText(da1txt);
			da3.repaint();
			dce3.repaint();	
			}
		}
	}
	
	public void promptGGP() {
		int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Player wins!"+buf+"Game Ended! Do you wish to procceed for a new game?", "Game End",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		    	 p = new Player();
		    	 
				 try {
					d = new Dealer();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				d.shuffleCards();
				for (int i=0; i < 3; i++) {
					p.addCard(d.dealCard());
					d.addCard(d.dealCard());
					}
				repaintAll();
				GamePlay gp = new GamePlay(this);
				gp.pp(p.getCardsOnHand(), p.getCardsOnHand());
		    }
		    if (confirmed == JOptionPane.NO_OPTION) {
		    	System.exit(confirmed);
		      //dispose();
		    }
	}
	
	public void promptGGD() {
		int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Dealer wins!"+buf+"Game Ended! Do you wish to procceed for a new game?", "Game End",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		    	 p = new Player();
		    	 
				 try {
					d = new Dealer();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				d.shuffleCards();
				for (int i=0; i < 3; i++) {
					p.addCard(d.dealCard());
					d.addCard(d.dealCard());
					}
				repaintAll();
				GamePlay gp = new GamePlay(this);
				gp.pp(p.getCardsOnHand(), d.getCardsOnHand());
		    }
		    if (confirmed == JOptionPane.NO_OPTION) {
		    	System.exit(confirmed);
		      //dispose();
		    }
	}
	
	public void saveData() throws IOException{
		d.deckExp();
	    FileWriter writer = new FileWriter("GameCards.dat",true);
		String buf = System.getProperty("line.separator");
		String sp = "|";
		for (int i=0;i<3;i++) {
			String deckExport = p.cardsOnHand.get(i).getName()+sp+p.cardsOnHand.get(i).getDescription()+sp+p.cardsOnHand.get(i).getEarth()
			+sp+p.cardsOnHand.get(i).getWater()+sp+p.cardsOnHand.get(i).getAir()+sp+p.cardsOnHand.get(i).getFire()+buf+
			d.cardsOnHand.get(i).getName()+sp+d.cardsOnHand.get(i).getDescription()+sp+d.cardsOnHand.get(i).getEarth()
			+sp+d.cardsOnHand.get(i).getWater()+sp+d.cardsOnHand.get(i).getAir()+sp+d.cardsOnHand.get(i).getFire()+buf;
			writer.write(deckExport);
		}
		writer.flush();
		writer.close();
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

