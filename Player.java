/* CSCI213 Assignment 3
 * --------------------
 * File name: Player.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Contains all player functions
 */

import java.util.ArrayList;
import java.util.Random;

public class Player{
protected ArrayList<Card> cardsOnHand;
private boolean stand;
private boolean isHuman;

public Player(boolean isHuman) {
super();
this.isHuman = isHuman;
cardsOnHand = new ArrayList<Card>();
stand = false;
}

public Player() {
super();
this.isHuman = true;
cardsOnHand = new ArrayList<Card>();
stand = false;
}

public void addCard (Card card) {
cardsOnHand.add(card);
}

public Card removeCard(String cardname)
{
	int temp = 0;
	for(int i = 0 ; i<cardsOnHand.size();i++)
	{
		if(cardsOnHand.get(i).getName().equals(cardname))
			temp = i;
	}
	return cardsOnHand.get(temp);
}

public ArrayList<Card> getCardsOnHand() {
return cardsOnHand;
}

//public char nextMove() {
//return Keyboard.readChar("Player choose a pillar for war (E/W/A/F) : ");
//}

public void showCards() {
for(Card card: cardsOnHand) {
System.out.println(card);
}
}

public void showNextCards() {
	for (int i = 3; i < 6; i++) {
		System.out.println(cardsOnHand.get(i));
	}
}

public void showFirst() {
	
	for (int i = 0; i < 3; i++) {
		System.out.println((i+1) + " - " + cardsOnHand.get(i));
	}

}

public int diceRoll() {
	int multiply = 0;
	Random random = new Random();
	multiply = random.nextInt(6)+1;
	
	return multiply;
}

}