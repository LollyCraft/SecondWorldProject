package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;


public class PowerBlocks {
    
    
    public ArrayList<Location> blockLocations = new ArrayList<>();
  
    
    public void placeBlock(World arena){
            
            
            for (double locX = -322; locX >= -368; locX--) {
		for (double locZ = 48; locZ >= 3; locZ--) {
			blockLocations.add(new Location(arena, locX, 3.00000, locZ));
		}
            }

            
            Location blockPetLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block petBlock = arena.getBlockAt(blockPetLocation);
            petBlock.setType(Material.EMERALD_BLOCK);
//            System.out.println("*******placed pet block******");
            
            Location blockKitLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block kitBlock = arena.getBlockAt(blockKitLocation);
            kitBlock.setType(Material.DIAMOND_BLOCK);
//            System.out.println("*******placed GOD KIT block******");
            
            Location blockWitchLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block witchLocation = arena.getBlockAt(blockWitchLocation);
            witchLocation.setType(Material.GOLD_BLOCK);
//            System.out.println("*******placed WITCH KIT block******");
            
            Location blockHealingLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block healingLocation = arena.getBlockAt(blockHealingLocation);
            healingLocation.setType(Material.REDSTONE_BLOCK);
//            System.out.println("*******placed Healing block******");
        }
    
}
