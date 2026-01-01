package me.senkoco.townyspawnmenu.commands.sub;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import me.senkoco.townyspawnmenu.utils.Metadata;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class HideSubCommand {
    public static List<String> autoComplete = Arrays.asList("nation", "town");

    public static boolean execute(Player player, String type) {
        Resident res = TownyAPI.getInstance().getResident(player);
        assert res != null;
        if(!player.hasPermission("townyspawnmenu.hide") || !player.hasPermission("townyspawnmenu.set.admin")) { player.sendMessage("§6[Towny Spawn Menu] §c你不能这样做！"); return false; }
        switch(type) {
            case "town" -> {
                try {
                    return hideTown(player, res);
                } catch (NotRegisteredException ignored) {
                    player.sendMessage("§6[Towny Spawn Menu] §c出错了！");
                    return false;
                }
            }
            case "nation" -> {
                try {
                    return hideNation(player, res);
                } catch (TownyException ignored) {
                    player.sendMessage("§6[Towny Spawn Menu] §c出错了！");
                    return false;
                }
            }
            default -> {
                player.sendMessage("§6[Towny Spawn Menu] §c请提供有效的区域类型！(nation/town)");
                return false;
            }
        }
    }

    public static boolean hideTown(Player player, Resident res) throws NotRegisteredException {
        if(!res.hasTown()) { player.sendMessage("§6[Towny Spawn Menu] §c你不在一个城镇中！"); return false; }
        if(!res.isMayor()) { player.sendMessage("§6[Towny Spawn Menu] §c你不是你所在城镇的市长！"); return false; }
        Metadata.setTownHidden(res.getTown());
        if(Metadata.getTownHidden(res.getTown())) {
            player.sendMessage("§6[Towny Spawn Menu] §3你的城镇现在对除你和你城镇居民以外的所有人隐藏！");
        }else{
            player.sendMessage("§6[Towny Spawn Menu] §3你的城镇现在对所有人可见！");
        }
        return true;
    }

    public static boolean hideNation(Player player, Resident res) throws TownyException {
        if(!res.hasNation()) { player.sendMessage("§6[Towny Spawn Menu] §c你不在一个国家中！"); return false; }
        if(!res.isKing()) { player.sendMessage("§6[Towny Spawn Menu] §c你不是你所在国家的国王！"); return false; }
        Metadata.setNationHidden(res.getNation());
        if(Metadata.getNationHidden(res.getNation())) {
            player.sendMessage("§6[Towny Spawn Menu] §3你的国家现在对除你和你国家居民以外的所有人隐藏！");
        }else{
            player.sendMessage("§6[Towny Spawn Menu] §3你的国家现在对所有人可见！");
        }
        return true;
    }
}
