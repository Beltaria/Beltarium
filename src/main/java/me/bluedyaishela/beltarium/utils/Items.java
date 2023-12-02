package me.bluedyaishela.beltarium.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class Items {
    public static boolean hasLore(String s, ItemStack itemStack) {
        if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
            Iterator<String> iterator = itemStack.getItemMeta().getLore().iterator();
            while (iterator.hasNext()) {
                String stripColor = ChatColor.stripColor(iterator.next());
                if (stripColor.contains(s))
                    return true;
            }
        }
        return false;
    }
}
