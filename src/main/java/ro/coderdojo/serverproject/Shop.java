package ro.coderdojo.serverproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCreature;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Shop {

    public World second_world;
    Money m = Money.getInstance();
    

    public Shop(World second_world) {
        this.second_world = second_world;
    }

//    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld() != second_world) {
            return;
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        Location loc = player.getLocation();

        if ((loc.getX() < 33) && (loc.getZ() < -25) && (loc.getZ() > -64) && (loc.getX() > -5)) {

            if (action == Action.RIGHT_CLICK_BLOCK && (block.getType().equals(Material.SIGN_POST) || block.getType().equals(Material.WALL_SIGN))) {
                Sign sign = (Sign) block.getState();

                if (sign.getLine(0).equals("oak wood")) {
                    player.sendMessage("I like ur sign :) - oak wood - took 200 silver");
                    if (m.money.get(player.getUniqueId().toString()) >= 200) {
                        takeSilver(player, 200);
                        player.getInventory().addItem(new ItemStack(Material.LOG));
                    } else {
                        player.sendMessage(ChatColor.RED + "Not enough money");
                        player.sendMessage(ChatColor.RED + "You have just " + m.money.get(player.getUniqueId().toString()));
                    }
                } else if (sign.getLine(0).equals("bookshelf")) {
                    player.sendMessage("I like ur sign :) - bookshelf - took 100 silver");
                    if (m.money.get(player.getUniqueId().toString()) >= 100) {
                        takeSilver(player, 100);
                        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF));
                    } else {
                        player.sendMessage(ChatColor.RED + "Not enough money");
                        player.sendMessage(ChatColor.RED + "You have just " + m.money.get(player.getUniqueId().toString()));
                    }
                }
            }

        }

    }

    public void takeSilver(Player player, int i) {

        m.takeSilver(player, i);
        player.sendMessage(ChatColor.GOLD + "You now have  " + m.money.get(player.getUniqueId().toString()));

    }
    
    Map<ItemStack, Integer> materials = new HashMap<>();
    
    public void sellMaterial(Player player,ItemStack item,int number,int price){
        
        player.getInventory().addItem(item);
        takeSilver(player, price);
        
        materials.put(new ItemStack(Material.BOOKSHELF),100);
    }

}
