package blackjack;

/**
 * Represents a basic playing card.
 *  
 * @author tayson
 */
public class Card {

	private final CardValue value;
	private final CardSuit  suit;
	
	private boolean isInUse = false;
	
	/**
	 * Initialize the Card with a Value and a Suit.
	 * @param value 
	 * @param suit
	 */
	public Card(CardValue value, CardSuit suit) {
		this.value = value;
		this.suit  = suit;
	}
	
	/**
	 * @return value of the card.
	 */
	public CardValue getValue() {return value;}
	
	/**
	 * @return true if the card has been dealt, false if not.
	 */
	public boolean isInUse() {return isInUse;}
	
	/**
	 * Declare whether the card is in use (in hand or discard 
	 * pile) or is in the deck ready to be dealt. 
	 * @param use
	 */
	public void use(boolean use) {isInUse = use;}
	
	/**
	 * Return a string in a similar format:
	 * "QUEEN of CLUBS"
	 */
	@Override
	public String toString() {return value + " of " + suit;}
	
}
