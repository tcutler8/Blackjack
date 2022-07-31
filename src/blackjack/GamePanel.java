package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -4346803565314594840L;
	
	private final int MAX_CARDS = 11;

	private JLayeredPane dealerHand;
	private JLayeredPane userHand;
	
	private JLabel[] dealerCards;
	private JLabel[] userCards;
	private JLabel   conclusion;
	
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
			
		conclusion = new JLabel();
		conclusion.setHorizontalAlignment(SwingConstants.CENTER);
		conclusion.setForeground(Color.WHITE);
		conclusion.setFont(new Font("Dialog", Font.PLAIN, 48));
		add(conclusion, BorderLayout.CENTER);
	}

	public void placeCardsOnTable(Card dealerCard, List<Card> userHand) {
		this.dealerCards[0].setIcon(dealerCard.getFaceImage());
		this.dealerCards[1].setIcon(Card.getBackImage());
		
		for (int i = 0; i < userHand.size(); ++i) {
			this.userCards[i].setIcon(userHand.get(i).getFaceImage());
			this.dealerCards[i].setVisible(true);
			this.userCards[i].setVisible(true);
		}
	}
	
	public void showDealerCards(List<Card> dealerHand) {
		for (int i = 0; i < dealerHand.size(); ++i) {
			this.dealerCards[i].setIcon(dealerHand.get(i).getFaceImage());
			this.dealerCards[i].setVisible(true);
		}
		repaint();
		System.out.println(dealerHand);
	}
	
	public void showUserCards(List<Card>  userHand) {
		for (int i = 0; i < userHand.size(); ++i) {
			this.userCards[i].setIcon(userHand.get(i).getFaceImage());
			this.userCards[i].setVisible(true);
		}
	}
	
	public void roundConclusion(String message) {
		conclusion.setText(message);
	}
	
	public void clearCards() {
		for (int i = 0; i < MAX_CARDS; ++i) {
			this.dealerCards[i].setVisible(false);
			this.userCards[i].setVisible(false);
		}
	}
	
}
