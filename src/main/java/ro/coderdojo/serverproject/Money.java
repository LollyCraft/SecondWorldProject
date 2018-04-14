package ro.coderdojo.serverproject;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Money implements Listener {
    
//    references:
//    https://www.youtube.com/watch?v=HuY8vwd6Fds
//    https://www.spigotmc.org/threads/custom-currency-help.63876/
//    https://www.youtube.com/watch?v=4w-m4QY99u0
    
    
    public Map<String, Integer> money = new HashMap<>();
    private static Money soleInstance = new Money();
            
    
    private Money(){
        
    }
 
    public static Money getInstance(){
        return soleInstance;
    }
    

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (money.get(p.getUniqueId().toString()) == null) {
            money.put(p.getUniqueId().toString(), 1200);
            System.out.println("Money bank created for this username.");
        } else {
            System.out.println("Money bank is already created for this username.");
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) { //monster merge
            if (event.getEntity().getKiller() instanceof Player) {
                Player p = event.getEntity().getKiller();
                giveSilver(p, 200);
//                p.sendMessage("200 silver added to your money bank");
            }
        }
    }

    public void giveSilver(Player p, int i) {
        String uuid = p.getUniqueId().toString();
        money.put(uuid, money.get(uuid) + i );
        p.sendMessage( i + " silver received!");
    }

    public void takeSilver(Player p, int i) {
        String uuid = p.getUniqueId().toString();
        money.put(uuid, money.get(uuid) - i);
        p.sendMessage( i + " silver taken!");
    }
}
