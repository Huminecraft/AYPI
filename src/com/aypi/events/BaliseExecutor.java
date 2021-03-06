package com.aypi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aypi.Aypi;
import com.aypi.utils.xml.XMLFile;
import com.aypi.utils.xml.balises.PlayerJoinBalise;
import com.aypi.utils.xml.balises.PlayerLeftBalise;
import com.aypi.utils.xml.balises.PlayerMoveBalise;
import com.aypi.utils.xml.balises.StartServerBalise;
import com.aypi.utils.xml.balises.StopServerBalise;

public class BaliseExecutor implements Listener {
	
	public static void onServerStart() {
		executeBalise(StartServerBalise.NAME, null);
	}
	
	public static void onServerStop() {
		executeBalise(StopServerBalise.NAME, null);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		executeBalise(PlayerJoinBalise.NAME, e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent e) {
		executeBalise(PlayerLeftBalise.NAME, e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		executeBalise(PlayerMoveBalise.NAME, e.getPlayer());
	}
	
	
	private static void executeBalise(String name, Player player) {
		
		for (XMLFile xmlFile : Aypi.getXMLFileManager().getXMLFile()) {
			xmlFile.executesAllBalises(name, player);
		}
		
	}
	

}
