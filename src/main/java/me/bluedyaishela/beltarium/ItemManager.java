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
        createBeltariumHelmet();
        createBeltariumChestplate();
        createBeltariumLeggings();
        createBeltariumBoots();
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

    private static void createBeltariumHelmet()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§4-= §cCasque en beltarium §4=-");

        List<String> lore = new ArrayList<>();
        lore.add("§cHaute température");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 4, true);
        itemMeta.addEnchant(Enchantment.OXYGEN, 3, true);
        itemMeta.addEnchant(Enchantment.WATER_WORKER, 1, true);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_HELMET = itemStack;
    }

    private static void createBeltariumChestplate()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§4-= §cPlastron en beltarium §4=-");

        List<String> lore = new ArrayList<>();
        lore.add("§cPeau de fer");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 4, true);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_CHESTPLATE = itemStack;
    }

    private static void createBeltariumLeggings()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§4-= §cJambières en beltarium §4=-");

        List<String> lore = new ArrayList<>();
        lore.add("§cVitesse améliorée");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 4, true);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_LEGGINGS = itemStack;
    }

    private static void createBeltariumBoots()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§4-= §cBottes en beltarium §4=-");

        List<String> lore = new ArrayList<>();
        lore.add("§cChute amortie");

        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 4, true);
        itemMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        itemStack.setItemMeta(itemMeta);
        BELTARIUM_BOOTS = itemStack;
    }

}
