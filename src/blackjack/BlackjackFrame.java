package blackjack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlackjackFrame extends JFrame {

	private static final long serialVersionUID = -6294229596642599442L;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BlackjackFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 780);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1000};
		gbl_contentPane.rowHeights = new int[]{660, 120};
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
