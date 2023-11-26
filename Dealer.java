/* CSCI213 Assignment 3
 * --------------------
 * File name: Dealer.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Contains dealer functions, inherits from Player Class
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Dealer extends Player {
Deck deck;
public Dealer() throws FileNotFoundException {
super (false);
deck = new Deck();
}

public void deckExp() throws IOException
{
	deck.writeCardsToFile();
}


public void shuffleCards() {
deck.shuffle();
}

public Card dealCard() {
return deck.dealCard();
}

public int dealerChooseCard()
{
	//boolean validate = false;
	Random r = new Random();
	int temp;
	temp = r.nextInt(3);
	
	return temp;
	
}

}