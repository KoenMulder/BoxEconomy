package io.github.KoenMulder.BoxEconomy;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import io.github.KoenMulder.BoxEconomy.TradeRequest;

public class TradeManager {
	public static void sendTradeRequest(Player player, Player target, String item, int amount, float money) {
		TradeRequest tr = new TradeRequest();
		tr.playerId = player.getUniqueId().toString();
		tr.targetId = target.getUniqueId().toString();
		tr.item = item;
		tr.amount = amount;
		tr.money = money;
		
		Inventory inv = player.getInventory();
		Material material = Material.getMaterial(item.toUpperCase());
		if (material != null) {
			if (inv.contains(material, amount)) {
				Storage.addTradeRequest(tr);
				player.sendMessage(ChatColor.DARK_AQUA + "sent trade request to " + target.getName());
				target.sendMessage(ChatColor.DARK_AQUA + "trade request from " + player.getName() + ": " + amount + " " + item + " for " + money);
			}
			else {
				player.sendMessage(ChatColor.DARK_AQUA + "you do not have enough " + item);
			}
		}
		else {
			player.sendMessage(ChatColor.DARK_AQUA + "material does not exist");
		}
	}
	
	public static void acceptTradeRequest(Player player, String partner) {
		Player target = Bukkit.getPlayer(partner.toLowerCase());
		
		if (target != null && target.isOnline()) {
			TradeRequest tr = Storage.getTradeRequest("target", player.getUniqueId().toString());
			float balance = BankManager.getBalance(player);
			
			if (tr != null) {
			
				if (balance > tr.money) {
					ItemStack items = new ItemStack(Material.getMaterial(tr.item.toUpperCase()), tr.amount);
					
					player.getInventory().addItem(items);
					target.getInventory().remove(items);
				
					BankManager.modifyBalance(player, -tr.money);
					BankManager.modifyBalance(target, tr.money);
				
					player.sendMessage(ChatColor.DARK_AQUA + "trade successful: obtained " + tr.amount + " " + tr.item + " for " + tr.money);
					target.sendMessage(ChatColor.DARK_AQUA + "trade successful: sold " + tr.amount + " " + tr.item + " for " + tr.money);
				
					Storage.removeTradeRequest("target", player.getUniqueId().toString());
				}
				else {
					player.sendMessage(ChatColor.DARK_AQUA + "not enough money");
					target.sendMessage(ChatColor.DARK_AQUA + player.getName() + " declined");
					Storage.removeTradeRequest("target", player.getUniqueId().toString());
				}
			}
			else {
				player.sendMessage(ChatColor.DARK_AQUA + "there is no trade request");
			}
		}
		else {
			player.sendMessage(ChatColor.DARK_AQUA + "could not accept trade request from player: does not exist");
		}
	}
	
	public static void denyTradeRequest(Player player, String partner) {
		Player target = Bukkit.getPlayer(partner.toLowerCase());
		TradeRequest tr = Storage.getTradeRequest("target", player.getUniqueId().toString());
		if (tr != null) {
			if (target != null && target.isOnline()) {
				Storage.removeTradeRequest("target", player.getUniqueId().toString());
				player.sendMessage(ChatColor.DARK_AQUA + "denied trade with " + partner);
				target.sendMessage(ChatColor.DARK_AQUA + player.getName() + " denied your trade request");
			}
		}
		else {
			player.sendMessage(ChatColor.DARK_AQUA + "there is no trade request");
		}
	}
}
