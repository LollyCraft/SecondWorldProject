package ro.coderdojo.serverproject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShop implements CommandExecutor {
    
    public World second_world = Bukkit.getServer().getWorld("Second_world");

    @Override
    public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
        if (sender instanceof Player) {
            
            Player player = (Player) sender;
            
            if(player.getWorld() == second_world){  
            
                
            player.teleport(new Location(second_world, -3, 32.00000, -29, -90.5f, 1.5f));
            player.sendMessage(ChatColor.BLUE + "Teleported to " + ChatColor.GOLD + "shop.");
            
            }else{
                player.sendMessage(ChatColor.BLUE + "-- " + ChatColor.RED + "You must be in Second World to make that command! "+ChatColor.BLUE + "--" );
            }
            
        }

        return true;
    }
}
