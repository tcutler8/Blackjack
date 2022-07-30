package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = 2137955758829872303L;
	
	JPanel panelBetPlacement;
	JPanel panelHitOrStay;
	
	private JLabel lblBetAmount;
	private int bet = 0;
	//private BlackjackGame playerChips = new BlackjackGame();
	private int maxBet = BlackjackGame.getChips(); 
	// 
	
	/**
	 * Create the panel.
	 */
	public ActionPanel() {
		//setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 0, 0));
		setBackground(Color.BLACK);
		
		createBetPlacementPanel();
		panelBetPlacement.setVisible(true);
		add(panelBetPlacement);
		
		createHitOrStayPanel();
		panelHitOrStay.setVisible(false);
		add(panelHitOrStay);
	}

	//
	// PANEL CREATION METHODS
	//
	
	public void createBetPlacementPanel() {
		panelBetPlacement = new JPanel();
		panelBetPlacement.setVisible(false);
		panelBetPlacement.setOpaque(false);
		panelBetPlacement.setLayout(new GridLayout(0, 2, 0, 0));
		
			JPanel panelSetBet = new JPanel();
			panelSetBet.setLayout(new BorderLayout(0, 5));
			panelSetBet.setBorder(new EmptyBorder(0, 0, 0, 90));
			panelSetBet.setOpaque(false);
			panelBetPlacement.add(panelSetBet);
			
				lblBetAmount = new JLabel();
				lblBetAmount.setFont(new Font("Dialog", Font.BOLD, 16));
				lblBetAmount.setForeground(Color.WHITE);
				lblBetAmount.setHorizontalAlignment(SwingConstants.CENTER);
				updateBetAmount();
				panelSetBet.add(lblBetAmount, BorderLayout.NORTH);
				
				JPanel panelBtnsIncrementBet = new JPanel();
				panelBtnsIncrementBet.setOpaque(false);
				panelBtnsIncrementBet.setLayout(new GridLayout(0, 5, 5, 0));
				panelSetBet.add(panelBtnsIncrementBet, BorderLayout.CENTER);
				
					JButton btnPlus1 = new BlackjackButton("+$1");
					btnChangeBetActionListener(btnPlus1, 1);
					panelBtnsIncrementBet.add(btnPlus1);
					
					JButton btnPlus5 = new BlackjackButton("+$5");
					btnChangeBetActionListener(btnPlus5, 5);
					panelBtnsIncrementBet.add(btnPlus5);
					
					JButton btnPlus10 = new BlackjackButton("+$10");
					btnChangeBetActionListener(btnPlus10, 10);
					panelBtnsIncrementBet.add(btnPlus10);
					
					JButton btnPlus25 = new BlackjackButton("+$25");
					btnChangeBetActionListener(btnPlus25, 25);
					panelBtnsIncrementBet.add(btnPlus25);
					
					JButton btnPlus100 = new BlackjackButton("+$100");
					btnChangeBetActionListener(btnPlus100, 100);
					panelBtnsIncrementBet.add(btnPlus100);
					
				JButton btnResetBet = new BlackjackButton("Reset Bet");
				btnChangeBetActionListener(btnResetBet, 0);
				panelSetBet.add(btnResetBet, BorderLayout.SOUTH);
					
			JPanel panelPlaceBet = new JPanel();
			panelPlaceBet.setOpaque(false);
			panelPlaceBet.setBorder(new EmptyBorder(0, 90, 0, 0));
			panelPlaceBet.setLayout(new BorderLayout());
			panelBetPlacement.add(panelPlaceBet);
			
			JLabel lblChipCount = new JLabel();
			lblChipCount.setText("Starting Chip Count: $" + maxBet);
			lblChipCount.setHorizontalAlignment(SwingConstants.CENTER);
			lblChipCount.setForeground(Color.WHITE);
			lblChipCount.setFont(new Font("Dialog", Font.BOLD, 16));
			panelPlaceBet.add(lblChipCount, BorderLayout.NORTH);
			
				JButton btnPlaceBet = new BlackjackButton("Place Bet");
				btnPlaceBet.setFont(new Font("Dialog", Font.PLAIN, 20));
				btnPlaceBet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BlackjackGame.startGame(bet);
						panelBetPlacement.setVisible(false);
						remove(panelBetPlacement);
						
						panelHitOrStay.setVisible(true);
						add(panelHitOrStay);
						
						repaint();
						
					}
				});
				panelPlaceBet.add(btnPlaceBet, BorderLayout.CENTER);
	}
	
	public void createHitOrStayPanel() {
		panelHitOrStay = new JPanel();
		panelHitOrStay.setBorder(new EmptyBorder(30, 0, 0, 0));
		panelHitOrStay.setVisible(false);
		panelHitOrStay.setLayout(new GridLayout(0, 2, 15, 0));
		panelHitOrStay.setOpaque(false);
		
			JButton btnHit = new BlackjackButton("HIT");
			btnHit.setFont(new Font("Dialog", Font.PLAIN, 24));
			btnHit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			panelHitOrStay.add(btnHit);
			
			JButton btnStay = new BlackjackButton("STAY");
			btnStay.setFont(new Font("Dialog", Font.PLAIN, 24));
			btnHit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			panelHitOrStay.add(btnStay);
		
	}
	
	//
	// OTHER METHODS
	//
	
	private void updateBetAmount() {lblBetAmount.setText("Bet: $" + bet);}
	
	private void betTooHigh() {
		lblBetAmount.setText("Bet: $" + bet + " (MAX BET)  ");
		lblBetAmount.setForeground(Color.RED);
	}
	
	private void btnChangeBetActionListener(JButton button, int betIncrement) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (betIncrement == 0)
					bet = 0;
				if ((bet + betIncrement) >= maxBet) {
					bet = 500;
					betTooHigh();
				}
				else {
					bet += betIncrement;
					updateBetAmount();
				}
			}
		});
	}
}
