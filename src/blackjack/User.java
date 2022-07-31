package blackjack;

/**
 * Represents the user (a player) in blackjack.
 * 
 * @author tayson
 */
public class User extends Player {

	private int balance;
	
	/**
	 * Initialize the user's balance.
	 * @param balance
	 */
	public User(int balance) {setBalance(balance);}
	
	/**
	 * @return user's balance
	 */
	public int getBalance() {return balance;}
	
	public void setBalance(int balance) {this.balance = balance;}
	
	/**
	 * Change the user's balance by <code>addend</code> 
	 * amount. Can be a negative number.
	 * @param addend
	 */
	public void changeBalance(int addend) {balance += addend;}
	
}
