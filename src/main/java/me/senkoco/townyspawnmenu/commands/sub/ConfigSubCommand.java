package me.senkoco.townyspawnmenu.commands.sub;

import me.senkoco.townyspawnmenu.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ConfigSubCommand {
    private static final Main instance = Main.getInstance();
    public static List<String> autoComplete = Arrays.asList("default-item", "menu-filler", "war-item", "no-nation", "private");

    public static boolean execute(Player player, String[] args) {
        if(args.length == 1) {
            player.sendMessage("§6[Towny Spawn Menu] §c你必须提供一个选项！");
            return false;
        }else if(args.length == 2) {
            player.sendMessage("§6[Towny Spawn Menu] §c你必须提供一个物品！");
            return false;
        }
        String option = args[1].toLowerCase();
        String itemName = args[2].replace("minecraft:", "").toUpperCase();
        Material item = Material.getMaterial(itemName.toUpperCase());
        if(item == null) {
            player.sendMessage("§6[Towny Spawn Menu] §c你必须提供一个有效的物品！");
            return false;
        }

        switch(option) {
            case "default-item" -> {
                instance.getConfig().set("menu.defaultItem", itemName);
                instance.saveConfig();
                player.sendMessage("§6[Towny Spawn Menu] §3成功设置城镇的默认物品！");
                return true;
            }
            case "menu-filler" -> {
                instance.getConfig().set("menu.menuFiller", itemName);
                instance.saveConfig();
                player.sendMessage("§6[Towny Spawn Menu] §3成功设置用于填充空白空间的物品！");
                return true;
            }
            case "war-item" -> {
                instance.getConfig().set("menu.warItem", itemName);
                instance.saveConfig();
                player.sendMessage("§6[Towny Spawn Menu] §3成功设置处于战争状态的城镇的物品！");
                return true;
            }
            case "no-nation" -> {
                instance.getConfig().set("menu.noNationItem", itemName);
                instance.saveConfig();
                player.sendMessage("§6[Towny Spawn Menu] §3成功设置没有国家的城镇的物品！");
                return true;
            }
            case "private" -> {
                instance.getConfig().set("menu.privateItem", itemName);
                instance.saveConfig();
                player.sendMessage("§6[Towny Spawn Menu] §3成功设置私有城镇的物品！");
                return true;
            }
            default -> {
                player.sendMessage("§6[Towny Spawn Menu] §c你必须提供一个有效的选项！");
                return false;
            }
        }
    }
}
