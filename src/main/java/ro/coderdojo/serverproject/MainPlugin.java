package ro.coderdojo.serverproject;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;


public class MainPlugin extends JavaPlugin {

    public static World lobby;
    public static World skywars;
    
    public static JavaPlugin plugin;

    
    
    
    @Override
    public void onEnable() {
                plugin = this;
                
        try {
            unzip();
        } catch (Exception ex) {
           throw new RuntimeException(ex);
        }
                
		loadLobby();             
                loadSkyWars();
                
                getServer().getPluginManager().registerEvents(new EventsListener(lobby,skywars), this);
                MainPlugin.plugin.getServer().getPluginManager().registerEvents(new SkyWarsListener(), this);
                
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
          
    }
    
    public void loadSkyWars(){
           skywars = Bukkit.getServer().createWorld(new WorldCreator("SkyWars_map"));
           skywars.setGameRuleValue("doMobSpawning", "false");
    }

    private void killAllMobs() {
        lobby.getEntities().forEach((e) -> {
            e.remove();
        });
        skywars.getEntities().forEach((e) -> {
            e.remove();
        });
    }
    
}
