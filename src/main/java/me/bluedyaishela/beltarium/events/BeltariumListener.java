package me.bluedyaishela.beltarium.events;

import me.bluedyaishela.beltarium.Beltarium;
import me.bluedyaishela.beltarium.utils.Items;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class BeltariumListener implements Listener {

    private final String BELTARIUM_HELMET_LORE = "Haute température";
    private final String BELTARIUM_CHESTPLATE_LORE = "Peau de fer";
    private final String BELTARIUM_LEGGINGS_LORE = "Vitesse améliorée";
    private final String BELTARIUM_BOOTS_LORE = "Chute amortie";

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event)
    {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        EntityDamageEvent.DamageCause cause = event.getCause();

        if (cause == EntityDamageEvent.DamageCause.FALL) {
            if (Items.hasLore(BELTARIUM_BOOTS_LORE, new ItemStack(player.getInventory().getBoots())))
                event.setCancelled(true);
        }

        List<EntityDamageEvent.DamageCause> causesToCancel = Arrays.asList(
                EntityDamageEvent.DamageCause.FIRE_TICK,
                EntityDamageEvent.DamageCause.LAVA,
                EntityDamageEvent.DamageCause.FIRE
        );

        if (causesToCancel.contains(cause)) {
            if(Items.hasLore(BELTARIUM_HELMET_LORE, new ItemStack(player.getInventory().getHelmet())))
                event.setCancelled(true);
        }

        if (cause == EntityDamageEvent.DamageCause.PROJECTILE) {
            if(Items.hasLore(BELTARIUM_CHESTPLATE_LORE, new ItemStack(player.getInventory().getChestplate())))
                event.setCancelled(true);
        }
    }

    /*
        Pantalon effet Speed
     */

    @EventHandler
    public void onPlayerInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player)event.getPlayer();
            ItemStack itemStack = player.getInventory().getLeggings();
            if(Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack))
            {
                Beltarium.getLeggingsList().add(player);
            } else {
                Beltarium.getLeggingsList().remove(player);
            }

            ItemStack helmet = player.getInventory().getHelmet();
            ItemStack chestplate = player.getInventory().getChestplate();
            ItemStack boots = player.getInventory().getBoots();

            if (Items.hasLore(BELTARIUM_HELMET_LORE, helmet) && Items.hasLore(BELTARIUM_CHESTPLATE_LORE, chestplate)
                    && Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack) && Items.hasLore(BELTARIUM_BOOTS_LORE, boots)
            ) {
                Beltarium.getFullBeltariumList().add(player);
            } else {
                Beltarium.getFullBeltariumList().remove(player);
            }
        }
    }

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getBrokenItem();
        if (Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack)) Beltarium.getLeggingsList().remove(player);
        Beltarium.getFullBeltariumList().remove(player);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getLeggings();
        if (Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack)) Beltarium.getLeggingsList().add(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        Beltarium.getLeggingsList().remove(player);
    }

    private void fullArmorInvisibility()
    {

    }

}
