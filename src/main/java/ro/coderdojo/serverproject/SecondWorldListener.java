package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SecondWorldListener implements Listener {
    
    //chests,low health hearts in arena

    public World second_world;
    public static Map<Player, Location> plots = new HashMap<>();
    public List<Location> plotCorners = new ArrayList();
    int nextFreePlot = 0;

    public SecondWorldListener(World second_world) {
        this.second_world = second_world;
        givePlots();
    }

    public SecondWorldListener() {
    }

    @EventHandler
    public void onJoinWorld(PlayerChangedWorldEvent event) throws Exception {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }
        Player player = event.getPlayer();
//		player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.sendMessage(ChatColor.RED + "Please remember that your inventory will be cleared once you exit this world!");
        player.setAllowFlight(true);

        if (plots.get(player) == null) {
            if (nextFreePlot > plotCorners.size() - 1) {
                event.getPlayer().sendMessage(ChatColor.RED + "No more plots free");
            } else {
                plots.put(player, plotCorners.get(nextFreePlot));
                event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "You now have a plot");
                nextFreePlot++;
            }
        }
    }

    private void givePlots() {

        int locx = 209;
        int locz = -241;
        for (int x = 1; x <= 5; x++) {
            for (int z = 1; z <= 5; z++) {
                plotCorners.add(new Location(second_world, locx, 32, locz));
                locz = locz + 44;
            }
            locz = -241;
            locx = locx - 44;
        }
//        for (Location loc : plotCorners) {
//            loc.getBlock().setType(Material.REDSTONE_BLOCK);
//        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }

        Location breakLocation = event.getBlock().getLocation();
        Location plotLocation = plots.get(event.getPlayer());
//        System.out.println("1 : " + breakLocation.getX() + " < " + plotLocation.getX());
//        System.out.println("2 : " + breakLocation.getX() + " > " + (plotLocation.getX() - 39));
//        System.out.println("1 : " + breakLocation.getZ() + " > " + plotLocation.getZ());
//        System.out.println("2 : " + breakLocation.getZ() + " < " + (plotLocation.getZ() - 39));
        if (breakLocation.getX() < plotLocation.getX() && breakLocation.getX() > plotLocation.getX() - 39) {
            if (breakLocation.getZ() > plotLocation.getZ() && breakLocation.getZ() < plotLocation.getZ() + 39) {
                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }

        Location breakLocation = event.getBlock().getLocation();
        Location plotLocation = plots.get(event.getPlayer());
        if (breakLocation.getX() < plotLocation.getX() && breakLocation.getX() > plotLocation.getX() - 39) {
            if (breakLocation.getZ() > plotLocation.getZ() && breakLocation.getZ() < plotLocation.getZ() + 39) {
                return;
            }
        }
        event.setCancelled(true);
    }

    //------------------------------------------------------------------------------------------------------
    
    @EventHandler
    public void shop(PlayerInteractEvent event) {
        Shop shop = new Shop(second_world);
        shop.onPlayerInteract(event);
    }

    @EventHandler
    public void OnMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }

        Player player = event.getPlayer();

        if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GLOWSTONE) {
            player.teleport(new Location(second_world, -3, 32.00000, -29, -90.5f, 1.5f));
        }

    }

//    public void teleportToPlot(Player player){           
//            Location plotLocation = plots.get(player);      
//            player.teleport(plotLocation);
//    }
}
