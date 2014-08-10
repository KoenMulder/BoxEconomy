package io.github.KoenMulder.BoxEconomy;

import java.util.logging.Logger;

import io.github.KoenMulder.BoxEconomy.BankAccount;
import io.github.KoenMulder.BoxEconomy.BankManager;
import io.github.KoenMulder.BoxEconomy.TradeManager;
 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
 
public class Commands {
	public static void balance(Player player, String[] args) {
		boolean adminPerm = player.hasPermission("boxeconomy.admin");
		if (args.length > 0) {
			// balance <player>
			if (adminPerm && args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					player.sendMessage("player does not exist");
				}
				else {
					player.sendMessage(Float.toString(BankManager.getBalance(target)));
				}
			}
			// balance <player> add|remove <sum>
			else if (adminPerm && args.length == 3) {
				
				Player target = Bukkit.getPlayer(args[0]);
				float amount = Float.parseFloat(args[2]);
				
				if (args[1].equalsIgnoreCase("add")) {
					BankManager.modifyBalance(target, amount);
				}
				else if (args[1].equalsIgnoreCase("remove")) {
					BankManager.modifyBalance(target, -amount);
				}
				else {
					player.sendMessage("usage: /balance <name> add|remove <amount>");
				}
			}
			// Error
			else {
				if (adminPerm) {
					player.sendMessage("usage: /balance <name> add|remove <amount>");
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
	
	public static void pay(Player player, String[] args) {
			// pay <player> <sum>
			if (args.length == 2) {
				Player recipient = Bukkit.getPlayer(args[0]);
			
				if (recipient == null || !recipient.isOnline()) {
					player.sendMessage("player is not online");
				}
				else {
					float amount = Float.parseFloat(args[1]);
					BankManager.transferMoney(player, recipient, amount);
				}
			}
			// error
			else {
				player.sendMessage("usage: /pay <player> <amount>");
			}
	}
	
	public static void trade(Player player, String[] args) {
		// trade <player> <item> <amount> <money>
		if (args.length == 4) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target != null && target.isOnline()) {
				String item = args[1];
				int amount = Integer.parseInt(args[2]);
				float money = Float.parseFloat(args[3]);
			
				TradeManager.sendTradeRequest(player, target, item, amount, money);
			}
			else {
				player.sendMessage("player " + args[0] + " is not online");
			}
		}
		// trade <accept> <player>
		else if (args.length == 2) {
			//Logger.getLogger("Minecraft").info(args[1]);
			if (args[0].equalsIgnoreCase("accept")) {
				TradeManager.acceptTradeRequest(player, args[1]);
			}
			else if (args[0].equalsIgnoreCase("deny")) {
				TradeManager.denyTradeRequest(player, args[1]);
			}
			else {
				player.sendMessage("usage: /trade accept|deny <player>");
			}
		}
		// error
		else {
			player.sendMessage("usage: /trade <player> <item> <sum>\n" + "/trade accept|deny <player>");
		}
	}
}
