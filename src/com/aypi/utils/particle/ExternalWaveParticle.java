package com.aypi.utils.particle;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ExternalWaveParticle extends ParticleForm{

	private Location location;
	private Location particleLocation;
	private double t;
	private double distance;
	
	public ExternalWaveParticle(Particle particle, Location location, double distance) {
		super(particle);
		this.location = location;
		this.particleLocation = new Location(location.getWorld(), location.getX()+0.5, location.getY(), location.getZ()+0.5);
		this.distance = distance;
	}
	
	public ExternalWaveParticle(Particle particle, Location location, double distance, double heigth) {
		super(particle);
		this.location = location;
		this.particleLocation = new Location(location.getWorld(), location.getX()+0.5, location.getY()+heigth, location.getZ()+0.5);
		this.distance = distance;
	}

	@Override
	public void update() {
		
		t += 0.1*Math.PI;
		for (double theta = 0 ; theta <= 2*Math.PI ; theta += Math.PI/32) {
			double x = t*Math.cos(theta);
			double y = Math.exp(-0.1*t) * Math.sin(t) + 1.5;
			double z = t*Math.sin(theta);
			particleLocation.add(x, y, z);
			particleLocation.getWorld().spawnParticle(super.getParticle(), particleLocation, 0, 0, 0, 0, null);
			particleLocation.subtract(x, y, z);
		}
		if (t > distance) {
			super.destroy();
		}
		
	}
	
	public Location getLocation() {
		return this.location;
	}

}
