package blackjack;

import java.util.Scanner;

public class BlackjackGame {

	public static void main(String[] args) {
		User player1 = new User(2500);
		Dealer dealer = new Dealer();
		DeckHandler deck = new DeckHandler();
		Scanner scan = new Scanner(System.in);
		int bet = 0;
		String hitOrStay = "h";
		
		// Player1 starting chip count
		System.out.println("Player starting chip count: " + player1.getBalance());
		System.out.print("Enter bet amount: ");
		bet = scan.nextInt();
		System.out.println();
		
		// Dealer starting out with two cards
		deck.dealTo(dealer);
		deck.dealTo(dealer);
		System.out.println("Dealer:");
		System.out.println("Card showing:   " + dealer.getFirstCard());
		System.out.println("Score of card:  " + dealer.getFirstCardScore());
		System.out.println();
		
		// Player starting out with two cards
		System.out.println("Player:");
		deck.dealTo(player1);
		deck.dealTo(player1);
		System.out.println("Hand:	" + player1.getHand().toString().replace("[", "").replace("]", ""));
		System.out.println("Score:	" + player1.getTotalScore());
		if (player1.getTotalScore() == 21) {
			System.out.println("BLACKJACK!!!");
			hitOrStay = "s";
		}
		System.out.println();
		
		// Player to hit or stay. This will be a button on the JPanel
		// Type in "h" to take a hit and "s" to stay
		while (hitOrStay.equals("h")) {
			System.out.print("Hit (h) or Stay (s)?: ");
			hitOrStay = scan.next();
			System.out.println();
			if (hitOrStay.equals("h")) {
				deck.dealTo(player1);
				System.out.println("Hand:	" + player1.getHand().toString().replace("[", "").replace("]", ""));
			}
			if (player1.getTotalScore() == 21) {
				hitOrStay = "s";
				System.out.println("BLACKJACK!!!");
			}
			if (hitOrStay.equals("s")) {
				System.out.println("Player will stay with a score of " + player1.getTotalScore());
			}
			if (player1.getTotalScore() > 21) {
				System.out.println("Score:	"	+ player1.getTotalScore() + " BUST!");
				hitOrStay = "s";
			}
			else {
				System.out.println("Score:	" + player1.getTotalScore());
			}
		}
		System.out.println();
		scan.close();

		// Run the dealer's hand. Will stay at 17
		while (dealer.shouldHit()) {
			deck.dealTo(dealer);
		}
		
		// Show dealer's hand
		System.out.println("Dealer Hand:  " + dealer.getHand().toString().replace("[", "").replace("]", ""));
		System.out.println("Score:	      " + dealer.getTotalScore());
		System.out.println();

		// Test to see if player wins
		if (player1.getTotalScore() < 22 && player1.getTotalScore() > dealer.getTotalScore() || dealer.getTotalScore() > 21) {
			System.out.println("Player wins!!");
			player1.changeBalance(bet); // Player wins bet
		}
		else {
			System.out.println("Player loses!!");
			player1.changeBalance(bet * -1); // Player loses bet
		}
		System.out.println();
		
		// Show player's updated balance
		System.out.println("Player current chip count: " + player1.getBalance());
		
		// This is to show how many cards left in the deck
		System.out.print("Amount of cards left in the deck: ");
		System.out.println(deck.getCardsLeftCount());
		
		// Return cards and shuffle the deck 
		dealer.clearHand();
		player1.clearHand();
		deck.shuffle();
		System.out.print("Amount of cards in new deck: ");
		System.out.println(deck.getCardsLeftCount());
		
	}

}
