
package ro.coderdojo.serverproject;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;

public class ArcheryListener implements Listener {
    
    public World archery;

    public ArcheryListener(World archery) {
        this.archery = archery;
        floatingText();
    }
    
        @EventHandler
	public void onJoin(PlayerChangedWorldEvent event) throws Exception {
		Player player = event.getPlayer();
                
                if(player.getWorld() != archery) return;
		
                player.getInventory().clear();
                
                ItemStack bow = new ItemStack(Material.BOW, 1);
                bow.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
                player.getInventory().addItem(bow);
                player.setAllowFlight(false);
                
        }
        
        List<ArmorStand> floatingTexts = new ArrayList();
        
        private void floatingText(){
            Location loc1 = new Location(archery,69,220.00000,22);
            
            ArmorStand entity1 = (ArmorStand) archery.spawnEntity(loc1, EntityType.ARMOR_STAND);
            entity1.setCustomName(ChatColor.GOLD + "-- How to play --");
            floatingTexts.add(entity1);

            ArmorStand  entity2 = (ArmorStand) archery.spawnEntity(loc1.subtract(0,0.5,0), EntityType.ARMOR_STAND);
            entity2.setCustomName(ChatColor.GREEN+ "1. Kill the animals from the cart to get money.");
            floatingTexts.add(entity2);

            ArmorStand  entity3 = (ArmorStand) archery.spawnEntity(loc1.subtract(0,0.8,0), EntityType.ARMOR_STAND);
            entity3.setCustomName(ChatColor.GREEN+"2. Each animal gives a different amount of money");
            floatingTexts.add(entity3);
            
            for(ArmorStand e : floatingTexts){
                e.setVisible(false);
                e.setCustomNameVisible(true);
                e.setInvulnerable(true);
                e.setSilent(true);
                e.setGravity(false);
            }
        }
        
        //------------------------------------------------------------------------------------------
        
    @EventHandler  
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld() != archery) {
            return;
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();

            if (action == Action.RIGHT_CLICK_BLOCK && (block.getType().equals(Material.SIGN_POST) || block.getType().equals(Material.WALL_SIGN))){
                sellArrows(player,20); 
            }else{
                    return;
                }
    }
    
    Money m = Money.getInstance();
    
    public void takeSilver(Player player, int i) {

        m.takeSilver(player, i);
        player.sendMessage(ChatColor.GOLD + "You now have  " + m.money.get(player.getUniqueId().toString()));

    }
    
    public void sellArrows(Player player,int price){
        
        if (m.money.get(player.getUniqueId().toString()) >= price) {
            player.sendMessage(ChatColor.GOLD+ "You have bought 10 arrows");
            for(int i = 1;i<=10;i++){
            player.getInventory().addItem(new ItemStack(Material.ARROW)); 
            }
            takeSilver(player, price);
        }else{
            player.sendMessage(ChatColor.RED + "Not enough money");
            player.sendMessage(ChatColor.RED + "You have just " + m.money.get(player.getUniqueId().toString()));
        }

    }
    
    //--------------------------------------------------------------------------------------
    @EventHandler
    public void cancelDestroy(VehicleDestroyEvent event){
        event.setCancelled(true);
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity target = event.getEntity();
        
        if(target.getWorld() != archery) return;
        
        if (target instanceof Pig) {  //merge doar pt pig!!!!!!!!!!!!!!!!!!!!
            if (event.getEntity().getKiller() instanceof Player) {
                Player p = event.getEntity().getKiller();
                giveSilver(p,100,target);
            }
        } else if (target instanceof Sheep) {
            if (event.getEntity().getKiller() instanceof Player) {
                Player p = event.getEntity().getKiller();
                giveSilver(p,50,target);
            }
        }else if (target instanceof Cow) {
            if (event.getEntity().getKiller() instanceof Player) {
                Player p = event.getEntity().getKiller();
                giveSilver(p,70,target);
            }
        }
    }
    
    public void giveSilver(Player player, int money,LivingEntity target) {

        m.giveSilver(player, money);
        player.sendMessage(ChatColor.GOLD+ "Recieved "+money+" coins");
        
        target.getVehicle().remove();
        spawnTarget(target,money);
    }
    
    Location loc = new Location(archery,79.700,221.00000,7);
    
    public void spawnTarget(LivingEntity target,int money){
        LivingEntity entity = (LivingEntity) archery.spawnEntity(loc,target.getType());
        target.setCustomName(money+" coins");
        target.setCustomNameVisible(true);
        
        Minecart minecartTarget = archery.spawn(loc, Minecart.class);
        minecartTarget.setPassenger(entity);
        
    }

}
