package blackjack;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class BlackjackButton extends JButton {

	/**
	 * Create the button.
	 * @param buttonText text to initialize the button with
	 */
	public BlackjackButton(String buttonText) {
		super(buttonText);
		setBorder(BorderFactory.createCompoundBorder(
				new BevelBorder(BevelBorder.RAISED, 
						Color.LIGHT_GRAY, Color.LIGHT_GRAY,
						Color.DARK_GRAY,  Color.DARK_GRAY),
				new EmptyBorder(3, 3, 3, 3)));
		setForeground(Color.BLACK);
		setBackground(Color.GRAY);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
	}
	
}