package blackjack;

public class Dealer extends Player {

	/**
	 * Decides whether or not the dealer should hit.
	 * Dealer HITS at a total score of 16 or below.
	 * Dealer STAYS at a total score of 17 or higher.
	 * @return
	 */
	public boolean shouldHit() {
		return (super.getTotalScore() > 16) ? false : true;
	}
	
	/**
	 * @return first card in the dealer's hand
	 */
	public Card getFirstCard() {return super.getHand().get(0);}
	
	/**
	 * @return score of the first card in the dealer's hand
	 */
	public int getFirstCardScore() {
		switch (getFirstCard().getValue()) {
			case ACE:   return 11;
			case KING:
			case QUEEN:
			case JACK:
			case TEN:   return 10;
			case NINE:  return 9;
			case EIGHT: return 8;
			case SEVEN: return 7;
			case SIX:   return 6;
			case FIVE:  return 5;	
			case FOUR:  return 4;
			case THREE: return 3;
			default:	return 2;
		}
	}
	
}
