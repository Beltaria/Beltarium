package me.bluedyaishela.beltarium.entities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BeltariumItemCreation {

    private Material material;
    private String displayName;
    private List<String> loreList;
    private List<BeltariumEnchantmentCreation> enchantmentList;

    public BeltariumItemCreation() { }

    public ItemStack getBeltariumItem(Material material, String displayName, List<String> loreList, List<BeltariumEnchantmentCreation> enchantmentList)
    {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(loreList);
        for (BeltariumEnchantmentCreation enchantment : enchantmentList)
        {
            itemMeta.addEnchant(enchantment.getEnchantment(), enchantment.getEnchantmentLevel(), enchantment.isVisible());
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
