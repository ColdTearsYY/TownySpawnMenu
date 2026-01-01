package me.senkoco.townyspawnmenu.listeners;

import me.senkoco.townyspawnmenu.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!player.hasPermission("townyspawnui.*")) return;
        if(Main.getUsingOldVersion()){
            player.sendMessage("§6[Towny Spawn Menu] §3您正在使用旧版本的 Towny Spawn Menu，请更新到版本 " + Main.getLatestVersion());
        }
    }
}
