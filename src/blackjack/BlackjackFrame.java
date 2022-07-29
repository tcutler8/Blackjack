package blackjack;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BlackjackFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BlackjackFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 720);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1000};
		gbl_contentPane.rowHeights = new int[]{620, 100};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel gamePanel = new GamePanel();
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		contentPane.add(gamePanel, gbc_gamePanel);
		
		JPanel actionPanel = new ActionPanel();
		GridBagConstraints gbc_actionPanel = new GridBagConstraints();
		gbc_actionPanel.fill = GridBagConstraints.BOTH;
		gbc_actionPanel.gridy = 1;
		contentPane.add(actionPanel, gbc_actionPanel);
	}

}
