package io.github.KoenMulder.BoxEconomy;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.KoenMulder.BoxEconomy.Storage;
import io.github.KoenMulder.BoxEconomy.BankAccount;

public class BankManager {
	public static void initPlayer(Player player)  {
		BankAccount account = new BankAccount((float) 100);
		Storage.setValue(player.getName(), account);
	}
	
	public static float getBalance(String player) {
		BankAccount account = Storage.getValue(player);
		
		if (account == null)
			return (float) 0.002;
		
		return account.getBalance();
	}
	
	public static boolean hasAccount(Player player) {
		BankAccount account = Storage.getValue(player.getName());
		
		if (account != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
