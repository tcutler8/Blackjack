package blackjack;

import java.util.Random;

/**
 * Handles a 52-card deck of playing cards.
 * 
 * @author tayson
 */
public class DeckHandler {

	private final Random rng = new Random();
	
	/**
	 * The number of cards in a deck of playing cards.
	 */
	public static final int DECK_SIZE = 52;
	private final Card[] fullDeck = new Card[DECK_SIZE];

	/**
	 * Initialize the deck with all 52 cards.
	 */
	public DeckHandler() {
		int i = 0;
		for (final CardSuit s : CardSuit.values())
			for (final CardValue v : CardValue.values()) {
				fullDeck[i] = new Card(v, s);
				++i;
			}
	}
	
	/**
	 * @return number of cards left in the deck.
	 */
	public int getCardsLeftCount() {
		int count = DECK_SIZE;
		for (Card c : fullDeck)
			if (c.isInUse())
				--count;
		return count;
	}
	
	/**
	 * Deal a card from the deck to <code>player</code>.
	 * 
	 * @param player to deal to
	 */
	public void dealTo(Player player) {
		int randInt;
		do {
			randInt = rng.nextInt(DECK_SIZE);
		} while (fullDeck[randInt].isInUse());
		fullDeck[randInt].use(true);
		
		player.deal(fullDeck[randInt]);
	}
	
	/**
	 * Shuffle the deck. Allows all cards to be used again.
	 */
	public void shuffle() {
		for (Card c : fullDeck)
			c.use(false);
	}

}
