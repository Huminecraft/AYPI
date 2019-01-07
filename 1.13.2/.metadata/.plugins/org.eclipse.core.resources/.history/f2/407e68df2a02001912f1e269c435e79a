package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;

import com.aypi.utils.xml.MCBalise;

public class MessageBalise extends MCBalise {
	
	private final static String name = "message";
	private final static String[] args = {};
	
	private String containt;
	
	public MessageBalise(String containt) {
		super(name, args);
	}

	@Override
	public void load(Player player) {
		player.sendMessage(this.containt);
	}
	
	@Override
	public void load() {
		
	}
	
	public String getContaint() {
		return this.containt;
	}

}
