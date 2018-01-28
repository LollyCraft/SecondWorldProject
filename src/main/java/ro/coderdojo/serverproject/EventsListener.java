package ro.coderdojo.serverproject;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (event.getClickedBlock() == null) {
			return;
		}
		Material material = event.getClickedBlock().getState().getType();
		Location location = event.getClickedBlock().getState().getLocation();
		if (action == Action.RIGHT_CLICK_BLOCK && material == Material.STONE_BUTTON) {
			System.out.println("Click: " + location);
			Location button1 = new Location(MainPlugin.lobby, -1490, 109.0, 683.0, location.getYaw(), location.getPitch());
			if (location.equals(button1)) {                   
                            player.teleport(new Location(arena, -344.613,4.00000,28.350));
//                            player.teleport(new Location(arena, 3.882, 118.00000, 117.717, 1.7f, 4.8f));//coord pt SkyWars_Map original
                            
			}
		}
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
}
