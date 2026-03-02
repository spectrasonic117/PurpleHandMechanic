package com.spectrasonic.PoppyGrabPacks.Items;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ItemsAdderUtils {

    // IDs de los bloques personalizados en ItemsAdder
    public static final String PURPLE_BLOCK_ID = "poppy:purple_block";
    public static final String YELLOW_BLOCK_ID = "poppy:yellow_block";
    public static final String GREEN_BLOCK_ID = "poppy:green_block";

    // Verificar si el ItemStack es el orange_grabpack
    public static boolean isOrangeGrabPack(ItemStack itemStack) {
        if (itemStack == null)
            return false;
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null)
            return false;
        return customStack.getNamespacedID().equalsIgnoreCase(ItemsAdderItems.ORANGE_GRABPACK_ID);
    }

    // Verificar si el bloque clickeado es el purple_block usando CustomBlock
    public static boolean isPurpleBlock(Block block) {
        if (block == null)
            return false;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null)
            return false;
        return customBlock.getNamespacedID().equalsIgnoreCase(PURPLE_BLOCK_ID);
    }

    // Verificar si el bloque clickeado es el yellow_block usando CustomBlock
    public static boolean isYellowBlock(Block block) {
        if (block == null)
            return false;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null)
            return false;
        return customBlock.getNamespacedID().equalsIgnoreCase(YELLOW_BLOCK_ID);
    }

    // Verificar si el bloque clickeado es el green_block usando CustomBlock
    public static boolean isGreenBlock(Block block) {
        if (block == null)
            return false;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null)
            return false;
        return customBlock.getNamespacedID().equalsIgnoreCase(GREEN_BLOCK_ID);
    }
}
