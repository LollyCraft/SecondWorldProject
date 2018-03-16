package ro.coderdojo.serverproject;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub implements CommandExecutor {
            
                @Override
                public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
             
                    player.teleport(new Location(MainPlugin.lobby,-1522, 111.0, 683.0));
                    player.sendMessage(ChatColor.BLUE + "Teleported to lobby");
                }

                 // If the player (or console) uses our command correct, we can return true
                 return true;
            }
        }
