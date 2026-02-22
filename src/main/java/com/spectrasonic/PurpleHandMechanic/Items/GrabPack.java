package com.spectrasonic.PurpleHandMechanic.Items;

import net.kyori.adventure.text.Component;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GrabPack {
    private final ItemStack item;

    public GrabPack(String name, int customModelData, Material material) {
        this.item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isSimilar(ItemStack item) {
        if (item == null || !item.hasItemMeta())
            return false;
        ItemMeta meta = item.getItemMeta();
        return meta.hasCustomModelData() &&
                meta.getCustomModelData() == this.item.getItemMeta().getCustomModelData();
    }
}
