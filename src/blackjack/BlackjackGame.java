package blackjack;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

	private final static int INITIAL_BALANCE = 500;
	public final static User user = new User(INITIAL_BALANCE);
	private static Dealer dealer = new Dealer();
	private static DeckHandler deck = new DeckHandler();

	private static BlackjackGame frame;
	
	private static GamePanel gamePanel;
	private static ActionPanel actionPanel;
	
	private static int playerWins = 0;
	private static int playerLosses = 0;
	private static int playerPushes = 0;
	private static int bet = 0;
	
	private static File dir, file;
	
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
	
	/**
	 * Creates the game
	 */
	public BlackjackGame() {
		dir = new File("src/blackjack/textfiles/");
		dir.mkdir();
		file = new File("src/blackjack/textfiles/PlayerBalance.txt");
		
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
	
	/**
	 * Deals the cards and is initialized when user selects "Place Bet" button
	 * @param bet amount
	 */
	public static void startGame(int bet) {
		BlackjackGame.bet = bet;
		user.changeBalance(-bet);
		
		dealCards();
		gamePanel.placeCardsOnTable(dealer.getFirstCard(), user.getHand());
	}
	
	/**
	 * This removes all content on the GamePanel. Used when the player wants to play 
	 * again. The betting panel will be displayed and the GamePanel is cleared. Also
	 * will shuffle cards if there are 20 cards or less
	 */
	public static void roundReset() {
		gamePanel.clearCards();
		gamePanel.roundConclusion("\nStatistics:\n\n" + getPlayerResults()
			+ ((deckCheck()) ? "\nReshuffling..." : ""));
		
		dealer.clearHand();
		user.clearHand();
		
		actionPanel.resetBetForeground();

		if (user.getBalance() < 10)
			gameReset();
	}
	
	public static void gameReset() {
		gamePanel.roundConclusion("GAMEOVER\nFinal Statistics:\n\n" + getPlayerResults() + "\nResetting...");
		playerWins = playerLosses = playerPushes = 0;
		playerResults();
	
		user.setBalance(INITIAL_BALANCE);
		deck.shuffle();
	}

	/**
	 * @return amount of chips player has
	 */
	public static int getChips() {return user.getBalance();}
	
	/**
	 * Deals the cards to the player and the dealer. Will check to see
	 * if player hit 21. If so, the user wins 2.5x their bet and the game
	 * is over
	 */
	private static void dealCards() {
		final int CARDS_TO_DEAL = 2;
		gamePanel.roundConclusion(null);
		for (int i = 0; i < CARDS_TO_DEAL; ++i) {
			deck.dealTo(user);
			deck.dealTo(dealer);
		}
		
		if (user.getTotalScore() == 21) {
			int winnings = (int) (bet * 2.5);
			gamePanel.showDealerCards(dealer.getHand());
			gamePanel.roundConclusion("\nBlackjack!\n+$" + winnings);
			actionPanel.continueOrQuit();
			user.changeBalance(winnings);
			playerWins = playerWins+ 1;
		}
		
	}
	
	/**
	 * Gives the player another card. Program will display card face up. If the
	 * player's total is above 21, the stay method is called. If player's total
	 * is 21, the game is over and the player wins
	 */
	public static void hit() {
		deck.dealTo(user);
		gamePanel.showUserCards(user.getHand());
		
		if (user.getTotalScore() >= 21)
			stay();
	}
	
	/**
	 * Tests to see if the player has won. Displays a message of the results and adjusts
	 * the player's winnings depending on the outcome of the game. Also will call the
	 * method to ask the player if they want to play again
	 */
	public static void stay() {
		dealersTurn();
		gamePanel.showDealerCards(dealer.getHand());
		
		int winnings = bet; // assumes push
		
		if (dealer.getTotalScore() > 21) {
			if (user.getTotalScore() <= 21) {
				winnings = bet * 2;
				gamePanel.roundConclusion("\nDealer busted.\n+$" + winnings);
				playerWins = playerWins+ 1;
			} else {
				gamePanel.roundConclusion("\nYou and dealer busted.\n+$" + winnings);
				playerLosses = playerLosses + 1;
			}
			
			user.changeBalance(winnings);
		} else if (user.getTotalScore() > 21) {
			gamePanel.roundConclusion("\nYou busted.");
			playerLosses = playerLosses + 1;
		} else if (user.getTotalScore() > dealer.getTotalScore()) {
			winnings = bet * 2;
			user.changeBalance(winnings);
			gamePanel.roundConclusion("\nYou win!\n+$" + winnings);
			playerWins = playerWins+ 1;
		} else if (user.getTotalScore() < dealer.getTotalScore()) {
			gamePanel.roundConclusion("\nYou Lose.");
			playerLosses = playerLosses + 1;
		} else {
			user.changeBalance(winnings);
			gamePanel.roundConclusion("\nPush. +$" + winnings);
			playerPushes = playerPushes + 1;
		}
		
		playerResults();
		actionPanel.continueOrQuit();
	}
	
	/**
	 * Continues to give the dealer another card as long as dealer total is below 17.
	 * Method is initialized after the player stays
	 */
	public static void dealersTurn() {
		while(dealer.shouldHit())
			deck.dealTo(dealer);
	}
	
	/**
	 * Checks to see if the deck has 20 cards or less remaining. If it does, the deck
	 * is shuffled and reset so that dealer does not run out of cards
	 * @return true if shuffled, false if left alone
	 */
	public static boolean deckCheck() {
		if (deck.getCardsLeftCount() <= 20) {
			deck.shuffle();
			return true;
		}
		return false;
	}
	
	/**
	 * Writes updated results to a file. Displays the player's balance
	 * and amount of wins, losses, and pushes
	 */
	public static void playerResults() {
		try (PrintWriter writer = new PrintWriter(file)) { 
			writer.printf("%-16s: $%d%n", "Player's Balance", getChips());
			writer.printf("%-16s: %d%n",  "Total Wins", 	     playerWins);
			writer.printf("%-16s: %d%n",  "Total Losses",	 playerLosses);
			writer.printf("%-16s: %d",    "Total Pushes", 	 playerPushes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(getPlayerResults());
		
		
	}
	
	/**
	 * Reads from the PlayerBalance text file and returns everything.
	 * This will be displayed for user after they play
	 * @return file contents
	 */
	public static String getPlayerResults() {
		String results = "";
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine())
				results += reader.nextLine() + "\n";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}

	//
	// CONSOLE TEST CLIENT
	//
	/*
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
	*/

}
