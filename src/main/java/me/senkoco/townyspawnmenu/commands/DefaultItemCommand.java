package me.senkoco.townyspawnmenu.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DefaultItemCommand implements TabExecutor {
    static Plugin plugin = Bukkit.getPluginManager().getPlugin("TownySpawnMenu");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("townyspawnmenu.set.default")) { sender.sendMessage("§6[Towny Spawn Menu] §c你不能这样做！"); return false; }

        Material material;
        try {
            material = Material.valueOf(args[0].replace("minecraft:", "").toUpperCase());
        }catch(IllegalArgumentException e){
            sender.sendMessage("§6[Towny Spawn Menu] §c请提供有效的物品或方块名称！");
            sender.sendMessage("§c示例: nether_star (不区分大小写，空格必须替换为下划线)");
            return false;
        }
        plugin.getConfig().set("menu.defaultItem", material.name());
        plugin.saveConfig();
        sender.sendMessage("§6[Towny Spawn Menu] §3菜单中城镇和国家的默认物品现在是: " + args[0].replace("minecraft:", ""));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return null;
        if(args.length == 1) {
            List<Material> allMaterials = new LinkedList<>(Arrays.stream(Material.values()).toList());
            List<String> materials = new LinkedList<>();
            for (Material current : allMaterials) {
                if (current.name().startsWith("LEGACY_")) break;
                materials.add("minecraft:" + current.name().toLowerCase());
            }
            return materials;
        }
        return null;
    }
}
