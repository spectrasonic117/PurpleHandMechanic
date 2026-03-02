package com.spectrasonic.PoppyGrabPacks.Items.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public class PurpleGrabpackItem {

    public static final String ID = "poppy:purple_grabpack";

    public static CustomStack getCustomStack() {
        return CustomStack.getInstance(ID);
    }

    public static ItemStack getItemStack() {
        CustomStack stack = getCustomStack();
        return stack != null ? stack.getItemStack() : null;
    }

    public static boolean isRegistered() {
        return CustomStack.isInRegistry(ID);
    }

    public static boolean isItem(ItemStack itemStack) {
        if (itemStack == null) return false;
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null) return false;
        return customStack.getNamespacedID().equalsIgnoreCase(ID);
    }
}
