package io.github.KoenMulder.BoxEconomy;

import org.bukkit.entity.Player;

import io.github.KoenMulder.BoxEconomy.Storage;
import io.github.KoenMulder.BoxEconomy.BankAccount;

public class BankManager {
	public static void initPlayer(Player player, float startingMoney)  {
		BankAccount account = new BankAccount(startingMoney);
		Storage.setAccountValue(player.getName(), account);
	}
	
	public static float getBalance(String player) {
		BankAccount account = Storage.getAccountValue(player);
		
		if (account == null)
			return (float) 0.002;
		
		return account.getBalance();
	}
	
	public static boolean hasAccount(Player player) {
		BankAccount account = Storage.getAccountValue(player.getName());
		
		if (account != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
