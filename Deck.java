/* CSCI213 Assignment 3
 * --------------------
 * File name: Deck.java
 * Author: Minton Yap Li Hui
 * Student Number: 5987398
 * Description: Consists of arrayList of cards in which methods such as shuffling can be invoked by the dealer
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deck{
private ArrayList<Card> cards;

public Deck() throws FileNotFoundException {
cards = new ArrayList<Card>();

Scanner s = new Scanner(new File("GameCards.dat"));

while(s.hasNextLine()){
	String gc = s.nextLine();
	String[] data = gc.split("\\|");
	
	Card c1 = new Card(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
	
	cards.add(c1);
	}
	//s.close();
}

public void showCards() {
for(Card card: cards) {
System.out.println(card);
}
}

public Card dealCard() {
return cards.remove(0);
}

public void appendCard(Card c) {
cards.add(c);
}

public void appendCards(ArrayList<Card> cards) {
shuffle(cards);
for (Card c: cards)
this.cards.add(c);
}

public void shuffle() {
shuffle(cards);
}

public void writeCardsToFile() throws IOException {
    FileWriter writer = new FileWriter("GameCards.dat"); // ,true
	String buf = System.getProperty("line.separator");
	String sp = "|";
	for (int i=0;i<cards.size();i++) {
		String deckExport = cards.get(i).getName()+sp+cards.get(i).getDescription()+sp+cards.get(i).getEarth()
		+sp+cards.get(i).getWater()+sp+cards.get(i).getAir()+sp+cards.get(i).getFire()+buf;
		writer.write(deckExport);
	}
	writer.flush();
	writer.close();
}

public void shuffle(ArrayList<Card> cards) {
Random r = new Random();
for(int i=0;i<cards.size()*1000; i++) {
int a = r.nextInt (cards.size());
int b = r.nextInt(cards.size());
Card cardA = cards.get(a);
Card cardB = cards.get(b);
cards.set(a, cardB);
cards.set(b, cardA);
}
}

}