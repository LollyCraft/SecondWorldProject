package ro.coderdojo.serverproject;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;


public class MainPlugin extends JavaPlugin {

    public static World lobby;
    public static World arena;
    
    public static JavaPlugin plugin;

    
    
    
    @Override
    public void onEnable() {
                plugin = this;
                
        try {
            unzip();
        } catch (Exception ex) {
//           throw new RuntimeException(ex);
			ex.printStackTrace();
        }
                
		loadLobby();             
                loadArena();
                
                getServer().getPluginManager().registerEvents(new EventsListener(lobby,arena), this);
                getServer().getPluginManager().registerEvents(new ArenaListener(arena), this);
                getServer().getPluginManager().registerEvents(new LobbyListener(lobby,arena), this);
//                getServer().getPluginManager().registerEvents(new LobbyListener(), this);

                                //Register Command Executors
		this.getCommand("CoderDojo").setExecutor(new CoderDojoCommand());
                
               
                        
                killAllMobs();
                

                
    }
    
     public void unzip() throws Exception {
        
            UnZip.unzip(this.getServer());
        
    }
    
    public void loadLobby(){
          lobby = Bukkit.getServer().createWorld(new WorldCreator("world_lobby"));
          lobby.setGameRuleValue("doMobSpawning", "false");
          lobby.setPVP(false);
          lobby.setStorm(false);
          
    }
    
    public void loadArena(){
           arena = Bukkit.getServer().createWorld(new WorldCreator("SkyWars_map"));
           arena.setGameRuleValue("doMobSpawning", "false");
    }

    private void killAllMobs() {
        lobby.getEntities().forEach((e) -> {
            e.remove();
        });
        arena.getEntities().forEach((e) -> {
            e.remove();
        });
    }
    
    
}
