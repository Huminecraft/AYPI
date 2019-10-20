package com.aypi.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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
	private World world;
	private ZoneListener zoneListener;
	
	private boolean asWhiteList = false;
	
	private ArrayList<OfflinePlayer> whiteList = new ArrayList<OfflinePlayer>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();	
	
	private int priority = PRIORITY_BASIC;
	
	public Zone(Square square, World world, ZoneListener zoneListener) {
		constructor(square, world, zoneListener);
	}
	
	public Zone(Square square, World world, ZoneListener zoneListener, int priority) {
		this.priority = priority;
		constructor(square, world, zoneListener);
	}
	
	private void constructor(Square square, World world, ZoneListener zoneListener) {
		Aypi.getZoneManager().addZone(this);
		this.square = square;
		this.world = world;
		this.zoneListener = zoneListener;
		//updatePlayerInZone(); //still useful ? just used in the constructor and never later, so the array is not relevant
	}
	
	public void removeZone() {
		Aypi.getZoneManager().removeZone(this);
	}
	
	public Square getSquare() {
		return square;
	}
	
	public World getWorld()
	{
		return world;
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
	
	public boolean containLocation(Location loc)
	{
		return world == loc.getWorld() && square.containLocation(loc);
	}
	
	public boolean containsChunk(Chunk chunk) {
		//TODO :WARNING WHEN THE CHUNK WILL DEPENDS ON THE Y TOO
		return world == chunk.getWorld() &&  square.containLocation(new Location(chunk.getWorld(), chunk.getX()*16, 64, chunk.getZ()*16));
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean entityIsInZone(Entity entity) {
		return world == entity.getWorld() && containLocation(entity.getLocation());
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
			if (entity instanceof Player && entity.getUniqueId().equals(player.getUniqueId())) {
				return true;
			}
		}
		return false;
	}
}
