package ro.coderdojo.serverproject;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelpMoney implements CommandExecutor {
            
                @Override
                public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    
                    player.sendMessage(ChatColor.YELLOW+"-----------------------------------------------------");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.BLUE+"You can get/spend money by playing the minigames : ");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.WHITE+"- In the Battle Arena - each player killed gives you 400 coins ");
                    player.sendMessage(ChatColor.WHITE+"- In Archery - each target killed gives you a specific amount of coins ");
                    player.sendMessage(ChatColor.WHITE+"- In Second World - you can buy materials/blocks with your money and use them to build on your plot ");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.YELLOW+"-----------------------------------------------------");
                }

                 // If the player (or console) uses our command correct, we can return true
                 return true;
            }
}
