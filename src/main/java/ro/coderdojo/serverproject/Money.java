package ro.coderdojo.serverproject;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Money extends JavaPlugin implements Listener {
    
//    references:
//    https://www.youtube.com/watch?v=HuY8vwd6Fds
//    https://www.spigotmc.org/threads/custom-currency-help.63876/
//    https://www.youtube.com/watch?v=4w-m4QY99u0
    
    
    public static JavaPlugin plugin;
    private HashMap<UUID, Integer> money = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @Override
    public void onDisable() {
        for (Entry<UUID, Integer> entry : money.entrySet()) {
            getConfig().set(entry.getKey() + ".Silver", entry.getValue());
        }

        saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (!getConfig().contains(p.getUniqueId().toString())) {
            getConfig().set(p.getUniqueId() + ".Silver", 0);
            money.put(p.getUniqueId(), 0);
            System.out.println("Money bank created for this username.");
        } else {
            money.put(p.getUniqueId(), getConfig().getInt(p.getUniqueId() + ".Silver"));
            System.out.println("Money bank is already created for this username.");
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Monster) {
            if (event.getEntity().getKiller() instanceof Player) {
                Player p = event.getEntity().getKiller();
                giveSilver(p, 200);
                System.out.println("200 silver added your to money bank");
            }
        } else if (event.getEntity() instanceof Villager) {
            if (event.getEntity() instanceof Player) {
                Player p = event.getEntity().getKiller();
                takeSilver(p, 200);
            }
        }
    }

    private void giveSilver(Player p, int i) {
        UUID uuid = p.getUniqueId();
        money.put(uuid, money.get(uuid) + i);
        p.sendMessage("§2§l$" + i + " silver received!");
    }

    private void takeSilver(Player p, int i) {
        UUID uuid = p.getUniqueId();
        money.put(uuid, money.get(uuid) - i);
        p.sendMessage("§c§l$" + i + " silver taken!");
    }
}
