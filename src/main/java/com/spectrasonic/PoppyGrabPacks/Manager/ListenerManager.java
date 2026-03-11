package com.spectrasonic.PoppyGrabPacks.Manager;

import com.spectrasonic.PoppyGrabPacks.Listeners.BlueGrabPackListener;
import com.spectrasonic.PoppyGrabPacks.Listeners.OrangeGrapPackListener;
import com.spectrasonic.PoppyGrabPacks.Listeners.PurpleGrapPackListener;
import com.spectrasonic.PoppyGrabPacks.Main;

public class ListenerManager {

    private final Main plugin;

    public ListenerManager(Main plugin) {
        this.plugin = plugin;
    }

    public void registerListener() {
        // Listeners de GrabPacks
        plugin.getServer().getPluginManager().registerEvents(new PurpleGrapPackListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OrangeGrapPackListener(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlueGrabPackListener(), plugin);
    }
}
