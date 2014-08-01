package io.github.KoenMulder.BoxEconomy;
 
import io.github.KoenMulder.BoxEconomy.Commands;


import org.bukkit.event.Listener;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin implements Listener {	
	@Override
	public void onDisable(){
		getLogger().info("BoxEconomy shuts down");
	}
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
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
		getLogger().info(event.getPlayer().getName()+ "joined nadjsjdnjajns");
		event.getPlayer().sendMessage("hihihihihi");
	}
}