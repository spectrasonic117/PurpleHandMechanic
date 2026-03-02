package com.spectrasonic.PoppyGrabPacks.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import com.spectrasonic.PoppyGrabPacks.Manager.ConfigManager;
import com.spectrasonic.PoppyGrabPacks.Utils.MessageUtils;
import org.bukkit.command.CommandSender;

@CommandAlias("grabpacks")
@CommandPermission("poppygrabpacks.admin")
public class GrabPacksCommand extends BaseCommand {

    @Dependency
    private ConfigManager configManager;

    @Default
    public void onDefault(CommandSender sender) {
        MessageUtils.sendMessage(sender, "<yellow>Uso: /grabpacks reload</yellow>");
    }

    @Subcommand("reload")
    public void onReload(CommandSender sender) {
        configManager.reloadConfig();
        MessageUtils.sendMessage(sender, "<green>Configuración recargada</green>");
    }
}
