package ro.coderdojo.serverproject;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub implements CommandExecutor {
    
        public World second_world = Bukkit.getServer().getWorld("Second_world");
            
                @Override
                public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    
                    player.getInventory().clear();
                    player.teleport(new Location(MainPlugin.lobby,-1522.666, 111.00000, 683.227, -91.6f, 2.1f));
                    player.sendMessage(ChatColor.BLUE + "Teleported to lobby");
                }

                 // If the player (or console) uses our command correct, we can return true
                 return true;
            }
}
