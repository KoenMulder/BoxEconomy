package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;
import io.github.KoenMulder.BoxEconomy.BankManager;

 
import org.bukkit.entity.Player;
 
public class Commands {
	public static void balance(Player player, String[] args) {
		player.sendMessage(Float.toString(BankManager.getBalance(player.getName())));
	}
	
	public static void pay(Player player, String[] args) {
		
	}
}