package me.senkoco.townyspawnmenu.commands.sub;

import org.bukkit.entity.Player;

public class DefaultSubCommand {
    public static boolean execute(Player player, String label) {
        player.sendMessage("§6[Towny Spawn Menu] §c用法: /" + label + " <menu/config/info/hide>\n§c<> = 必填");
        return true;
    }
}
