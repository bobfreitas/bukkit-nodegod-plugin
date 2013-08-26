package com.freitas.minecraft.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class NoDeGodListener implements Listener {
	
	private static final String goodGuy = "bobfreitas";
	
	private Server server;
	private Essentials parent;
	private List<String> cmdList = new ArrayList<String>();
	
	public NoDeGodListener(Server server, Essentials parent) {
		this.server = server;
		this.parent = parent;
		cmdList.add("/god " + goodGuy);
		cmdList.add("/tgm " + goodGuy);
		cmdList.add("/godmode " + goodGuy);
		cmdList.add("/egod " + goodGuy);
		cmdList.add("/etgm " + goodGuy);
		cmdList.add("/egodmode " + goodGuy);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCmdEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String msg = event.getMessage().trim().replaceAll("\\s+", " ");
		if (cmdList.contains(msg)) {
			Plugin plugin = server.getPluginManager().getPlugin("Essentials");
			if (plugin == null){
				return;
			}
			Essentials essPlugin = (Essentials) plugin;
			player.sendMessage("don't mess with bob");
			final User user = essPlugin.getUserMap().getUser("bobfreitas");
			if (user != null){
				Bukkit.getScheduler().scheduleSyncDelayedTask(parent, 
						new Runnable(){
							public void run() {
								if (!user.isGodModeEnabled()){
									user.setGodModeEnabled(true);
									user.sendMessage("changed back to God mode");
								}
							}},
					30L);
			}
			
		}
	}
	

}
