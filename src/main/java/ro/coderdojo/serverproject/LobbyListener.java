package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class LobbyListener implements Listener {

    public World lobby;
    public World arena;
    public World second_world;
    public Plugin plugin;


    public LobbyListener(World lobby, World arena,World second_world) {
        this.lobby = lobby;
        this.arena = arena;
        this.second_world = second_world;
        
        floatingText();
    }
    
//    @EventHandler
//	public void playerJoined(PlayerJoinEvent event) throws Exception {
//		Player player = event.getPlayer();
//		player.setGameMode(GameMode.SURVIVAL);
//		player.getInventory().clear();
//                
//        }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        player.getActivePotionEffects().forEach((effect) -> {
            player.removePotionEffect(effect.getType());
        });

        player.setExhaustion(0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.setExhaustion​(0);
    }

     @EventHandler
	public void OnMove(PlayerMoveEvent event) {
            if(event.getPlayer().getWorld() != lobby) {
                return;
            }
            
            Player player = event.getPlayer();
            
            if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.EMERALD_BLOCK) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 2));
                }
            
            
        }
        
        @EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (event.getClickedBlock() == null) {
			return;
		}
		Material material = event.getClickedBlock().getState().getType();
		Location location = event.getClickedBlock().getState().getLocation();
		if (action == Action.RIGHT_CLICK_BLOCK && material == Material.STONE_BUTTON) {
			System.out.println("Click: " + location);
                        
			Location button1 = new Location(MainPlugin.lobby, -1490, 109.0, 683.0, location.getYaw(), location.getPitch());
			if (location.equals(button1)) {                   
                            player.teleport(new Location(arena, -344.613,4.00000,28.350));
                            player.sendMessage(ChatColor.BLUE + "Teleported to Battle Arena");
//                            player.teleport(new Location(arena, 3.882, 118.00000, 117.717, 1.7f, 4.8f));//coord pt SkyWars_Map original
			}
                        
                        Location button2 = new Location(MainPlugin.lobby,-1523, 109.0, 716.0, location.getYaw(), location.getPitch());
                        if (location.equals(button2)) {                   
                            player.teleport(new Location(second_world, -9.052,32.00000,17.931));
                            player.sendMessage(ChatColor.BLUE + "Teleported to Second World");
			}
                        
		}                  
	}
        
        @EventHandler
        public void onMove(PlayerMoveEvent event){
            
            Player player = event.getPlayer();
            
            Location loc = player.getLocation().subtract(0, 1, 0);
                if(loc.getBlock().getType() == Material.GOLD_BLOCK){
                    player.teleport(new Location(lobby, -1522, 111.0, 683.0));
                    player.sendMessage("Teleported back to lobby");
                }
        }
        
        public void floatingText(){
            
            Location loc1 = new Location(lobby,-1516.648,111.00000,683.393);
            ArmorStand  entity = (ArmorStand) lobby.spawnEntity(loc1, EntityType.ARMOR_STAND);
            entity.setCustomName(ChatColor.RED + "-- To Battle arena --");
            entity.setCustomNameVisible(true);
            entity.setVisible(false);
            
            Location loc2 = new Location(lobby,-1522.464,111.00000,689.406);
            ArmorStand  entity2 = (ArmorStand) lobby.spawnEntity(loc2, EntityType.ARMOR_STAND);
            entity2.setCustomName(ChatColor.WHITE + "-- To Second World --");
            entity2.setCustomNameVisible(true);
            entity2.setVisible(false);
        }
    
}

