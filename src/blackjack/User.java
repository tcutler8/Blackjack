package blackjack;

public class User extends Player {

	private int balance;
	
	public User(int balance) {this.balance = balance;}
	
	public int getBalance() {return balance;}
	
	public void addToBalance(int addend) {balance += addend;}
	
}
