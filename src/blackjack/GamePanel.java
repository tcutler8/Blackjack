package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -4346803565314594840L;
	
	private final int MAX_CARDS = 11;

	private JLayeredPane dealerHand;
	private JLayeredPane userHand;
	
	private JLabel[] dealerCards;
	private JLabel[] userCards;
	private JTextPane   conclusion;

	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(new Color(0, 102, 0));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 0, 15, 0));
		
		dealerHand = new JLayeredPane();
		dealerHand.setLayout(new FlowLayout(FlowLayout.CENTER, -179, 0));
		dealerHand.setOpaque(false);
		add(dealerHand, BorderLayout.NORTH);
			
			dealerCards = new JLabel[MAX_CARDS];
			for (int i = 0; i < dealerCards.length; ++i) {
				dealerCards[i] = new JLabel();
				dealerCards[i].setVisible(false);
				dealerHand.add(dealerCards[i]);
				dealerHand.moveToFront(dealerCards[i]);
			}
		
		userHand = new JLayeredPane();
		userHand.setLayout(new FlowLayout(FlowLayout.CENTER, -179, 0));
		userHand.setOpaque(false);
		add(userHand, BorderLayout.SOUTH);

			userCards = new JLabel[MAX_CARDS];
			for (int i = 0; i < userCards.length; ++i) {
				userCards[i] = new JLabel();
				userCards[i].setVisible(false);
				userHand.add(userCards[i]);
				userHand.moveToFront(userCards[i]);
			}
			
		conclusion = new JTextPane();
		conclusion.setBorder(null);
		conclusion.setAutoscrolls(false);
		conclusion.setEditable(false);
		conclusion.setBackground(new Color(0, 102, 0));
//		conclusion.setHorizontalAlignment(SwingConstants.CENTER);
		conclusion.setForeground(Color.WHITE);
		conclusion.setFont(new Font("Dialog", Font.PLAIN, 48));
		StyledDocument doc = conclusion.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		add(conclusion, BorderLayout.CENTER);

	}

	/**
	 * Deals 2 cards to player and dealer when the game starts. Dealer has one
	 * card face down and one card face out
	 * @param dealer's cards
	 * @param player's cards
	 */
	public void placeCardsOnTable(Card dealerCard, List<Card> userHand) {
		this.dealerCards[0].setIcon(dealerCard.getFaceImage());
		this.dealerCards[1].setIcon(Card.getBackImage());
		
		for (int i = 0; i < userHand.size(); ++i) {
			this.userCards[i].setIcon(userHand.get(i).getFaceImage());
			this.dealerCards[i].setVisible(true);
			this.userCards[i].setVisible(true);
		}
	}
	
	/**
	 * Shows both of dealer's cards. This is used after dealer has dealt remaining 
	 * cards and the player has stayed, busted, or hit 21
	 * @param dealerHand
	 */
	public void showDealerCards(List<Card> dealerHand) {
		for (int i = 0; i < dealerHand.size(); ++i) {
			this.dealerCards[i].setIcon(dealerHand.get(i).getFaceImage());
			this.dealerCards[i].setVisible(true);
		}
	}
	
	/**
	 * Shows both of player's cards
	 * @param Player's card (List)
	 */
	public void showUserCards(List<Card>  userHand) {
		for (int i = 0; i < userHand.size(); ++i) {
			this.userCards[i].setIcon(userHand.get(i).getFaceImage());
			this.userCards[i].setVisible(true);
		}
	}
	
	/**
	 * Will display a message in the middle of the screen. This is used to inform
	 * the player if they won or lost. Message is passed to method as a parameter
	 * @param message string
	 */
	public void roundConclusion(String message) {
		conclusion.setText(message);
	}
	
	/**
	 * Hides the cards. Used if the player wants to play again and the betting panel
	 * is displayed
	 */
	public void clearCards() {
		for (int i = 0; i < MAX_CARDS; ++i) {
			this.dealerCards[i].setVisible(false);
			this.userCards[i].setVisible(false);
		}
	}
	
}
