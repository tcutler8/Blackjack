package blackjack;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel gamePanel = new GamePanel();
		contentPane.add(gamePanel, BorderLayout.CENTER);
		
		JPanel actionPanel = new ActionPanel();
		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}

}
