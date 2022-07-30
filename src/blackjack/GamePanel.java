package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -4346803565314594840L;

	JPanel dealerHand;
	JPanel userHand;
	
	JLabel[] dealerCards;
	JLabel[] userCards;
	
	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(new Color(0, 102, 0));
		setLayout(new BorderLayout());
		
		dealerHand = new JPanel();
		dealerHand.setLayout(new FlowLayout(FlowLayout.CENTER, -110, 10));
		dealerHand.setOpaque(false);
		add(dealerHand, BorderLayout.NORTH);
			
			dealerCards = new JLabel[11];
			for (int i = 0; i < dealerCards.length; ++i) {
				dealerCards[i] = new JLabel();
				dealerCards[i].setVisible(false);
				dealerHand.add(dealerCards[i]);
			}
		
		userHand = new JPanel();
		userHand.setLayout(new FlowLayout(FlowLayout.CENTER, -110, 0));
		userHand.setOpaque(false);
		add(userHand, BorderLayout.SOUTH);

			userCards = new JLabel[11];
			for (int i = 0; i < userCards.length; ++i) {
				userCards[i] = new JLabel();
				userCards[i].setVisible(false);
				userHand.add(userCards[i]);
			}
	}

	public void placeCardsOnTable(Card dealerCard, List<Card> userCard) {
		dealerCards[0].setIcon(dealerCard.getFaceImage());
		dealerCards[1].setIcon(Card.getBackImage());
		
		
		for (int i = 0; i < userCard.size(); ++i) {
			userCards[i].setIcon(userCard.get(i).getFaceImage());
			
			dealerCards[i].setVisible(true);
			userCards[i].setVisible(true);
		}
		
		
	}
	
}
