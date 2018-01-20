package ro.coderdojo.serverproject;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public final class LobbyListener implements Listener {

    public World lobby;
    public Plugin plugin;

    public LobbyListener(World lobby, Plugin plugin) {
        this.plugin = plugin;
        this.lobby = lobby;
    }

     @EventHandler
	public void OnMove(PlayerMoveEvent event) {
            if(event.getPlayer().getWorld() != lobby) {
                return;
            }
            
            Player player = event.getPlayer();
            
            if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.EMERALD_BLOCK) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2));
                }
            
            
        }
    
}

