package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Shotdown extends JavaPlugin {
 
public void onEnable()
{
	Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
}
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	
    Player p = (Player) sender;
	
    if (!(sender instanceof Player)) { //stop command use in console
            sender.sendMessage(ChatColor.RED + "This plugin is for players only!");
            return true;
    }
    if (cmd.getName().equalsIgnoreCase("shotdown")) {
    	if (args.length > 1) {
    	    if (args[0].equalsIgnoreCase("arena")) {
    	 
    	        if (args[1].equalsIgnoreCase("setspawn")) {
    				getConfig().set("arena.world", p.getLocation().getWorld().getName());
    				getConfig().set("arena.x", p.getLocation().getX());
    				getConfig().set("arena.y", p.getLocation().getY());
    				getConfig().set("arena.z", p.getLocation().getZ());
    				saveConfig();
    				p.sendMessage(ChatColor.GREEN + "Shotdown Arena Spawn Set!");
    				return true;
    	        }
    	 
    	        if ((args[1].equalsIgnoreCase("del")) || (args[1].equalsIgnoreCase("dell"))) {
    	            sender.sendMessage("tool del ok");
    	            //TODO: Tool Del Code
    	            return true;
    	        }
    	 
    	        //If the player dont add the "add or del", or ask for help with the "?" or "help" show this:
    	        if ((args[1] == null) || (args.length >= 1) || ((args[1].equalsIgnoreCase("?")) || (args[1].equalsIgnoreCase("help")))) {
    	        	p.sendMessage("/sd arena setspawn - Set the arena spawn point"); 
    	            return true;
    	        }
    	    }
    	}
    		}
    	if (cmd.getName().equalsIgnoreCase("setspawn")) {
    		if(p.hasPermission("krealms.shotdown.setup")) {
    			if (!(args.length == 0)) {
    				p.sendMessage(ChatColor.RED + "KRealms Server Spawn Set!");
    				getConfig().set("spawn.world", p.getLocation().getWorld().getName());
    				getConfig().set("spawn.x", p.getLocation().getX());
    				getConfig().set("spawn.y", p.getLocation().getY());
    				getConfig().set("spawn.z", p.getLocation().getZ());
    				saveConfig();
    				p.sendMessage(ChatColor.GREEN + "Shotdown Arena Spawn Set!");
    				return true;
    			}
    			else
    			{
    				p.sendMessage(ChatColor.RED + "You cant do that!");
    				return true;
    				
    			}
    		}
    		else
    		{
    			p.sendMessage(ChatColor.RED + "You cant do that!");
    			return true;
    		}
    		
    	}
	return false;
}
}//end
 