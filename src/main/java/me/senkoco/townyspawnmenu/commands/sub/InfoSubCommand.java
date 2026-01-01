package me.senkoco.townyspawnmenu.commands.sub;

import com.palmergames.bukkit.towny.Towny;
import me.senkoco.townyspawnmenu.Main;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class InfoSubCommand {
    private static final String townyVersion = Towny.getPlugin().getVersion();

    public static boolean execute(Player player) {
        if (!player.hasPermission("townyspawnmenu.showinfo")) {
            player.sendMessage("§6[Towny Spawn Menu] §c你不能这样做！");
            return false;
        }
        List<String> info = new LinkedList<>();
        info.add("§c§l=========================");
        info.add("§6§lTowny Spawn Menu " + Main.getVersion());
        String madeFor = "0.100.4.4";
        if(!townyVersion.equals(madeFor)){
            info.add("§6专为 §lTowny " + madeFor + " 制作 §6(正在使用 §lTowny v" + townyVersion + "§6)");
        }else {
            info.add("§6专为 §lTowny " + madeFor + " 制作");
        }
        if(Main.getUsingOldVersion()){
            info.add("§6有新版本可用！");
        }else{
            info.add("§6你正在使用最新版本");
        }
        info.add("§c§l=========================");

        info.forEach(player::sendMessage);
        return true;
    }
}
