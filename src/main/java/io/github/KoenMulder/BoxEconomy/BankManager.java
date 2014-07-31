package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.Storage;
import io.github.KoenMulder.BoxEconomy.BankAccount;

public class BankManager {
	public static void initPlayer()  {
		BankAccount account = new BankAccount();
		account.balance = (float) 100;
	}
	public static float getBalance(String player) {
		BankAccount account = Storage.getValue(player);
		
		return account.balance;
	}
}
