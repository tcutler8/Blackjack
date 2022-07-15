package blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	private List<Card> hand = new ArrayList<>();
	
	public void deal(Card card) {hand.add(card);}
	
	public List<Card> getHand() {return hand;}
	
	public int getScore() {
		int acesCount = 0;
		int score     = 0;

		for (Card c : hand) 
			switch (c.getValue()) {
				case ACE:
					++acesCount;
					break;
				case KING:
				case QUEEN:
				case JACK:
				case TEN:   ++score;
				case NINE:  ++score;
				case EIGHT: ++score;
				case SEVEN: ++score;
				case SIX:   ++score;
				case FIVE:  ++score;
				case FOUR:  ++score;
				case THREE: ++score;
				case TWO:   ++score;
				default:    ++score;
			}
		
		if (score < (12 - acesCount) && acesCount > 0) 
			return score += 11 + (acesCount -1);
		else
			return score += acesCount;
	}
	
}
