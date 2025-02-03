package com.spectrasonic.PurpleHandMechanic;

import com.spectrasonic.PurpleHandMechanic.Listeners.PlayerInteractListener;
import com.spectrasonic.PurpleHandMechanic.Utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        registerCommands();
        registerEvents();
        MessageUtils.sendStartupMessage(this);

    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
        MessageUtils.sendVeiMessage(this);
    }

    public void registerCommands() {
        // Set Commands Here
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }
}
