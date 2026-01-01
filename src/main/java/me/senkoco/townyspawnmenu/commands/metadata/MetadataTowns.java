package me.senkoco.townyspawnmenu.commands.metadata;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.senkoco.townyspawnmenu.utils.Metadata;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MetadataTowns implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Resident res = TownyAPI.getInstance().getResident((Player)sender);
        assert res != null;
        if(!sender.hasPermission("townyspawnmenu.set.town") || !sender.hasPermission("townyspawnmenu.set.admin")) { sender.sendMessage("§6[Towny Spawn Menu] §c你不能这样做！"); return false; }
        if(!res.hasTown()) { sender.sendMessage("§6[Towny Spawn Menu] §c你不在一个城镇中！"); return false; }
        if(!res.isMayor()) { sender.sendMessage("§6[Towny Spawn Menu] §c你不是你所在城镇的市长！"); return false; }

        Material material;
        try {
            material = Material.valueOf(args[0].replace("minecraft:", "").toUpperCase());
        }catch(IllegalArgumentException e){
            sender.sendMessage("§6[Towny Spawn Menu] §c请提供有效的物品或方块名称！");
            sender.sendMessage("§c示例: nether_star (不区分大小写，空格必须替换为下划线)");
            return false;
        }

        if(args.length > 1) {
            if(!sender.hasPermission("townyspawnmenu.set.admin")) { sender.sendMessage("§4你不能这样做！"); return false; }
            Metadata.setBlockInMenu(Objects.requireNonNull(TownyAPI.getInstance().getTown(args[1])), material.name());
            sender.sendMessage("§6[Towny Spawn Menu] §3该城镇在菜单中的物品/方块现在是: " + material.name().toLowerCase());
        }else{
            Metadata.setBlockInMenu(Objects.requireNonNull(res.getTownOrNull()), material.name());
            sender.sendMessage("§6[Towny Spawn Menu] §3你的城镇在菜单中的物品/方块现在是: " + material.name().toLowerCase());
        }
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
        if(sender.hasPermission("townyspawnmenu.set.admin")) {
            if(args.length == 2) {
                List<Town> allTowns = new LinkedList<>(TownyAPI.getInstance().getTowns());
                List<String> townNames = new LinkedList<>();

                for (Town current : allTowns) {
                    townNames.add(current.getName());
                }
                return townNames;
            }
        }
        return null;
    }
}
