package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
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

            if (action == Action.RIGHT_CLICK_BLOCK && (block.getType().equals(Material.SIGN_POST) || block.getType().equals(Material.WALL_SIGN))) 
                sellWhichMaterial(player,block); 
        }
    }
    
    //-----------------------------------------------------------------------------
    
    public void sellWhichMaterial(Player player,Block block){
        Sign sign = (Sign) block.getState();
        String material = sign.getLine(0);
        switch (material){
            case "oak wood":
                sellMaterial(player,Material.LOG,64,200);
                break;
            case "bookshelf":
                sellMaterial(player,Material.BOOKSHELF,64,100);
                break;
                //nu esista alt fel de wood
            case "jungle wood":
                sellMaterial(player,Material.LOG_2,64,100);
                break;
            case "brich wood":
                sellMaterial(player,Material.LOG,64,200);
                break;
            case "spruce wood":
                sellMaterial(player,Material.LOG,64,200);
                break;
            case "acacia wood":
                sellMaterial(player,Material.LOG,64,200);
                break;
            case "dark oak wood":
                sellMaterial(player,Material.LOG,64,200);
                break;
            case "brick":
                sellMaterial(player,Material.BRICK,20,300);
                break;
            case "obsidian":
                sellMaterial(player,Material.OBSIDIAN,10,500);
                break;
            case "snow":
                sellMaterial(player,Material.SNOW_BLOCK,20,200);
                break;
            case "pumpkin":
                sellMaterial(player,Material.PUMPKIN,1,50);
                break;
            case "melon":
                sellMaterial(player,Material.MELON,1,50);
                break;
            case "netherrack":
                sellMaterial(player,Material.NETHERRACK,30,300);
                break;
            case "soulsand":
                sellMaterial(player,Material.SOUL_SAND,10,300);
                break;
            case "glowstone":
                sellMaterial(player,Material.GLOWSTONE,64,200);
                break;
            case "quartz":
                sellMaterial(player,Material.QUARTZ_BLOCK,20,200);
                break;
            case "nether brick":
                sellMaterial(player,Material.NETHER_BRICK,20,300);
                break;
            case "end stone":
                sellMaterial(player,Material.ENDER_STONE,20,400);
                break;
            case "purpur block":
                sellMaterial(player,Material.PURPUR_BLOCK,10,500);
                break;
            case "purpur pillar":
                sellMaterial(player,Material.PURPUR_PILLAR,10,600);
                break;
            case "stone":
                sellMaterial(player,Material.STONE,64,150);
                break;
            case "cobblestone":
                sellMaterial(player,Material.COBBLESTONE,64,100);
                break;
            case "torch":
                sellMaterial(player,Material.TORCH,20,150);
                break;
            case "gravel":
                sellMaterial(player,Material.GRAVEL,30,300);
                break;
            case "sand":
                sellMaterial(player,Material.SAND,64,200);
                break;
            case "red sandstone":
                sellMaterial(player,Material.RED_SANDSTONE,20,300);
                break;
            case "sea lantern":
                sellMaterial(player,Material.SEA_LANTERN,1,200);
                break;
            case "prismarine":
                sellMaterial(player,Material.PRISMARINE,10,300);
                break;
            case "redstone":
                sellMaterial(player,Material.REDSTONE,64,200);
                break;
            case "redstone torch":
                sellMaterial(player,Material.REDSTONE_TORCH_ON,10,200);
                break;
            case "bone":
                sellMaterial(player,Material.BONE,5,100);
                break;
             case "bone block":
                sellMaterial(player,Material.BONE_BLOCK,10,400);
                break;
             case "cactus":
                sellMaterial(player,Material.CACTUS,1,150);
                break;
             case "anvil":
                sellMaterial(player,Material.ANVIL,1,200);
                break;
             case "ender chest":
                sellMaterial(player,Material.ENDER_CHEST,1,300);
                break;
              case "chest":
                sellMaterial(player,Material.CHEST,1,20);
                break;
              case "enchantment":
                sellMaterial(player,Material.ENCHANTMENT_TABLE,1,200);
                break;
                  
        }                
    }

    //---------------------------------------------------------------------------------------
    
    public void takeSilver(Player player, int i) {

        m.takeSilver(player, i);
        player.sendMessage(ChatColor.GOLD + "You now have  " + m.money.get(player.getUniqueId().toString()));

    }
    
    public void sellMaterial(Player player,Material item,int number,int price){
        
        if (m.money.get(player.getUniqueId().toString()) >= price) {
            player.sendMessage(ChatColor.GOLD+ "You have bought " + item);
            for(int i = 1;i<=number;i++){
            player.getInventory().addItem(new ItemStack(item)); 
            }
            takeSilver(player, price);
        }else{
            player.sendMessage(ChatColor.RED + "Not enough money");
            player.sendMessage(ChatColor.RED + "You have just " + m.money.get(player.getUniqueId().toString()));
        }

    }

}
