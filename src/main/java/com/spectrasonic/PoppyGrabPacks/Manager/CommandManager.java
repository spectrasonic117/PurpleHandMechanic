package com.spectrasonic.PoppyGrabPacks.Manager;

import co.aikar.commands.BukkitCommandManager;
import com.spectrasonic.PoppyGrabPacks.Commands.GrabPacksCommand;
import com.spectrasonic.PoppyGrabPacks.Main;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandManager {

    private final Main plugin;
    private BukkitCommandManager commandManager;

    public void registerCommands() {
        commandManager = new BukkitCommandManager(plugin);
        commandManager.registerDependency(ConfigManager.class, plugin.getConfigManager());
        commandManager.registerCommand(new GrabPacksCommand());
    }

    public BukkitCommandManager getCommandManager() {
        return commandManager;
    }
}