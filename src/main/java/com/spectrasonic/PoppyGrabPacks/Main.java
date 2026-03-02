package com.spectrasonic.PoppyGrabPacks;

import co.aikar.commands.BukkitCommandManager;
import com.spectrasonic.PoppyGrabPacks.Commands.GrabPacksCommand;
import com.spectrasonic.PoppyGrabPacks.Manager.ConfigManager;
import com.spectrasonic.PoppyGrabPacks.Manager.ListenerManager;
import com.spectrasonic.PoppyGrabPacks.Utils.MessageUtils;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import lombok.Getter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    private ConfigManager configManager;
    private BukkitCommandManager commandManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        configManager = ConfigManager.getInstance(this);
        registerCommands();
        registerEvents();
        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    public void registerEvents() {
        new ListenerManager(this).registerListener();

        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onItemsAdderLoad(ItemsAdderLoadDataEvent event) {
                getLogger().info("ItemsAdder: Items listos");
            }
        }, this);
    }

    public void registerCommands() {
        commandManager = new BukkitCommandManager(this);

        commandManager.registerCommand(new GrabPacksCommand());

    }
}
