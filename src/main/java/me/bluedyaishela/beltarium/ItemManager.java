package me.bluedyaishela.beltarium;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack BELTARIUM_NUGGET;
    public static ItemStack BELTARIUM_INGOT;
    public static ItemStack BELTARIUM_SWORD;
    public static ItemStack BELTARIUM_AXE;
    public static ItemStack BELTARIUM_PICKAXE;
    public static ItemStack BELTARIUM_SHOVEL;
    public static ItemStack BELTARIUM_HOE;
    public static ItemStack BELTARIUM_HELMET;
    public static ItemStack BELTARIUM_CHESTPLATE;
    public static ItemStack BELTARIUM_LEGGINGS;
    public static ItemStack BELTARIUM_BOOTS;

    public static void init()
    {
        createBeltariumNugget();
        createBeltariumIngot();
    }

    private static void createBeltariumNugget()
    {
        ItemStack itemStack = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§e-=- §6Fragment d'équipement §e-=-");

        List<String> lore = new ArrayList<>();
        lore.add("§fCe fragment est échangeable auprès");
        lore.add("§fd'un §bPNJ §fau §e/spawn");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.DURABILITY, 10, false);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_NUGGET = itemStack;
    }

    private static void createBeltariumIngot()
    {
        ItemStack itemStack = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§c-=- §4Fragment Légendaire §c-=-");

        List<String> lore = new ArrayList<>();
        lore.add("§fCe fragment est échangeable auprès");
        lore.add("§fd'un §bPNJ §fau §e/spawn");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.DURABILITY, 10, false);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_INGOT = itemStack;
    }

}
