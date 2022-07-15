package blackjack;

import java.util.Random;

public class DeckHandler {

	private final Random rng = new Random();
	
	private final int DECK_SIZE = 52;
	private final Card[] deck = new Card[DECK_SIZE];

	public DeckHandler() {
		int i = 0;
		for (final CardSuit s : CardSuit.values())
			for (final CardValue v : CardValue.values()) {
				deck[i] = new Card(v, s);
				++i;
			}
	}
	
	public void dealTo(Player player) {
		Card card;
		
		boolean alreadyUsed;
		// makes sure the card hasn't already been dealt before dealing
		do {
			alreadyUsed = false;
			card = deck[rng.nextInt(DECK_SIZE)];
			for (Card c : deck)
				if (c.isInUse())
					alreadyUsed = true;
		} while (alreadyUsed);
		
		player.deal(card);
	}

}
