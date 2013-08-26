package com.freitas.minecraft.plugins;

import static com.earth2me.essentials.I18n._;

import org.bukkit.entity.Player;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class NoDeGod extends Essentials {
	
private NoDeGodListener noDeGodListener;

	@Override
	public void onDisable() {
		
		for (Player p : getServer().getOnlinePlayers())
		{
			User user = getUser(p);
			if (user.isVanished())
			{
				user.toggleVanished();
				p.sendMessage(_("Server stopping"));
			}
		}
	}
	
	public void onEnable() {
		Server server = getServer();
		noDeGodListener = new NoDeGodListener(server, this);
		PluginManager pm = server.getPluginManager();
		pm.registerEvents(this.noDeGodListener, this);
	}
	

}
