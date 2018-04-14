package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


public final class EventsListener implements Listener {
    
    World lobby;
    World arena;
    World second_world;



        public EventsListener(World lobby,World arena,World second_world){
            this.lobby = lobby;
            this.arena = arena;
            this.second_world = second_world;
        }

    
        
	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
                player.getInventory().clear();
                
                player.teleport(new Location(lobby, -1522.666, 111.00000, 683.227, -91.6f, 2.1f));
                // x,y,z,f -> facing
                
                AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		healthAttribute.setBaseValue(20.00);
                player.setExhaustion(0);
                player.setFoodLevel(20);
                floatingText();
	}
        
        
        @EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
            
		Player player = event.getPlayer();
                
                event.setRespawnLocation(new Location(lobby, -1522.666, 111.00000, 683.227, -91.6f, 2.1f));
                
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
                AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		healthAttribute.setBaseValue(20.00);
                player.setExhaustion(0);
                player.setFoodLevel(20);
                
                player.getActivePotionEffects().forEach((effect) -> {
                    player.removePotionEffect(effect.getType());
                });
                
                if (event.getPlayer().getWorld() == second_world) {
                    player.teleport(new Location(second_world,-9.052,32.00000,17.931));
                }
                
        }
        
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onBlockBreak(BlockBreakEvent event) {
            if(event.getPlayer().getWorld() == second_world) return;
//            event.getPlayer().sendMessage(ChatColor.YELLOW + " Nu poti sparge " + ChatColor.RED + "lobby-ul!");
            event.setCancelled(true);
        }
        
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlaceBlock(BlockPlaceEvent event){
            if(event.getPlayer().getWorld() == second_world) return;
            event.setCancelled(true);
        }
        
        private void floatingText(){
            
            Location loc1 = new Location(lobby,-1516.648,111.00000,683.393);
            ArmorStand  entity1 = lobby.spawn(loc1, ArmorStand.class);
            entity1.setCustomName(ChatColor.RED + "-- To Battle arena --");
            entity1.setCustomNameVisible(true);
            entity1.setVisible(false);

            //nu se  spawneaza armour stand-urile
            
            Location loc2 = new Location(lobby,-1522.464,111.00000,689.406);
            ArmorStand  entity2 = (ArmorStand) lobby.spawnEntity(loc2, EntityType.ARMOR_STAND);
            entity2.setCustomName(ChatColor.BLUE + "-- To Second World --");
            entity2.setCustomNameVisible(true);
            entity2.setVisible(false);
            
            Location loc3 = new Location(lobby,-1522.464,111.00000,678.006);
            ArmorStand  entity3 = (ArmorStand) lobby.spawnEntity(loc3, EntityType.ARMOR_STAND);
            entity3.setCustomName(ChatColor.BLUE + "-- To Archery --");
            entity3.setCustomNameVisible(true);
            entity3.setVisible(false);
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
}
