/* CSCI213 Assignment 3
 * --------------------
 * File name: GamePlay.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Contains gameplay functions
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class GamePlay implements MouseListener{

	//boolean gameEnd = false;
	//boolean playerTurn=true;//player turn
	boolean PlayerwinDice=false;
	int difference = 0;
	int transfer = 0;
	int turn = 1;
	
	JLabel pillarLabel;
	//private GameDisplay gameDisplay;
	GameDisplay Gamedis;
	JFrame pView;
	
	String pillarC = "";
	
	public GamePlay(GameDisplay Gamedis)
	{
		this.Gamedis=Gamedis;
	}
	
	public void pp(ArrayList<Card> playercard, ArrayList<Card> dealercard) {
		
		String buf = System.getProperty("line.separator");
		Gamedis.panel.setBackground(Color.PINK);
		
		// TODO Auto-generated method stub
		pView = new JFrame("War of Greek Gods");
		pView.setSize(400, 300);
		pView.setResizable(false);
		pView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pView.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		pView.add(panel);
		
		pillarLabel = new JLabel();
		pillarLabel = new JLabel("Pillar");
		pillarLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pillarLabel.setBounds(50, 30, 100, 50);
		panel.add(pillarLabel);
		
		String[] pillar = {"Earth", "Water", "Air", "Fire"};
		JComboBox promptP = new JComboBox(pillar);
		promptP.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		promptP.setBounds(220, 30, 100, 50);
		panel.add(promptP);
		
		JLabel cardLabel = new JLabel();
		cardLabel = new JLabel("Game Cards");
		cardLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		cardLabel.setBounds(50, 100, 140, 50);
		panel.add(cardLabel);
		
		String[] cardc = {playercard.get(0).getName(), playercard.get(1).getName(),playercard.get(2).getName()};
		JComboBox promptC = new JComboBox(cardc);
		promptC.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		promptC.setBounds(220, 100, 100, 50);
		panel.add(promptC);
		
		JButton button = new JButton("OK");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		button.setBounds(150, 180, 80, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				int promptP1 = promptP.getSelectedIndex();	
				String msg2 = pillar[promptP1]; 
				int promptC1 = promptC.getSelectedIndex();			
				
					switch(msg2) {
					case "Earth":
						//GameDisplay.panel.setBackground(Color.GREEN);
						Gamedis.panel.setBackground(Color.GREEN);
						JOptionPane.showMessageDialog(null, "Player Sent Card "+playercard.get(promptC1).getName()+" to Earth Pillar");
						pView.dispose();	
						//pView.setVisible(false);
						break;
					case "Water":
						Gamedis.panel.setBackground(Color.CYAN);
						JOptionPane.showMessageDialog(null, "Player Sent Card "+playercard.get(promptC1).getName()+" to Water Pillar");
						pView.dispose();
						//pView.setVisible(false);
						break;	
					case "Air":
						Gamedis.panel.setBackground(Color.GRAY);
						JOptionPane.showMessageDialog(null, "Player Sent Card "+playercard.get(promptC1).getName()+" to Air Pillar");
						pView.dispose();
						//pView.setVisible(false);
						break;
					case "Fire":
						Gamedis.panel.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null, "Player Sent Card "+playercard.get(promptC1).getName()+" to Fire Pillar");
						pView.dispose();
						//pView.setVisible(false);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Element power of card cannot be '0'! ");
					}
					Player p = new Player();
					
					boolean rerolldice = false;
					PlayerwinDice = false;
					int diceResult1 = p.diceRoll();
					int diceResult2 = 0;
					Dealer d = null;
					int dealercardnum = 0;
					try {
						d = new Dealer();
						dealercardnum = d.dealerChooseCard();
						
						//if(dealercardnum==0) {
							JOptionPane.showMessageDialog(null, "Dealer Sent Card "+dealercard.get(dealercardnum).getName()+" to " +msg2+ " Pillar");
						//}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//int difference = 0;
					//int transfer = 0;
					
					do {
						diceResult1 = p.diceRoll();
						diceResult2 = d.diceRoll();
						rerolldice = false;
						if (diceResult1 < diceResult2) {
							//System.out.println("Dealer wins");
							JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 + buf + " Dealer wins dice roll");
							difference = diceResult2 - diceResult1;
							transfer = difference * 10;
							PlayerwinDice = false;//
							rerolldice = false;
						}
						else if (diceResult1 > diceResult2) {
							//System.out.println("Player wins");
							JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 + buf + " Player wins dice roll");

							difference = diceResult1 - diceResult2;
							transfer = difference * 10;
							PlayerwinDice = true;//
							rerolldice = false;
						}

						else if(diceResult1 == diceResult2)
						{
							JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 +buf+ " It is a tie! Re-rolling dice..");
							//System.out.println("It is a tie! Re-rolling dice..");
							rerolldice = true;
						}
					} while (rerolldice == true);
					
					try {
						Gamedis.promptCallBack(msg2, playercard.get(promptC1).getName(), dealercardnum, diceResult1, diceResult2, difference, transfer);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}		
		});
		panel.add(button);
		//GameDisplay.playerTurn=false;
	}
	
	public void dp(ArrayList<Card> playercard, ArrayList<Card> dealercard) throws FileNotFoundException {
		
		Dealer dealer = new Dealer();
		Player p = new Player();
		String buf = System.getProperty("line.separator");
		//String sp = "|";
		String pillar="";
		Random r = new Random();
		int c = r.nextInt(4)+1;	
		boolean rerolldice = false;
		PlayerwinDice = false;
		int diceResult1 = p.diceRoll();
		int diceResult2 = 0;
		Dealer d = null;
		int dealercardnum = 0;
		String cardChoice="";
		
		try {
			d = new Dealer();
			dealercardnum = d.dealerChooseCard();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//char decision = ' ';
		if(c==1)
		{
			Gamedis.panel.setBackground(Color.GREEN);
			//decision = 'e';
			pillar+=("Earth");
			JOptionPane.showMessageDialog(null, "Dealer chooses Earth pillar"+buf+"Dealer Sent Card "+dealercard.get(dealercardnum).getName()+" to Earth Pillar");
			String[] cardc = { playercard.get(0).getName(), playercard.get(1).getName(),playercard.get(2).getName() };
			cardChoice = (String) JOptionPane.showInputDialog(null, "Player Game Cards", "Select Player Card Choice", JOptionPane.QUESTION_MESSAGE,  null, cardc, cardc[0]);
			//int validate = 0;
			//validate = cardChoice.getSelectedIndex();
			//if(playercard.get(0).getEarth()==0 || playercard.get(1).getEarth()==0 || playercard.get(2).getEarth()==0) {
			//	JOptionPane.showMessageDialog(null, "Card sent to pillar contains element value of '0'!");
			//} // loop 
		}
		if(c==2)
		{
			Gamedis.panel.setBackground(Color.CYAN);
			pillar+=("Water");
			JOptionPane.showMessageDialog(null, "Dealer chooses Water pillar"+buf+"Dealer Sent Card "+dealercard.get(dealercardnum).getName()+" to Water Pillar");
			
			String[] cardc = { playercard.get(0).getName(), playercard.get(1).getName(),playercard.get(2).getName() };
			cardChoice = (String) JOptionPane.showInputDialog(null, "Player Game Cards", "Select Player Card Choice", JOptionPane.QUESTION_MESSAGE,  null, cardc, cardc[0]);
		}
		if(c==3)
		{
			Gamedis.panel.setBackground(Color.GRAY);
			pillar+=("Air");
			JOptionPane.showMessageDialog(null, "Dealer chooses Air pillar"+buf+"Dealer Sent Card "+dealercard.get(dealercardnum).getName()+" to Air Pillar");
			
			String[] cardc = { playercard.get(0).getName(), playercard.get(1).getName(),playercard.get(2).getName() };
			cardChoice = (String) JOptionPane.showInputDialog(null, "Player Game Cards", "Select Player Card Choice", JOptionPane.QUESTION_MESSAGE,  null, cardc, cardc[0]);
		}
		if(c==4)
		{
			Gamedis.panel.setBackground(Color.RED);
			pillar+=("Fire");
			JOptionPane.showMessageDialog(null, "Dealer chooses Fire pillar"+buf+"Dealer Sent Card "+dealercard.get(dealercardnum).getName()+" to Fire Pillar");	
			String[] cardc = { playercard.get(0).getName(), playercard.get(1).getName(),playercard.get(2).getName() };
			cardChoice = (String) JOptionPane.showInputDialog(null, "Player Game Cards", "Select Player Card Choice", JOptionPane.QUESTION_MESSAGE,  null, cardc, cardc[0]);
		}
		
		do {
			diceResult1 = p.diceRoll();
			diceResult2 = d.diceRoll();
			rerolldice = false;
			if (diceResult1 < diceResult2) {
				//System.out.println("Dealer wins");
				JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 + buf + " Dealer wins dice roll");
				difference = diceResult2 - diceResult1;
				transfer = difference * 10;
				PlayerwinDice = false;//
				rerolldice = false;
			}
			else if (diceResult1 > diceResult2) {
				//System.out.println("Player wins");
				JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 + buf + " Player wins dice roll");

				difference = diceResult1 - diceResult2;
				transfer = difference * 10;
				PlayerwinDice = true;//
				rerolldice = false;
			}

			else if(diceResult1 == diceResult2)
			{
				JOptionPane.showMessageDialog(null, "Player rows dice: " + diceResult1 + " Dealer rolls dice: "+ diceResult2 +buf+ " It is a tie! Re-rolling dice..");
				//System.out.println("It is a tie! Re-rolling dice..");
				rerolldice = true;
			}
		} while (rerolldice == true);
		Gamedis.promptCallBack2(pillar, cardChoice, dealercard.get(dealercardnum).getName(), dealercardnum, diceResult1, diceResult2, difference, transfer);
		// cardChoice,
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == Gamedis.playerCard1) {
		JOptionPane.showMessageDialog(null,"Player Card 1"); //Gamedis.playerCard1
		}
		if(arg0.getSource() == Gamedis.playerCard2) {
			JOptionPane.showMessageDialog(null,"Player Card 2");
		}
		if(arg0.getSource() == Gamedis.playerCard3) {
			JOptionPane.showMessageDialog(null,"Player Card 3");
		}
		if(arg0.getSource() == Gamedis.dealerCard1) {
			JOptionPane.showMessageDialog(null,"Dealer Card 1"); //Gamedis.dealerCard1
			}
			if(arg0.getSource() == Gamedis.dealerCard2) {
				JOptionPane.showMessageDialog(null,"Dealer Card 2");
			}
			if(arg0.getSource() == Gamedis.dealerCard3) {
				JOptionPane.showMessageDialog(null,"Dealer Card 3");
			}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == Gamedis.playerCard1) {
		Gamedis.playerCard1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
		//Gamedis.playerCard1.setSize(400, 560);
		}
		if(arg0.getSource() == Gamedis.playerCard2) {
		Gamedis.playerCard2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
		}
		if(arg0.getSource() == Gamedis.playerCard3) {
		Gamedis.playerCard3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
		}
		if(arg0.getSource() == Gamedis.dealerCard1) {
			Gamedis.dealerCard1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
			}
			if(arg0.getSource() == Gamedis.dealerCard2) {
				Gamedis.dealerCard2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
			}
			if(arg0.getSource() == Gamedis.dealerCard3) {
				Gamedis.dealerCard3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLACK,Color.RED));
			}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Gamedis.playerCard1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		Gamedis.playerCard2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		Gamedis.playerCard3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		Gamedis.dealerCard1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		Gamedis.dealerCard2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		Gamedis.dealerCard3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
