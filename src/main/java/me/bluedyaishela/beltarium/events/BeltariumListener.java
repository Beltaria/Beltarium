package me.bluedyaishela.beltarium.events;

import me.bluedyaishela.beltarium.Beltarium;
import me.bluedyaishela.beltarium.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class BeltariumListener implements Listener {

    private final String BELTARIUM_HELMET_LORE = "Haute température";
    private final String BELTARIUM_CHESTPLATE_LORE = "Peau de fer";
    private final String BELTARIUM_LEGGINGS_LORE = "Vitesse renforcée";
    private final String BELTARIUM_BOOTS_LORE = "Chute amortie";

    static Set<Player> FullBeltariumList = new LinkedHashSet<>();

    private final Beltarium plugin;

    public BeltariumListener(Beltarium plugin) {
        this.plugin = plugin;
    }

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

    @EventHandler
    public void onPlayerInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player)event.getPlayer();

            Bukkit.getScheduler().runTaskLater((Plugin) plugin, new Runnable() {
                @Override
                public void run() {
                    ApplyInvisibilityEffect(player);
                }
            }, 1L);
        }
    }

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getBrokenItem();

        if (Items.hasLore(BELTARIUM_HELMET_LORE, itemStack) || Items.hasLore(BELTARIUM_CHESTPLATE_LORE, itemStack)
                || Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack) || Items.hasLore(BELTARIUM_BOOTS_LORE, itemStack)
        ) {
            Beltarium.getFullBeltariumList().remove(player);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        if (Items.hasLore(BELTARIUM_HELMET_LORE, helmet) && Items.hasLore(BELTARIUM_CHESTPLATE_LORE, chestplate)
                && Items.hasLore(BELTARIUM_LEGGINGS_LORE, leggings) && Items.hasLore(BELTARIUM_BOOTS_LORE, boots)
        ) {
            FullBeltariumList.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 0, true, true));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        FullBeltariumList.remove(player);
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }

    @EventHandler
    public void onConsumingPotion(PlayerItemConsumeEvent event)
    {
        ItemStack itemStack = event.getItem();
        if (itemStack.getType() == Material.POTION)
        {
            if (itemStack.getDurability() == 8234 || itemStack.getDurability() == 8266)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onGettingPotionEffect(PotionSplashEvent event)
    {
        ThrownPotion thrownPotion = event.getPotion();
        for (PotionEffect effect : thrownPotion.getEffects())
        {
            if (effect.getType().getName().equals("SLOW"))
            {
                for (LivingEntity entity : event.getAffectedEntities())
                {
                    ItemStack itemStack = entity.getEquipment().getLeggings();
                    if (Items.hasLore(BELTARIUM_LEGGINGS_LORE, itemStack))
                    {
                        event.setCancelled(true);
                    } else {
                        for (PotionEffect potionEffect : entity.getActivePotionEffects())
                        {
                            if (potionEffect.getType().getName().equals("SLOW"))
                            {
                                if (potionEffect.getDuration() < effect.getDuration())
                                {
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    entity.addPotionEffect(new PotionEffect(effect.getType(), effect.getDuration(), effect.getAmplifier(), true));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
        if (!(event.getDamager() instanceof Player)) return;
        Player damagerPlayer = (Player) event.getDamager();

        ItemStack itemStack = damagerPlayer.getItemInHand();
        if (itemStack != null && itemStack.getType() == Material.DIAMOND_SWORD)
        {
            if (Items.hasLore("Force herculéenne", itemStack))
            {
                Random random = new Random();
                int randomValue = random.nextInt(101);
                if (randomValue < 10)
                {
                    double newDamage = event.getDamage() * 1.5;
                    event.setDamage(newDamage);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();

        Bukkit.getScheduler().runTaskLater((Plugin) plugin, new Runnable() {
            @Override
            public void run() {
                ApplyInvisibilityEffect(player);
            }
        }, 1L);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater((Plugin) plugin, new Runnable() {
            @Override
            public void run() {
                ApplyInvisibilityEffect(player);
            }
        }, 1L);
    }

    private void ApplyInvisibilityEffect(Player player)
    {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        if (Items.hasLore(BELTARIUM_HELMET_LORE, helmet) && Items.hasLore(BELTARIUM_CHESTPLATE_LORE, chestplate)
                && Items.hasLore(BELTARIUM_LEGGINGS_LORE, leggings) && Items.hasLore(BELTARIUM_BOOTS_LORE, boots))
        {
            FullBeltariumList.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 0, true, true));
        } else if (FullBeltariumList.contains(player)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            FullBeltariumList.remove(player);
        }
    }
}
