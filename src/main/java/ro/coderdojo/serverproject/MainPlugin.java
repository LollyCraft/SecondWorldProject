package ro.coderdojo.serverproject;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;


public class MainPlugin extends JavaPlugin {

    public static World lobby;
    public static World arena;
    public static World second_world;
    public static World archery;

    
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
                loadSecondWorld();
                loadArchery();
                
                getServer().getPluginManager().registerEvents(new EventsListener(lobby,arena,second_world), this);
                getServer().getPluginManager().registerEvents(new ArenaListener(arena), this);
                getServer().getPluginManager().registerEvents(new LobbyListener(lobby,arena,second_world,archery), this);
                getServer().getPluginManager().registerEvents(new SecondWorldListener(second_world), this);
                getServer().getPluginManager().registerEvents(new ArcheryListener(archery), this);
                getServer().getPluginManager().registerEvents(Money.getInstance(), this);

                                //Register Command Executors
		this.getCommand("CoderDojo").setExecutor(new CoderDojoCommand());
                this.getCommand("hub").setExecutor(new CommandHub());
                this.getCommand("moneyBank").setExecutor(new CommandMoney());
                this.getCommand("teleportToPlot").setExecutor(new CommandPlot());
                this.getCommand("teleportToShop").setExecutor(new CommandShop());
                this.getCommand("moneyHelp").setExecutor(new CommandHelpMoney());
               
                        
                killAllMobs();
                
                
    }
    
     public void unzip() throws Exception {
        
            UnZip.unzip(this.getServer());
        
    }
    
    public void loadLobby(){
          lobby = Bukkit.getServer().createWorld(new WorldCreator("world_lobby"));
          lobby.setPVP(false);
          lobby.setGameRuleValue("doDaylightCycle", "false");       
          stdGameRules(lobby);
    }
    
    public void loadArena(){
           arena = Bukkit.getServer().createWorld(new WorldCreator("Arena_map"));
           stdGameRules(arena);
           arena.setGameRuleValue("naturalRegeneration", "false");
           lobby.setGameRuleValue("doDaylightCycle", "false");
    }
    
    public void loadSecondWorld(){
           second_world = Bukkit.getServer().createWorld(new WorldCreator("Second_world"));
           second_world.setPVP(false);

           stdGameRules(second_world);
    }
    
    public void loadArchery(){
          archery = Bukkit.getServer().createWorld(new WorldCreator("Archery_map"));
          archery.setPVP(false);
          archery.setGameRuleValue("doDaylightCycle", "false");       
          stdGameRules(archery);
    }

    private void killAllMobs() {
        lobby.getEntities().forEach((e) -> {
            e.remove();
        });
        arena.getEntities().forEach((e) -> {
            e.remove();
        });
        second_world.getEntities().forEach((e) -> {
            e.remove();
        });
    }
    
    public void stdGameRules(World world){
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("doMobLoot", "false");
        world.setGameRuleValue("fallDamage", "false"); 
        world.setGameRuleValue("doWeatherCycle", "false");
        world.setStorm(false);
        world.setGameRuleValue("keepInventory", "true");
    }
    

}
