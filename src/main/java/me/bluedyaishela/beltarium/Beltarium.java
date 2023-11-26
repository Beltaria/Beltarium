package me.bluedyaishela.beltarium;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Beltarium extends JavaPlugin {

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
