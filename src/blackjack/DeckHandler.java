package blackjack;

import java.util.Random;

public class DeckHandler {

	private final Random rng = new Random();
	
	private final int DECK_SIZE = 52;
	private final Card[] fullDeck = new Card[DECK_SIZE];

	public DeckHandler() {
		int i = 0;
		for (final CardSuit s : CardSuit.values())
			for (final CardValue v : CardValue.values()) {
				fullDeck[i] = new Card(v, s);
				++i;
			}
	}
	
	public void dealTo(Player player) {
		int randInt;
		do {
			randInt = rng.nextInt(DECK_SIZE);
		} while (fullDeck[randInt].isInUse());
		fullDeck[randInt].use(true);
		
		player.deal(fullDeck[randInt]);
	}

}
