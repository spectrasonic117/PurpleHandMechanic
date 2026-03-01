package com.spectrasonic.PoppyGrabPacks;

import com.spectrasonic.PoppyGrabPacks.Manager.ListenerManager;
import com.spectrasonic.PoppyGrabPacks.Utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    public void registerEvents() {
        new ListenerManager(this).registerListener();
    }
}
