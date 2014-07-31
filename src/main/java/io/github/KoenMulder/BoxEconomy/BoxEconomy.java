package io.github.KoenMulder.BoxEconomy;
 
import io.github.KoenMulder.BoxEconomy.BoxCommands;
 
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
 
public class BoxEconomy extends JavaPlugin{	
	@Override
	public void onDisable(){
		getLogger().info("BoxEconomy shuts down");
	}
	@Override
	public void onEnable(){
		getLogger().info("Boxeconomy started");
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		String cmdName = cmd.getName().toLowerCase();
		Player player = (Player) sender;
		
		switch(cmdName) {
		case "balance":
			BoxCommands.balance(player, args);
			break;
		case "pay":
			BoxCommands.pay(player, args);
			break;
		}
		return true;
	}
}