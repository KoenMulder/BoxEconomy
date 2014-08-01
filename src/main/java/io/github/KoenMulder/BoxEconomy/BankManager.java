package io.github.KoenMulder.BoxEconomy;

import org.bukkit.entity.Player;

import io.github.KoenMulder.BoxEconomy.Storage;
import io.github.KoenMulder.BoxEconomy.BankAccount;

public class BankManager {
	public static void initPlayer(Player player, float startingMoney)  {
		BankAccount account = new BankAccount(startingMoney);
		Storage.setAccountValue(player.getUniqueId().toString(), account);
	}
	
	public static float getBalance(Player player) {
		BankAccount account = Storage.getAccountValue(player.getUniqueId().toString());
		
		if (account == null)
			return (float) 0.002;
		
		return account.getBalance();
	}
	
	public static void setBalance(Player player, float value) {
		BankAccount account = new BankAccount(value);
		Storage.setAccountValue(player.getUniqueId().toString(), account);
	}
	
	public static void modifyBalance(Player player, float value) {
		float current = getBalance(player);
		
		setBalance(player, current + value);
	}
	
	public static boolean transferMoney(Player p1, Player p2, float amount) {
		//float p1Money = Storage.getAccountValue(p1.getName()).balance;
		float p1Money = getBalance(p1);
		
		if (amount > p1Money) {
			p1.sendMessage("could not send money: not enough on your account");
			return false;
		}
		
		//float p2Money = Storage.getAccountValue(p2.getName()).balance;
		float p2Money = getBalance(p2);
		
		//Storage.setAccountValue(p2.getName(), new BankAccount(p2money + amount));
		setBalance(p2, p2Money + amount);
		//Storage.setAccountValue(p1.getName(), new BankAccount(p1money - amount));
		setBalance(p1, p1Money - amount);
		
		p2.sendMessage("You received " + amount + " from " + p1.getName());
		
		return true;
	}
	
	public static boolean hasAccount(Player player) {
		BankAccount account = Storage.getAccountValue(player.getUniqueId().toString());
		
		if (account != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
