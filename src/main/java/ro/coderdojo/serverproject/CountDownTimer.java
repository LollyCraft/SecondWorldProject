
package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownTimer extends BukkitRunnable {
	private int counter = 5 * 20;

        
	@Override
	public void run() {
		if (counter > 0) {
			if (counter % 20 == 0) {
                            MainPlugin.plugin.getServer().broadcastMessage(ChatColor.WHITE + "Power for " + ChatColor.RED + counter / 20 + ChatColor.WHITE + " seconds");
			}
			counter = counter - 1;
		} else {
			this.cancel();
		}
	}
       
//        @EventHandler
        public void messagePlayer(PlayerEvent event){
            
            Player player = event.getPlayer();
            
            if(counter == 0){
                player.getInventory().clear();
            }else{
                player.sendMessage(ChatColor.WHITE + "Power for " + ChatColor.RED + counter / 20 + ChatColor.WHITE + " seconds");
            }
        }
}