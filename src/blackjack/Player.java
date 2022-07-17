package blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a player in Blackjack.
 * 
 * @author tayson
 */
public abstract class Player {

	private List<Card> hand = new ArrayList<>();
	
	/**
	 * Deal the Card to the Player.
	 * @param card to deal
	 */
	public void deal(Card card) {hand.add(card);}
	
	/**
	 * Remove all cards from the player's hands. 
	 */
	public void clearHand() {hand.clear();}
	
	/**
	 * @return List of all cards in the Player's hand
	 */
	public List<Card> getHand() {return hand;}
	
	/**
	 * @return total score of the Player's hand
	 */
	public int getTotalScore() {
		int acesCount = 0;
		int score     = 0;

		for (Card c : hand) 
			// fall through switch that adds 1 to the score 
			// for the card in question and every card with
			// a value that is less than it. 
			switch (c.getValue()) {
				case KING:
				case QUEEN:
				case JACK:
				case TEN:   score++;
				case NINE:  score++;
				case EIGHT: score++;
				case SEVEN: score++;
				case SIX:   score++;
				case FIVE:  score++;	
				case FOUR:  score++;
				case THREE: score++;
				case TWO:   score++;
				// used later in case the ace can worth be 11 points
				case ACE:	score++; acesCount++; 
			}
		
		// return the score, but add 10 to it if the score
		// is less than 12 and there is at least 1 ace
		return score += (((score < 12) && (acesCount > 0)) ? 10 : 0);
	}
	
}
