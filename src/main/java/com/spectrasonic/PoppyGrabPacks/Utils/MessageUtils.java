package com.spectrasonic.PoppyGrabPacks.Utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class MessageUtils {

    public static final String DIVIDER = "<gray>----------------------------------------</gray>";
    public static final String PREFIX = "<dark_gray>[<gradient:#3ff5cf:#3fd2f5>PoppyGrabPacks</gradient>]</dark_gray> <green>»</green> ";

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(miniMessage.deserialize(PREFIX + message));
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(PREFIX + message));
    }

    public static void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage(
                miniMessage.deserialize(PREFIX + "<red>You do not have permission to use this command!</red>"));
    }

    public static void sendStartupMessage(JavaPlugin plugin) {
        String[] messages = {
                DIVIDER,
                PREFIX + "<white>" + plugin.getPluginMeta().getName() + "</white> <green>Plugin Enabled!</green>",
                PREFIX + "<light_purple>Version: </light_purple>" + plugin.getPluginMeta().getVersion(),
                PREFIX + "<white>Developed by: </white><red>" + plugin.getPluginMeta().getAuthors() + "</red>",
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendVeiMessage(JavaPlugin plugin) {
        String[] messages = {
                PREFIX + "⣇⣿⠘⣿⣿⣿⡿⡿⣟⣟⢟⢟⢝⠵⡝⣿⡿⢂⣼⣿⣷⣌⠩⡫⡻⣝⠹⢿⣿⣷",
                PREFIX + "⡆⣿⣆⠱⣝⡵⣝⢅⠙⣿⢕⢕⢕⢕⢝⣥⢒⠅⣿⣿⣿⡿⣳⣌⠪⡪⣡⢑⢝⣇",
                PREFIX + "⡆⣿⣿⣦⠹⣳⣳⣕⢅⠈⢗⢕⢕⢕⢕⢕⢈⢆⠟⠋⠉⠁⠉⠉⠁⠈⠼⢐⢕⢽",
                PREFIX + "⡗⢰⣶⣶⣦⣝⢝⢕⢕⠅⡆⢕⢕⢕⢕⢕⣴⠏⣠⡶⠛⡉⡉⡛⢶⣦⡀⠐⣕⢕",
                PREFIX + "⡝⡄⢻⢟⣿⣿⣷⣕⣕⣅⣿⣔⣕⣵⣵⣿⣿⢠⣿⢠⣮⡈⣌⠨⠅⠹⣷⡀⢱⢕",
                PREFIX + "⡝⡵⠟⠈⢀⣀⣀⡀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣼⣿⢈⡋⠴⢿⡟⣡⡇⣿⡇⡀⢕",
                PREFIX + "⡝⠁⣠⣾⠟⡉⡉⡉⠻⣦⣻⣿⣿⣿⣿⣿⣿⣿⣿⣧⠸⣿⣦⣥⣿⡇⡿⣰⢗⢄",
                PREFIX + "⠁⢰⣿⡏⣴⣌⠈⣌⠡⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣉⣉⣁⣄⢖⢕⢕⢕",
                PREFIX + "⡀⢻⣿⡇⢙⠁⠴⢿⡟⣡⡆⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣵⣵⣿",
                PREFIX + "⡻⣄⣻⣿⣌⠘⢿⣷⣥⣿⠇⣿⣿⣿⣿⣿⣿⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                PREFIX + "⣷⢄⠻⣿⣟⠿⠦⠍⠉⣡⣾⣿⣿⣿⣿⣿⣿⢸⣿⣦⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟",
                PREFIX + "⡕⡑⣑⣈⣻⢗⢟⢞⢝⣻⣿⣿⣿⣿⣿⣿⣿⠸⣿⠿⠃⣿⣿⣿⣿⣿⣿⡿⠁⣠",
                PREFIX + "⡝⡵⡈⢟⢕⢕⢕⢕⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⠿⠋⣀⣈⠙",
                PREFIX + "⡝⡵⡕⡀⠑⠳⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⢉⡠⡲⡫⡪⡪⡣",
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendBroadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(miniMessage.deserialize(message));
        }
    }

    public static void sendShutdownMessage(JavaPlugin plugin) {
        String[] messages = {
                DIVIDER,
                PREFIX + "<red>" + plugin.getPluginMeta().getName() + " plugin Disabled!</red>",
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(message));
        }
    }
}
