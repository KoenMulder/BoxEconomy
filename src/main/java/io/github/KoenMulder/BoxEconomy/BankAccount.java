package io.github.KoenMulder.BoxEconomy;

public class BankAccount {
	public float balance;
	
	BankAccount(float balance) {
		this.balance = balance;
	}
	
	public float getBalance() {
		return this.balance;
	}
}