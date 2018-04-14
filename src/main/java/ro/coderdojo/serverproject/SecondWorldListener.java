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

    public World second_world;
    ArrayList<Location> locations = new ArrayList<>();
    Map<Player, Location> plots = new HashMap<>();
    List<Location> plotCorners = new ArrayList();
    int nextFreePlot = 0;

    public SecondWorldListener(World second_world) {
        this.second_world = second_world;
        givePlots();
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
        floatingText();

        if (plots.get(player) == null) {
            if (nextFreePlot > plotCorners.size() - 1) {
                event.getPlayer().sendMessage(ChatColor.RED + "No more plots free");
            } else {
                plots.put(player, plotCorners.get(nextFreePlot));
                event.getPlayer().sendMessage(ChatColor.BLUE + "You now have a plot");
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
        for (Location loc : plotCorners) {
            loc.getBlock().setType(Material.REDSTONE_BLOCK);
        }
    }

//        @EventHandler
//        public void onJoinServer(PlayerJoinEvent event){
//            if (event.getPlayer().getWorld() != second_world) {
//                return;
//            }
//            if(nextFreePlot > plotCorners.size()-1){
//                event.getPlayer().sendMessage(ChatColor.RED+"No more plots free");
//            }else{
//                Player player = event.getPlayer();
//                plots.put(player,plotCorners.get(nextFreePlot));
//                event.getPlayer().sendMessage(ChatColor.BLUE+"You now have a plot");
//                nextFreePlot++;
//            }
//        }
    
    //SA FAC COMANDA DE TP LA PLOTUL TAU!!!!!!!!!!
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }

        Location breakLocation = event.getBlock().getLocation();
        Location plotLocation = plots.get(event.getPlayer());
        System.out.println("1 : " + breakLocation.getX() + " < " + plotLocation.getX());
        System.out.println("2 : " + breakLocation.getX() + " > " + (plotLocation.getX() - 39));
        System.out.println("1 : " + breakLocation.getZ() + " > " + plotLocation.getZ());
        System.out.println("2 : " + breakLocation.getZ() + " < " + (plotLocation.getZ() - 39));
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
    Location loc1 = new Location(second_world, -8.029, 32.00000, 13.559);
    Location loc2 = new Location(second_world, -8.073, 32.00000, 21.127);

    private void floatingText() {

        ArmorStand entity = (ArmorStand) second_world.spawnEntity(loc1, EntityType.ARMOR_STAND);
        entity.setCustomName(ChatColor.WHITE + "-" + ChatColor.GOLD + "To your plot" + ChatColor.WHITE + "-");
        entity.setCustomNameVisible(true);
        entity.setVisible(false);

        ArmorStand entity2 = (ArmorStand) second_world.spawnEntity(loc2, EntityType.ARMOR_STAND);
        entity2.setCustomName(ChatColor.WHITE + "-" + ChatColor.RED + " To shop " + ChatColor.WHITE + "-");
        entity2.setCustomNameVisible(true);
        entity2.setVisible(false);

    }

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
}
