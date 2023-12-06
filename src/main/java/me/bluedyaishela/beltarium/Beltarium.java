package me.bluedyaishela.beltarium;

import me.bluedyaishela.beltarium.events.BeltariumListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Beltarium extends JavaPlugin {

    private static Beltarium plugin;
    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists())
        {
            this.getLogger().info("Le fichier config.yml n'a pas été trouvé, création en cours...");
            this.saveDefaultConfig();
        } else {
            this.getLogger().info("Le fichier de configuration a été trouvé, chargement en cours...");
        }
        FileConfiguration config = this.getConfig();

        plugin = this;

        Tools.init();
        ItemManager.init();

        this.getCommand("beltarium").setExecutor(new Commands());
        this.RegisterEvents();
    }

    public void RegisterEvents()
    {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BeltariumListener(plugin), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
