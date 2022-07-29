package blackjack;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class ActionPanel extends JPanel {

	JLabel lblBetAmount;
	private int bet = 0;
	
	/**
	 * Create the panel.
	 */
	public ActionPanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(0, 0, 0));
		
		createBetPlacementPanel();
	}
	
	public void createBetPlacementPanel() {
		JPanel panelBetPlacement = new JPanel();
		panelBetPlacement.setOpaque(false);
		panelBetPlacement.setLayout(new GridLayout(1, 2, 0, 0));
		add(panelBetPlacement);
		
			JPanel panelSetBet = new JPanel();
			panelSetBet.setBorder(new EmptyBorder(10, 50, 20, 50));
			panelSetBet.setOpaque(false);
			panelSetBet.setLayout(new BorderLayout(0, 5));
			panelBetPlacement.add(panelSetBet);
			
				lblBetAmount = new JLabel();
				updateBetAmount();
				lblBetAmount.setFont(new Font("Dialog", Font.BOLD, 16));
				lblBetAmount.setForeground(Color.WHITE);
				lblBetAmount.setHorizontalAlignment(SwingConstants.CENTER);
				panelSetBet.add(lblBetAmount, BorderLayout.NORTH);
				
				JPanel panelBtnsIncrementBet = new JPanel();
				panelBtnsIncrementBet.setOpaque(false);
				panelBtnsIncrementBet.setLayout(new GridLayout(0, 5, 5, 0));
				panelSetBet.add(panelBtnsIncrementBet, BorderLayout.CENTER);
				
					JButton btnPlus1 = createButton("+$1");
					btnChangeBetActionListener(btnPlus1, 1);
					panelBtnsIncrementBet.add(btnPlus1);
					
					JButton btnPlus5 = createButton("+$5");
					btnChangeBetActionListener(btnPlus5, 5);
					panelBtnsIncrementBet.add(btnPlus5);
					
					JButton btnPlus10 = createButton("+$10");
					btnChangeBetActionListener(btnPlus10, 10);
					panelBtnsIncrementBet.add(btnPlus10);
					
					JButton btnPlus25 = createButton("+$25");
					btnChangeBetActionListener(btnPlus25, 25);
					panelBtnsIncrementBet.add(btnPlus25);
					
					JButton btnPlus100 = createButton("+$100");
					btnChangeBetActionListener(btnPlus100, 100);
					panelBtnsIncrementBet.add(btnPlus100);
					
				JButton btnResetBet = createButton("Reset Bet");
				btnChangeBetActionListener(btnResetBet, 0);
				panelSetBet.add(btnResetBet, BorderLayout.SOUTH);
					
			JPanel panelPlaceBet = new JPanel();
			panelPlaceBet.setOpaque(false);
			panelPlaceBet.setBorder(new EmptyBorder(40, 90, 40, 90));
			panelPlaceBet.setLayout(new BorderLayout(0, 0));
			panelBetPlacement.add(panelPlaceBet);
			
				JButton btnPlaceBet = createButton("Place Bet");
				btnPlaceBet.setFont(new Font("Dialog", Font.BOLD, 20));
				btnPlaceBet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BlackjackGame.setBet(bet);
						panelBetPlacement.setVisible(false);
					}
				});
				panelPlaceBet.add(btnPlaceBet, BorderLayout.CENTER);
	}
	
	private JButton createButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setBorder(new BevelBorder(BevelBorder.RAISED, 
				Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.DARK_GRAY, Color.DARK_GRAY));
		button.setForeground(Color.BLACK);
		button.setBackground(Color.GRAY);
		button.setFocusPainted(false);
		return button;
	}
	
	private void updateBetAmount() {lblBetAmount.setText("Bet: $" + bet);}
	
	private void btnChangeBetActionListener(JButton button, int betIncrement) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (betIncrement == 0)
					bet = 0;
				bet += betIncrement;
				updateBetAmount();
			}
		});
	}

}
