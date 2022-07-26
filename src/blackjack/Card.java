package blackjack;

import javax.swing.ImageIcon;

/**
 * Represents a basic playing card.
 *  
 * @author tayson
 */
public class Card {

	private final CardValue value;
	private final CardSuit  suit;

	private final ImageIcon face;
	private static final ImageIcon back = new ImageIcon(Card.class.getResource(
			"cards-png/card-back.png"));
	
	private boolean isInUse = false;
	
	/**
	 * Initialize the Card with a Value and a Suit.
	 * @param value 
	 * @param suit
	 */
	public Card(CardValue value, CardSuit suit) {
		this.value = value;
		this.suit  = suit;
		this.face = new ImageIcon(getClass().getResource(
				"cards-png/"
				+ value.toString() + "-" + suit.toString()
				+ ".png"
				));
	}
	/**
	 * @return the image of the back of a card
	 */
	public static ImageIcon getBackImage() {return back;}
	
	/**
	 * @return the card's corresponding image
	 */
	public ImageIcon getFaceImage() {return face;}

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
