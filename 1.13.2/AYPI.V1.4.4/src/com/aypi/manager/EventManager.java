package com.aypi.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.aypi.Aypi;
import com.aypi.events.EntityDamage;
import com.aypi.events.MenuListener;
import com.aypi.events.PlayerBreakBlock;
import com.aypi.events.PlayerInteract;
import com.aypi.events.PlayerMove;
import com.aypi.events.PlayerPlaceBlock;
import com.aypi.events.TimerFinishEvent;

public class EventManager {

	/*
	 * Class gérant les évenements de l'AYPI
	 * 
	 * @param plugin Le plugin AYPI
	 */

	public EventManager(Aypi plugin) {
		PluginManager pluginManager = Bukkit.getPluginManager();

		pluginManager.registerEvents(new MenuListener(), plugin);
		pluginManager.registerEvents(new PlayerBreakBlock(), plugin);
		pluginManager.registerEvents(new PlayerPlaceBlock(), plugin);
		pluginManager.registerEvents(new PlayerInteract(), plugin);
		pluginManager.registerEvents(new TimerFinishEvent(), plugin);
		pluginManager.registerEvents(new EntityDamage(), plugin);
		
		if (plugin.getConfig().getBoolean("playermove"))
			pluginManager.registerEvents(new PlayerMove(), plugin);
	}

}
