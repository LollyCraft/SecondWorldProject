package ro.coderdojo.serverproject;


import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class RepeatTimer extends BukkitRunnable{
    
      public World arena = Bukkit.getServer().getWorld("Arena_map");
      
       ArrayList<Location> blockLocations = new ArrayList<>();
    
            private int counter = 10;


	@Override
	public void run() {
		if (counter > 0) {
//				MainPlugin.plugin.getServer().broadcastMessage("Placed");
                                placePowBlock(arena);
			
			counter--;
                } else{
                    this.cancel();
                }
	}
        
        public void placePowBlock(World arena){
            PowerBlocks powBlock = new PowerBlocks();
            powBlock.placeBlock(arena);
            counter++;
        }
           
        
//                .runTaskTimer(MainPlugin.plugin, 20L, 0L);

    }
    

