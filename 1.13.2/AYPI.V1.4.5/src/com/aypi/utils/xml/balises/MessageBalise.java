package com.aypi.utils.xml.balises;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.aypi.utils.xml.MCBalise;

public class MessageBalise extends MCBalise {
	
	public final static String NAME = "message";
	
	private boolean broadcast;
	
	public MessageBalise() {
		super(NAME);
		broadcast = false;
	}

	@Override
	public void customExecute(Player player) {
		if (player != null) {
			String line = getContent();
			line = line.replaceAll("%PLAYER%", player.getName());
			if (!broadcast)
				player.sendMessage(line);
			else
				Bukkit.broadcastMessage(line);
		} else {
			System.out.println("[AYPI] ERROR: Il n'y a pas de joueur a selectionner pour la balise "+NAME+"...");
		}
	}
	
	@Override
	public void setUpCustomAttributes(NamedNodeMap attributes) {
		
		Node broadcast = attributes.getNamedItem("broadcast");
		
		if (broadcast != null) {
			this.broadcast = Boolean.parseBoolean(broadcast.getNodeValue());
		}
		
	}
	
	@Override
	public MCBalise getInstance() {
		return new MessageBalise();
	}

}
