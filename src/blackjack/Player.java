package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int balance;
	private List<Card> hand = new ArrayList<>();
	
	public Player() {
		
	}
	
	public void deal(Card card) {
		
	}
	
	public int getBalance() {
		return balance;
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public int getScore() {
		return -1;
	}
	
}
