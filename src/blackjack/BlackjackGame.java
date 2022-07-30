package blackjack;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Game of BlackJack between the dealer (computer) and the user. 
 * 
 * @author mark + tayson
 */
public class BlackjackGame extends  JFrame {
	
	private static final long serialVersionUID = -7888782584683513688L;
	
	public final static User user = new User(500);
	private static Dealer dealer = new Dealer();
	private static DeckHandler deck = new DeckHandler();
	private static int bet = 0;

	private static BlackjackGame frame;
	
	private static GamePanel gamePanel;
	private static ActionPanel actionPanel;
	
	//
	// SET UP FRAME
	//
	
	public static void main(String[] args) {
		System.setProperty("awt.useSystemAAFontSettings","on"); // set anti-aliasing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new BlackjackGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BlackjackGame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 780);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1000};
		gbl_contentPane.rowHeights = new int[]{660, 120};
		contentPane.setLayout(gbl_contentPane);
		
		gamePanel = new GamePanel();
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		contentPane.add(gamePanel, gbc_gamePanel);
		
		actionPanel = new ActionPanel();
		GridBagConstraints gbc_actionPanel = new GridBagConstraints();
		gbc_actionPanel.fill = GridBagConstraints.BOTH;
		gbc_actionPanel.gridy = 1;
		contentPane.add(actionPanel, gbc_actionPanel);
	}
	
	//
	// GAME LOGIC
	//
	
	public static void startGame(int bet) {
		BlackjackGame.bet = bet;
		dealCards();
		gamePanel.placeCardsOnTable(dealer.getFirstCard(), user.getHand());
	}

	public static int getChips() {return user.getBalance();}
	
	private static void dealCards() {
		final int CARDS_TO_DEAL = 2;
		for (int i = 0; i < CARDS_TO_DEAL; ++i) {
			deck.dealTo(user);
			deck.dealTo(dealer);
		}
	}

	//
	// CONSOLE TEST CLIENT
	//
	
	@SuppressWarnings("unused")
	private static void consoleTestClient() {
		Scanner     scan 	 = new Scanner(System.in);
		String      hitOrStay = "h";
		
		// Player1 starting chip count
		System.out.println("Player starting chip count: " + user.getBalance());
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
		deck.dealTo(user);
		deck.dealTo(user);
		System.out.println("Hand:	" + user.getHand().toString().replace("[", "").replace("]", ""));
		System.out.println("Score:	" + user.getTotalScore());
		if (user.getTotalScore() == 21) {
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
				deck.dealTo(user);
				System.out.println("Hand:	" + user.getHand().toString().replace("[", "").replace("]", ""));
			}
			if (user.getTotalScore() == 21) {
				hitOrStay = "s";
				System.out.println("BLACKJACK!!!");
			}
			if (hitOrStay.equals("s")) {
				System.out.println("Player will stay with a score of " + user.getTotalScore());
			}
			if (user.getTotalScore() > 21) {
				System.out.println("Score:	"	+ user.getTotalScore() + " BUST!");
				hitOrStay = "s";
			}
			else {
				System.out.println("Score:	" + user.getTotalScore());
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
		if (dealer.getTotalScore() == user.getTotalScore()) {
			System.out.println("Push!!"); // Push
		}
		else if (dealer.getTotalScore() > 21 && user.getTotalScore() < 22) {
			System.out.println("Player wins!!");
			user.changeBalance(bet); // Player wins bet
		}
		else if (user.getTotalScore() < 22 && user.getTotalScore() > dealer.getTotalScore()) {
			System.out.println("Player wins!!");
			user.changeBalance(bet); // Player wins bet
		}
		else {
			System.out.println("Player loses!!");
			user.changeBalance(bet * -1); // Player loses bet
		}
		System.out.println();
		
		// Show player's updated balance
		System.out.println("Player current chip count: " + user.getBalance());
		
		// This is to show how many cards left in the deck
		System.out.print("Amount of cards left in the deck: ");
		System.out.println(deck.getCardsLeftCount());
		
		// Return cards and shuffle the deck 
		dealer.clearHand();
		user.clearHand();
		deck.shuffle();
		System.out.print("Amount of cards in new deck: ");
		System.out.println(deck.getCardsLeftCount());
	}

}
