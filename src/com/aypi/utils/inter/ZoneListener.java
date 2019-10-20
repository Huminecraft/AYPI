package com.aypi.utils.inter;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.PortalCreateEvent;

public interface ZoneListener {
	
	public abstract void onPlayerMoveInZone(Player player, PlayerMoveEvent e);
	public abstract void onPlayerTryToPlaceBlock(Player player, Block block, BlockPlaceEvent e);
	public abstract void onPlayerTryToRemoveBlock(Player player, Block block, BlockBreakEvent e);
	public abstract void onPlayerTryToInteractEvent(Player player, PlayerInteractEvent e);
	public abstract void onPlayerEnterZone(Player player);
	public abstract void onPlayerExitZone(Player player);
	public abstract void onDamage(Entity entity, EntityDamageEvent e);
	public abstract void onEntitySuffersDamages(Entity entity, EntityDamageByEntityEvent e);
	public abstract void onEntityMakesDamages(Entity entity, EntityDamageByEntityEvent e);
	public abstract void onEntityDeath(Entity entity, EntityDeathEvent e);
	public abstract void onExplosionPrime(ExplosionPrimeEvent e);
	public abstract void onPortalCreate(PortalCreateEvent e);
}
