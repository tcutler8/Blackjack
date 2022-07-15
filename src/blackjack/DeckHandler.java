package blackjack;

public class DeckHandler {

	private final Card[] deck = new Card[52];

	public DeckHandler() {
		int i = 0;
		for (final CardSuit s : CardSuit.values())
			for (final CardValue v : CardValue.values()) {
				deck[i] = new Card(v, s);
				++i;
			}
	}

}
