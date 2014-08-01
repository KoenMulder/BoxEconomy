package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;
import io.github.KoenMulder.BoxEconomy.BankManager;

 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
 
public class Commands {
	public static void balance(Player player, String[] args) {
		player.sendMessage(Float.toString(BankManager.getBalance(player)));
	}
	
	public static boolean pay(Player player, String[] args) {
		if (args.length < 1) {
			player.sendMessage("usage: /pay [player] [amount]");
			return false;
		}
		
		Player recipient = Bukkit.getPlayer(args[0]);
		
		if (recipient == null || !recipient.isOnline()) {
			player.sendMessage("player is not online");
			return false;
		}
		else {
			float amount = Float.parseFloat(args[1]);
		
			BankManager.transferMoney(player, recipient, amount);
			
			return true;
		}
	}
}