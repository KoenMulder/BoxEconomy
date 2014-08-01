package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;
import io.github.KoenMulder.BoxEconomy.BankManager;
import java.util.logging.Logger;
 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
 
public class Commands {
	public static void balance(Player player, String[] args) {
		boolean adminPerm = player.hasPermission("boxeconomy.admin");
		if (args.length > 0) {
			if (adminPerm && args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					player.sendMessage("player does not exist");
				}
				else {
					player.sendMessage(Float.toString(BankManager.getBalance(target)));
				}
			}
			else if (adminPerm && args.length == 3) {
				Logger.getLogger("Minecraft").info("3 args");
				//Logger.getLogger("Minecraft").info(args[1]);
				
				Player target = Bukkit.getPlayer(args[0]);
				float amount = Float.parseFloat(args[2]);
				
				if (args[1].equalsIgnoreCase("add")) {
					Logger.getLogger("Minecraft").info("add");
					BankManager.modifyBalance(target, amount);
				}
				else if (args[1].equalsIgnoreCase("remove")) {
					Logger.getLogger("Minecraft").info("remove");
					BankManager.modifyBalance(target, -amount);
				}
				else {
					player.sendMessage("usage: /balance [[name] add|remove [amount]]");
				}
			}
			else {
				if (adminPerm) {
					player.sendMessage("usage: /balance [name add|remove amount]");
				}
				else {
					player.sendMessage("usage: /balance");
				}
			}
		}
		else {
			player.sendMessage(Float.toString(BankManager.getBalance(player)));
		}
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