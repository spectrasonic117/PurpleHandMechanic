package com.spectrasonic.PurpleHandMechanic.Manager;

import com.spectrasonic.PurpleHandMechanic.Listeners.MagneticGrabPackListener;
import com.spectrasonic.PurpleHandMechanic.Listeners.OrangeGrapPackListener;
import com.spectrasonic.PurpleHandMechanic.Listeners.PurpleGrapPackListener;
import com.spectrasonic.PurpleHandMechanic.Main;

public class ListenerManager {

    private final Main plugin;

    public ListenerManager(Main plugin) {
        this.plugin = plugin;
    }

    public void registerListener() {
        plugin.getServer().getPluginManager().registerEvents(new PurpleGrapPackListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OrangeGrapPackListener(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MagneticGrabPackListener(), plugin);
    }
}
