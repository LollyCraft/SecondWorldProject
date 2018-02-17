package ro.coderdojo.serverproject;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class LobbyListener implements Listener {

    public World lobby;
    public World arena;
    public Plugin plugin;

    public LobbyListener(World lobby, World arena) {
        this.lobby = lobby;
        this.arena = arena;
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
//                            player.teleport(new Location(arena, 3.882, 118.00000, 117.717, 1.7f, 4.8f));//coord pt SkyWars_Map original
                            
			}
		}
	}
    
}

