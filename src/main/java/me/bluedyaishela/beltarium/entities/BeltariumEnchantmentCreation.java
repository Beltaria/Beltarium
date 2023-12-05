package me.bluedyaishela.beltarium.entities;

import org.bukkit.enchantments.Enchantment;

public class BeltariumEnchantmentCreation {

    private Enchantment enchantment;
    private Integer enchantmentLevel;
    private boolean isVisible;

    public BeltariumEnchantmentCreation(Enchantment enchantment, Integer enchantmentLevel, boolean isVisible) {
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        this.isVisible = isVisible;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public Integer getEnchantmentLevel() {
        return enchantmentLevel;
    }

    public void setEnchantmentLevel(Integer enchantmentLevel) {
        this.enchantmentLevel = enchantmentLevel;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
