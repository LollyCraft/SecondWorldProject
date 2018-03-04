
package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SecondWorldListener implements Listener {
    
    public World second_world;

    public SecondWorldListener(World second_world) {
        this.second_world = second_world;
      
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        player.getActivePotionEffects().forEach((effect) -> {
            player.removePotionEffect(effect.getType());
        });

        player.setExhaustion(0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        
        animalSigns();
    }
    
    
    public void animalSigns(){
        Location loc1 = new Location(second_world,-8.009,32.00000,18.298);
        LivingEntity entity = (LivingEntity) second_world.spawnEntity(loc1, EntityType.COW);
        entity.setCustomName(ChatColor.GOLD + "To your plot");
        entity.setCustomNameVisible(true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 2));
        
        Location loc2 = new Location(second_world,-5.192, 32.00000, 20.949);
        LivingEntity entity2 = (LivingEntity) second_world.spawnEntity(loc2, EntityType.COW);
        entity2.setCustomName(ChatColor.GOLD + "To shop");
        entity2.setCustomNameVisible(true);
        entity2.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 2));
    }
    
}
