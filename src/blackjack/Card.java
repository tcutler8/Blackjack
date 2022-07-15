package blackjack;

public class Card {

	private CardValue value;
	private CardSuit  suit;
	private boolean isInUse = false;
	
	public Card(CardValue value, CardSuit suit) {
		this.value = value;
		this.suit  = suit;
	}
	
	public boolean isInUse() {return isInUse;}
	
	public void use(boolean use) {isInUse = use;}
	
}
