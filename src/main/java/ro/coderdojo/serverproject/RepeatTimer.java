package ro.coderdojo.serverproject;


import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RepeatTimer extends BukkitRunnable{
    
      public World arena;
       ArrayList<Location> blockLocations = new ArrayList<>();
       Player player ;
    
            @Override
            public void run() {
            
            
            for (double locX = -322; locX >= -368; locX--) {
		for (double locZ = 48; locZ >= 3; locZ--) {
			blockLocations.add(new Location(arena, locX, 3.00000, locZ));
		}
            }

            
            Location blockLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block petBlock = arena.getBlockAt(blockLocation);
            //exception here
            petBlock.setType(Material.EMERALD_BLOCK);
            System.out.println("*******placed pet block******");
            
            
                //Code to run every 2 seconds
                player.sendMessage("Test");
            }
           
        
//                .runTaskTimer(MainPlugin.plugin, 20L, 0L);

    }
    

