package ro.coderdojo.serverproject;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMoney implements CommandExecutor {
        Money m = Money.getInstance();        
        
                @Override
                public boolean onCommand(CommandSender sender, Command HubCommand, String hub, String[] args) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String uuid = player.getUniqueId().toString();
             
                    player.sendMessage(ChatColor.BLUE + "-----------------");
                    player.sendMessage(ChatColor.RED + "You have " + m.money.get(uuid));
                    player.sendMessage(ChatColor.BLUE + "-----------------");
                }

                 // If the player (or console) uses our command correct, we can return true
                 return true;
            }
                
                
}
