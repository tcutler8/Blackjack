package blackjack;

import java.awt.EventQueue;
import java.util.Scanner;

/**
 * Game of BlackJack between the dealer (computer) and the user. 
 * 
 * @author mark + tayson
 */
public class BlackjackGame {
	
	public final static User player = new User(2500);
	private static Dealer dealer = new Dealer();
	private static DeckHandler deck = new DeckHandler();
	private static int bet = 0;

	public static void main(String[] args) {

		System.setProperty("awt.useSystemAAFontSettings","on"); // set anti-aliasing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackjackFrame frame = new BlackjackFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void setBet(int bet) {BlackjackGame.bet = bet;}

	@SuppressWarnings("unused")
	private static void consoleTestClient() {
		Scanner     scan 	 = new Scanner(System.in);
		String      hitOrStay = "h";
		
		// Player1 starting chip count
		System.out.println("Player starting chip count: " + player.getBalance());
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
		deck.dealTo(player);
		deck.dealTo(player);
		System.out.println("Hand:	" + player.getHand().toString().replace("[", "").replace("]", ""));
		System.out.println("Score:	" + player.getTotalScore());
		if (player.getTotalScore() == 21) {
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
				deck.dealTo(player);
				System.out.println("Hand:	" + player.getHand().toString().replace("[", "").replace("]", ""));
			}
			if (player.getTotalScore() == 21) {
				hitOrStay = "s";
				System.out.println("BLACKJACK!!!");
			}
			if (hitOrStay.equals("s")) {
				System.out.println("Player will stay with a score of " + player.getTotalScore());
			}
			if (player.getTotalScore() > 21) {
				System.out.println("Score:	"	+ player.getTotalScore() + " BUST!");
				hitOrStay = "s";
			}
			else {
				System.out.println("Score:	" + player.getTotalScore());
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
		if (dealer.getTotalScore() == player.getTotalScore()) {
			System.out.println("Push!!"); // Push
		}
		else if (dealer.getTotalScore() > 21 && player.getTotalScore() < 22) {
			System.out.println("Player wins!!");
			player.changeBalance(bet); // Player wins bet
		}
		else if (player.getTotalScore() < 22 && player.getTotalScore() > dealer.getTotalScore()) {
			System.out.println("Player wins!!");
			player.changeBalance(bet); // Player wins bet
		}
		else {
			System.out.println("Player loses!!");
			player.changeBalance(bet * -1); // Player loses bet
		}
		System.out.println();
		
		// Show player's updated balance
		System.out.println("Player current chip count: " + player.getBalance());
		
		// This is to show how many cards left in the deck
		System.out.print("Amount of cards left in the deck: ");
		System.out.println(deck.getCardsLeftCount());
		
		// Return cards and shuffle the deck 
		dealer.clearHand();
		player.clearHand();
		deck.shuffle();
		System.out.print("Amount of cards in new deck: ");
		System.out.println(deck.getCardsLeftCount());
	}

}
