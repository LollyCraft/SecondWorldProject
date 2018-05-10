package ro.coderdojo.serverproject;

import static java.awt.SystemColor.text;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
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
                
                //Bukkit.getServer().dispatchCommand(player, "title @a title {"text":"WELCOME","color":"gold"}");
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
//            event.getPlayer().sendMessage(ChatColor.YELLOW + " YOU cannot " + ChatColor.RED + "break");
            event.setCancelled(true);
        }
        
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlaceBlock(BlockPlaceEvent event){
            if(event.getPlayer().getWorld() == second_world) return;
            event.setCancelled(true);
        }
        
        List<ArmorStand> floatingTexts = new ArrayList();
        
    @SuppressWarnings("empty-statement")
        private void floatingText(){
            
            //------------------------------------------------------------------------------
            
            //LOBBY TEXTS 
            Location loc1 = new Location(lobby,-1516.648,111.00000,683.393);
            ArmorStand  entity1 = lobby.spawn(loc1, ArmorStand.class);
            entity1.setCustomName(ChatColor.RED + "-- To Battle arena --");
            floatingTexts.add(entity1);
            
            Location loc4 = new Location(lobby,-1501,108.00000,683);
            ArmorStand  entity4 = (ArmorStand) lobby.spawnEntity(loc4, EntityType.ARMOR_STAND);
            entity4.setCustomName(ChatColor.GOLD + "Kill players to get money");
            floatingTexts.add(entity4);
            
            Location loc5 = new Location(lobby,-1501,108.00000,683).subtract(0,0.3,0);
            ArmorStand  entity5 = (ArmorStand) lobby.spawnEntity(loc5, EntityType.ARMOR_STAND);
            entity5.setCustomName("Each special block has an effect.");
            floatingTexts.add(entity5);
            //---------------
            
            Location loc2 = new Location(lobby,-1522.464,111.00000,689.406);
            ArmorStand  entity2 = (ArmorStand) lobby.spawnEntity(loc2, EntityType.ARMOR_STAND);
            entity2.setCustomName(ChatColor.BLUE + "-- To Second World --");
            floatingTexts.add(entity2);
            
            Location loc6 = new Location(lobby,-1522,108.00000,704);
            ArmorStand  entity6 = (ArmorStand) lobby.spawnEntity(loc6, EntityType.ARMOR_STAND);
            entity6.setCustomName(ChatColor.GOLD + "Create your own house");;
            floatingTexts.add(entity6);
            //---------------
            
            Location loc3 = new Location(lobby,-1522.464,111.00000,678.006);
            ArmorStand  entity3 = (ArmorStand) lobby.spawnEntity(loc3, EntityType.ARMOR_STAND);
            entity3.setCustomName(ChatColor.BLUE + "-- To Archery --");
            floatingTexts.add(entity3);
            
            Location loc7 = new Location(lobby,-1522,108.00000,662);
            ArmorStand  entity7 = (ArmorStand) lobby.spawnEntity(loc7, EntityType.ARMOR_STAND);
            entity7.setCustomName(ChatColor.GOLD + "Kill targets to get money");;
            floatingTexts.add(entity7);
            //--------------------------------------------------------------------------------
            
            //SECOND WORLD TEXTS
            Location loc8 = new Location(second_world, -8.029, 32.00000, 13.559);
            Location loc9 = new Location(second_world, -8.073, 32.00000, 21.127);
    
            ArmorStand entity8 = (ArmorStand) second_world.spawnEntity(loc8, EntityType.ARMOR_STAND);
            entity8.setCustomName(ChatColor.WHITE + "-" + ChatColor.GOLD + "To your plot" + ChatColor.WHITE + "-");
            floatingTexts.add(entity8);

            ArmorStand entity9 = (ArmorStand) second_world.spawnEntity(loc8.subtract(0, 0.5, 0), EntityType.ARMOR_STAND);
            entity9.setCustomName(ChatColor.WHITE + "-" + ChatColor.GREEN + " type /plot to teleport " + ChatColor.WHITE + "-");
            floatingTexts.add(entity9);

            ArmorStand entity10 = (ArmorStand) second_world.spawnEntity(loc9, EntityType.ARMOR_STAND);
            entity10.setCustomName(ChatColor.WHITE + "-" + ChatColor.GOLD + " To shop " + ChatColor.WHITE + "-");
            floatingTexts.add(entity10);

            ArmorStand entity11 = (ArmorStand) second_world.spawnEntity(loc9.subtract(0, 0.5, 0), EntityType.ARMOR_STAND);
            entity11.setCustomName(ChatColor.WHITE + "-" + ChatColor.GREEN + " Glowstones teleport you " + ChatColor.WHITE + "-");
            floatingTexts.add(entity11);
            
            //----------------
            for(ArmorStand ar :floatingTexts){
                ar.setCustomNameVisible(true);
                ar.setVisible(false);
                ar.setGravity(false);
            }
        }
}
