package io.github.KoenMulder.BoxEconomy;
 
import java.io.File;
import java.sql.SQLException;

import io.github.KoenMulder.BoxEconomy.Commands;
import io.github.KoenMulder.BoxEconomy.BankManager;
import io.github.KoenMulder.BoxEconomy.Storage;


import org.bukkit.event.Listener;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin implements Listener {	
	@Override
	public void onDisable() {
		getLogger().info("BoxEconomy shuts down");
	}
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		
		File dataDir = new File(getDataFolder() + "/");
		if(!dataDir.exists())
		   dataDir.mkdir();
		
		try {
			Storage.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getLogger().info("Boxeconomy started");
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		String cmdName = cmd.getName().toLowerCase();
		Player player = (Player) sender;
		
		switch(cmdName) {
		case "balance":
			Commands.balance(player, args);
			break;
		case "pay":
			Commands.pay(player, args);
			break;
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!BankManager.hasAccount(player)) {
			getLogger().info("Initializing bank account for " + event.getPlayer());
			BankManager.initPlayer(player, Float.parseFloat(this.getConfig().getString("default_money")));
		}
	}
}