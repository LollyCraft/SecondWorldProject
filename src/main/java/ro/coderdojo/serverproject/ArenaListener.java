package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class ArenaListener implements Listener {

	public World arena;
	
	ArrayList<Location> beaconLocations = new ArrayList();
	
	public static HashMap<String, Location> lastTeleportLocation = new HashMap<>();

	public ArenaListener(World arena) {
		this.arena = arena;
            locateBeacons();
            placeBlock();
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();

		player.getActivePotionEffects().forEach((effect) -> {
			player.removePotionEffect(effect.getType());
		});

		player.setExhaustion(0);
		player.setFoodLevel(20);
	}

	@EventHandler
	public void OnMove(PlayerMoveEvent event) {
		if (event.getPlayer().getWorld() != arena) {
			return;
		}
		
		Player player = event.getPlayer();
		
	 
	 
		if(lastTeleportLocation.get(player.getName()) != null ){
			Location lastTeleportBlock =  lastTeleportLocation.get(player.getName()).getBlock().getLocation();	
			Location playerPositionBlock = player.getLocation().getBlock().getLocation();
			
			int lastTeleportBlockX = lastTeleportBlock.getBlockX();
			int lastTeleportBlockZ = lastTeleportBlock.getBlockZ();
			
			int playerPositionBlockX = playerPositionBlock.getBlockX();
			int playerPositionBlockZ = playerPositionBlock.getBlockZ();
			
			System.out.println("1:" + lastTeleportBlock);
			System.out.println("2:" + playerPositionBlock);
			if(lastTeleportBlockX == playerPositionBlockX && lastTeleportBlockZ == playerPositionBlockZ) {
				System.out.println("exit");
				return;
			} else {
				System.out.println("reset");
				lastTeleportLocation.remove(player.getName());
			}
		} 

		//if the block at the player location minus 1 on y is dirt
                    
		if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.BEACON) {
			Location chance = beaconLocations.get(new Random().nextInt(beaconLocations.size()));
			event.getPlayer().teleport(chance);
			player.sendMessage("You have been teleported to " + chance);
			lastTeleportLocation.put(player.getName(), chance);
		}
	}

	public void locateBeacons() {
		int amount = 0;

		for (Chunk chunk : arena.getLoadedChunks()) {
			int cx = chunk.getX() << 4;
			int cz = chunk.getZ() << 4;
			for (int x = cx; x < cx + 16; x++) {
				for (int z = cz; z < cz + 16; z++) {
					for (int y = 0; y < 128; y++) {
						Block block = arena.getBlockAt(x, y+1, z);
						if (block.getType() == Material.BEACON) {
							System.out.println("Found beacon: " + block.getLocation());
							beaconLocations.add(arena.getHighestBlockAt(block.getLocation()).getLocation().subtract( 0, -1, 0));
							amount++;
						}
					}
				}
			}
		}
		System.out.println("Total beacons found: " + amount);
	}

        
        //            Location playerOldLocation = event.getPlayer().getLocation().subtract(0, 1, 0);
//            Location centru = new Location(arena,-344.613,4.00000,28.350);
//		for (double locX = -322; locX >= -368; locX--) {
//			for (double locZ = 48; locZ >= 3; locZ--) {
//				tpLocation.add(new Location(arena, locX, 4.00000, locZ));
//			}
//		}
        
        
        //*******************  animal_pet with an invisible golem in it  **************************
        Map <String, LivingEntity> pets = new HashMap<>();
        ArrayList<Location> blockLocations = new ArrayList<>();
        

        public void placeBlock(){
            
            
            for (double locX = -322; locX >= -368; locX--) {
		for (double locZ = 48; locZ >= 3; locZ--) {
			blockLocations.add(new Location(arena, locX, 3.00000, locZ));
		}
            }

            
            Location blockLocation = blockLocations.get(new Random().nextInt(blockLocations.size()));
            Block petBlock = arena.getBlockAt(blockLocation);
            petBlock.setType(Material.EMERALD_BLOCK);
            System.out.println("*******placed pet block******");
            
            
        }

        @EventHandler
        public void petSpawn(PlayerMoveEvent event){ 
            
            if (event.getPlayer().getWorld() != arena) {
			return;
		}
            
            //se spawneaza o tona de mobs,imi trebuie o variabila ce sa opreasca asta
            Location spawnLocation = event.getPlayer().getLocation().subtract(0, 1, 0);
            
            
            if(spawnLocation.getBlock().getType() == Material.EMERALD_BLOCK){
                
                    
                    Entity cow = arena.spawnEntity(spawnLocation.add(0,1,0), EntityType.COW);
                    LivingEntity golem = (LivingEntity) arena.spawnEntity(spawnLocation.add(0,1,0), EntityType.IRON_GOLEM);
 
                    cow.setPassenger(golem);
//                  cow.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 2));
                
                    spawnLocation.subtract(0,1,0).getBlock().setType(Material.AIR);
                    spawnLocation.subtract(0,1,0).getBlock().setType(Material.SAND);
                
            }
        }
        

}
