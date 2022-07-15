package blackjack;

public class DeckHandler {
 
	private Card[] deck = new Card[52];
	
	public DeckHandler() {
		int i = 0;
		for (cardSuit s : CardSuit.values())
            for (CardValue v : CardValue.values()) {
                deck[i] = new Card(v, s);
                ++i;
            }
	}
  
}
