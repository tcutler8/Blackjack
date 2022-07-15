package blackjack;

public class Dealer extends Player {

	public boolean shouldHit() {
		return (super.getScore() > 16) ? false : true;
	}
	
}
