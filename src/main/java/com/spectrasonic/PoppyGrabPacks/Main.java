package com.spectrasonic.PoppyGrabPacks;

import com.spectrasonic.PoppyGrabPacks.Manager.ListenerManager;
import com.spectrasonic.PoppyGrabPacks.Utils.MessageUtils;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

        // Registrar evento de ItemsAdder para recargar cuando se haga /iareload
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onItemsAdderLoad(ItemsAdderLoadDataEvent event) {
                getLogger().info("ItemsAdder ha cargado los datos - Items personalizados listos");
            }
        }, this);
    }
}
