package ro.coderdojo.serverproject;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCreature;

public class CountDownTimer extends BukkitRunnable {

    public int counter = 5 * 20;

    List<CraftCreature> creatures;
    Player player;

    public CountDownTimer(List<CraftCreature> creatures, Player player) {
        this.creatures = creatures;
        this.player = player;
    }

    @Override
    public void run() {
        if (counter > 0) {
            if (counter % 20 == 0) {
//                            MainPlugin.plugin.getServer().broadcastMessage(ChatColor.WHITE + "Power for " + ChatColor.RED + counter / 20 + ChatColor.WHITE + " seconds");
            }
            counter = counter - 1;
        } else {
            
            if (creatures != null){
                for (CraftCreature pet : creatures) {
                 pet.remove();
             }
                creatures.removeAll(creatures);
            }
            this.cancel();

        }
    }

//        @EventHandler
    public void messagePlayer(Player player) {

                player.getInventory().clear();
                player.sendMessage(ChatColor.RED + "Power has been used." );
                
//            player.sendMessage(ChatColor.WHITE + "Power for " + ChatColor.RED + counter / 20 + ChatColor.WHITE + " seconds");
        //e enervant
        }
}

