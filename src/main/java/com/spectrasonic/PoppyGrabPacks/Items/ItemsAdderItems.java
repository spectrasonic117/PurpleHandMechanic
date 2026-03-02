package com.spectrasonic.PoppyGrabPacks.Items;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public class ItemsAdderItems {

    // IDs de los items personalizados en ItemsAdder
    public static final String OMNIHAND_ID = "poppy:omnihand";
    public static final String PURPLE_GRABPACK_ID = "poppy:purple_grabpack";
    public static final String ORANGE_GRABPACK_ID = "poppy:orange_grabpack";
    public static final String MAGNETIC_GRABPACK_ID = "poppy:magnetic_grabpack";

    public static CustomStack getOmnihand() {
        return CustomStack.getInstance(OMNIHAND_ID);
    }

    public static CustomStack getPurpleGrabPack() {
        return CustomStack.getInstance(PURPLE_GRABPACK_ID);
    }

    public static CustomStack getOrangeGrabPack() {
        return CustomStack.getInstance(ORANGE_GRABPACK_ID);
    }

    public static CustomStack getMagneticGrabPack() {
        return CustomStack.getInstance(MAGNETIC_GRABPACK_ID);
    }

    public static ItemStack getOmnihandItemStack() {
        CustomStack stack = getOmnihand();
        return stack != null ? stack.getItemStack() : null;
    }

    public static ItemStack getPurpleGrabPackItemStack() {
        CustomStack stack = getPurpleGrabPack();
        return stack != null ? stack.getItemStack() : null;
    }

    public static ItemStack getOrangeGrabPackItemStack() {
        CustomStack stack = getOrangeGrabPack();
        return stack != null ? stack.getItemStack() : null;
    }

    public static ItemStack getMagneticGrabPackItemStack() {
        CustomStack stack = getMagneticGrabPack();
        return stack != null ? stack.getItemStack() : null;
    }

    public static boolean isOmnihandRegistered() {
        return CustomStack.isInRegistry(OMNIHAND_ID);
    }

    public static boolean isPurpleGrabPackRegistered() {
        return CustomStack.isInRegistry(PURPLE_GRABPACK_ID);
    }

    public static boolean isOrangeGrabPackRegistered() {
        return CustomStack.isInRegistry(ORANGE_GRABPACK_ID);
    }

    public static boolean isMagneticGrabPackRegistered() {
        return CustomStack.isInRegistry(MAGNETIC_GRABPACK_ID);
    }
}
