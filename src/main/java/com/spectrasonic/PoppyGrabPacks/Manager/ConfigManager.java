package com.spectrasonic.PoppyGrabPacks.Manager;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Getter
public class ConfigManager {

    private static ConfigManager instance;
    private final JavaPlugin plugin;
    private FileConfiguration config;

    // Purple GrabPack - Parametros de velocidad
    @Getter
    private double purpleForwardForce;
    @Getter
    private double purpleUpwardForce;

    private ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public static ConfigManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
        return instance;
    }

    public void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        loadPurpleGrabPackConfig();
    }

    public void reloadConfig() {
        loadConfig();
        plugin.getLogger().info("Configuración recargada correctamente");
    }

    private void loadPurpleGrabPackConfig() {
        // Cargar valores con valores por defecto
        purpleForwardForce = getConfig().getDouble("purple_grabpack.forward_force", 0.3);
        purpleUpwardForce = getConfig().getDouble("purple_grabpack.upward_force", 0.9);
    }

    public void saveConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("No se pudo guardar la configuración: " + e.getMessage());
        }
    }
}
