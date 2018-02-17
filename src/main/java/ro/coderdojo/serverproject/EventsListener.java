package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


public final class EventsListener implements Listener {
    
    World lobby;
    World arena;



        public EventsListener(World lobby,World arena){
            this.lobby = lobby;
            this.arena = arena;
        }

    
        
	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
                player.getInventory().clear();
		player.sendMessage("Salut " + ChatColor.AQUA + player.getName() + ChatColor.WHITE + "! Felicitări pentru primul mod de Minecraft!");
                
                player.teleport(new Location(lobby, -1522.666, 111.00000, 683.227, -91.6f, 2.1f));
                // x,y,z,f -> facing
                
                AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		healthAttribute.setBaseValue(20.00);
                player.setExhaustion(0);
                player.setFoodLevel(20);
	}
        
        
        @EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
            
		Player player = event.getPlayer();
                event.setRespawnLocation(new Location(lobby, -1522.666, 111.00000, 683.227, -91.6f, 2.1f));
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
                AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		healthAttribute.setBaseValue(20.00);
                player.setExhaustion(0);
                player.setFoodLevel(20);
                
        }
        
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onBlockBreak(BlockBreakEvent event) {
//            event.getPlayer().sendMessage(ChatColor.YELLOW + " Nu poti sparge " + ChatColor.RED + "lobby-ul!");
            event.setCancelled(true);
        }
        
//        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args,PlayerEvent event) {
//            
//            Player player = event.getPlayer();
//            
//            if (cmd.getName().equalsIgnoreCase("hub")) { // If the player typed /hub then do the following, note: If you only registered this executor for one command, you don't need this
//		player.teleport(new Location(lobby, -1522.666, 111.00000, 683.227, -91.6f, 2.1f));
//		return true;
//	} //If this has happened the function will return true. 
//        // If this hasn't happened the value of false will be returned.
//	return false; 
//        }
        
        
        
        //        public void fillChest(){
//            
//            Location loc = new Location(arena,3.582,118.00000,121.700);
//        
//        arena.getBlockAt(loc).setType(Material.CHEST);
//        Block block = loc.getBlock();
//        Chest chest = (Chest)block.getState();
//        Inventory inv = chest.getInventory();
//       
//        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
//        ItemStack gold_boots = new ItemStack(Material.GOLD_BOOTS, 1);
//        ItemStack fish = new ItemStack(Material.COOKED_FISH, 1);
//        ItemStack golden_apple = new ItemStack(Material.GOLDEN_APPLE, 1);
//        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 2);
//       
//        inv.addItem(diamond_sword, gold_boots, fish, golden_apple, enderpearl);
//            
//        }
}
