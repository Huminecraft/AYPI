package com.aypi.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.aypi.Aypi;
import com.aypi.utils.inter.ZoneListener;

public class Zone {
	
	public static final int PRIORITY_BASIC = 0;
	public static final int PRIORITY_ADVANCED = 1;
	public static final int PRIORITY_HIGH = 2;
	public static final int PRIORITY_ULTRA = 3;
	
	
	private Square square;
	private ZoneListener zoneListener;
	
	private boolean asWhiteList = false;
	
	private ArrayList<OfflinePlayer> whiteList = new ArrayList<OfflinePlayer>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();	
	
	private int priority = PRIORITY_BASIC;
	
	public Zone(Square square, ZoneListener zoneListener) {
		constructor(square, zoneListener);
	}
	
	public Zone(Square square, ZoneListener zoneListener, int priority) {
		this.priority = priority;
		constructor(square, zoneListener);
	}
	
	private void constructor(Square square, ZoneListener zoneListener) {
		Aypi.getZoneManager().addZone(this);
		this.square = square;
		this.zoneListener = zoneListener;
		updatePlayerInZone();
	}
	
	public void removeZone() {
		Aypi.getZoneManager().removeZone(this);
	}
	
	public Square getSquare() {
		return square;
	}
	
	public void setWhiteList(boolean value) {
		this.asWhiteList = value;
	}
	
	public boolean asWhiteList() {
		return asWhiteList;
	}
	
	public void addPlayerToWhiteList(OfflinePlayer player) {
		whiteList.add(player);
	}
	
	public void removePlayerToWhiteList(OfflinePlayer player) {
		whiteList.remove(player);
	}
	
	public ZoneListener getZoneListener () {
		return zoneListener;
	}
	
	public boolean containLocation(Location loc) {
		if (square.getPos1().getBlockX() <= loc.getBlockX()
		&& square.getPos1().getBlockY() <= loc.getBlockY()
		&& square.getPos1().getBlockZ() <= loc.getBlockZ()
		&& square.getPos2().getBlockX() >= loc.getBlockX()
		&& square.getPos2().getBlockY() >= loc.getBlockY()
		&& square.getPos2().getBlockZ() >= loc.getBlockZ()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean entityIsInZone(Entity entity) {
		return containLocation(entity.getLocation());
	}
	
	public void updatePlayerInZone() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (entityIsInZone(player)) {
				entities.add(player);
			}
		}
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public boolean entityListContainPlayer(Player player) {
		for (Entity entity : entities) {
			if (entity instanceof Player && entity.getName().equalsIgnoreCase(player.getName())) {
				return true;
			}
		}
		return false;
	}
}
