package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SecondWorldListener implements Listener {

    public World second_world;
    ArrayList<Location> locations = new ArrayList<>();
    Map<Player,List<Location>> plots = new HashMap<>();

    public SecondWorldListener(World second_world) {
        this.second_world = second_world;
        floatingText();
    }
    
    @EventHandler
	public void onJoin(PlayerChangedWorldEvent event) throws Exception {
            if (event.getPlayer().getWorld() != second_world) {
                return;
            }
		Player player = event.getPlayer();
//		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
                player.sendMessage(ChatColor.RED + "Please remember that your inventory will be cleared once you exit this world!");
        }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        
        if (event.getPlayer().getWorld() != second_world) {
                return;
        }

        Player player = event.getPlayer();

        player.getActivePotionEffects().forEach((effect) -> {
            player.removePotionEffect(effect.getType());
        });

        player.setExhaustion(0);
        player.setFoodLevel(20);
        player.getInventory().clear();

    }
    
    
        Location loc1 = new Location(second_world, -8.029, 32.00000, 13.559);
        Location loc2 = new Location(second_world, -8.073, 32.00000, 21.127);

        
    private void floatingText() {
        
        ArmorStand  entity = (ArmorStand) second_world.spawnEntity(loc1, EntityType.ARMOR_STAND);
        entity.setCustomName(ChatColor.WHITE + "-"+ChatColor.GOLD + "To your plot" + ChatColor.WHITE + "-");
        entity.setCustomNameVisible(true);
        entity.setVisible(false);       

        ArmorStand entity2 = (ArmorStand) second_world.spawnEntity(loc2, EntityType.ARMOR_STAND);
        entity2.setCustomName(ChatColor.WHITE + "-"+ChatColor.RED + " To shop "+ChatColor.WHITE + "-");
        entity2.setCustomNameVisible(true);
        entity2.setVisible(false);

    }
    
    @EventHandler
    public void shop(PlayerInteractEvent event){
        Shop shop = new Shop(second_world);
        shop.onPlayerInteract(event);
    }
    
}
