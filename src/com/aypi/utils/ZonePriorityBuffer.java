package com.aypi.utils;

import java.util.ArrayList;

public class ZonePriorityBuffer {
	
	private ArrayList<Zone> zones;
	
	private int priority;
	
	public ZonePriorityBuffer() {
		this.zones = new ArrayList<Zone>();
		this.priority = Zone.PRIORITY_BASIC;
	}
	
	public void addZone(Zone zone) {
		zones.add(zone);
		if (zone.getPriority() > priority) {
			this.priority = zone.getPriority();
		}
	}
	
	public ArrayList<Zone> getZones() {
		return zones;
	}
	
	public ArrayList<Zone> getPriorityZones() {
		ArrayList<Zone> priority = new ArrayList<Zone>();
		for (Zone zone : zones) {
			if (zone.getPriority() >= this.priority) {
				priority.add(zone);
			}
		}
		return priority;
	}

}
