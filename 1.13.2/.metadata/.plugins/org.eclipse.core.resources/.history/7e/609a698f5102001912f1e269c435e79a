package com.aypi.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.aypi.Aypi;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.balises.PlayerJoinBalise;

public class BaliseExecutor implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		for (XMLFile xmlFile : Aypi.getXMLFileManager().getXMLFile()) {
			xmlFile.executesAllBalises(PlayerJoinBalise.NAME, e.getPlayer());
		}
	}

}
