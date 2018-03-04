package ro.coderdojo.serverproject;


import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class RepeatTimer extends BukkitRunnable{
    
      public World arena = Bukkit.getServer().getWorld("Arena_map");
      
       ArrayList<Location> blockLocations = new ArrayList<>();
    
            private int counter = 5 * 20;


	@Override
	public void run() {
		if (counter > 0) {
			if (counter % 20 == 0) {
				MainPlugin.plugin.getServer().broadcastMessage("Placed");
                                placePowBlock(arena);
			}
			counter = counter - 1;
                }
	}
        
        public void placePowBlock(World arena){
            PowerBlocks powBlock = new PowerBlocks();
            powBlock.placeBlock(arena);
        }
           
        
//                .runTaskTimer(MainPlugin.plugin, 20L, 0L);

    }
    

