package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class ArenaListener implements Listener {

	public World arena;
	
	ArrayList<Location> beaconLocations = new ArrayList();

	public ArenaListener(World arena) {
		this.arena = arena;
            locateBeacons();
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

//            Location playerOldLocation = event.getPlayer().getLocation().subtract(0, 1, 0);
//            Location centru = new Location(arena,-344.613,4.00000,28.350);
//		for (double locX = -322; locX >= -368; locX--) {
//			for (double locZ = 48; locZ >= 3; locZ--) {
//				tpLocation.add(new Location(arena, locX, 4.00000, locZ));
//			}
//		}

		//if the block at the player location minus 1 on y is dirt
		if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.BEACON) {
			Location Chance = beaconLocations.get(new Random().nextInt(beaconLocations.size()));
			event.getPlayer().teleport(Chance);
			player.sendMessage("You have been teleported to " + Chance);
		}
	}
	//        public void fillChest(){
//            
//            Location loc = new Location(arena,3.582,118.00000,121.700);
//        
//        arena.getBlockAt(loc).setType(Material.CHEST);
//        Block block = loc.getBlock();
//        Chest chest = (Chest)block.getState();
//        Inventory inv = chest.getInventory();
//       
//        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
//        ItemStack gold_boots = new ItemStack(Material.GOLD_BOOTS, 1);
//        ItemStack fish = new ItemStack(Material.COOKED_FISH, 1);
//        ItemStack golden_apple = new ItemStack(Material.GOLDEN_APPLE, 1);
//        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 2);
//       
//        inv.addItem(diamond_sword, gold_boots, fish, golden_apple, enderpearl);
//            
//        }

	public void locateBeacons() {
		int amount = 0;

		for (Chunk chunk : arena.getLoadedChunks()) {
			int cx = chunk.getX() << 4;
			int cz = chunk.getZ() << 4;
			for (int x = cx; x < cx + 16; x++) {
				for (int z = cz; z < cz + 16; z++) {
					for (int y = 0; y < 128; y++) {
						Block block = arena.getBlockAt(x, y, z);
						if (block.getType() == Material.BEACON) {
							System.out.println("Found beacon: " + block.getLocation());
							beaconLocations.add(arena.getHighestBlockAt(block.getLocation()).getLocation());
							amount++;
						}
					}
				}
			}
		}
		System.out.println("Total beacons found: " + amount);
	}

}
