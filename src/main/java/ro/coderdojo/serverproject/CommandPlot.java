package ro.coderdojo.serverproject;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlot implements CommandExecutor {
    
    public World second_world = Bukkit.getServer().getWorld("Second_world");

    @Override
    public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
        if (sender instanceof Player) {
            
            Map<Player, Location> plots = SecondWorldListener.plots;
            Player player = (Player) sender;
            
            if(player.getWorld() == second_world){  
            
            System.out.println("SWL ESTE: " + plots.toString());
            System.out.println(player);
            Location plotLocation = plots.get(player);
            player.teleport(plotLocation);
            player.sendMessage(ChatColor.BLUE + "Teleported to " + ChatColor.GOLD + "your plot.");
            
            }else{
                player.sendMessage(ChatColor.BLUE + "-- " + ChatColor.RED + "You must be in Second World to make that command! "+ChatColor.BLUE + "--" );
            }
            
        }

        return true;
    }
}
