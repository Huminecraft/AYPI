package com.aypi.utils.particle;

import org.bukkit.Location;
import org.bukkit.Particle;

public class WaveCenterParticle extends ParticleForm{

	private Location location;
	
	public WaveCenterParticle(Particle particle, Location location) {
		super(particle);
		this.location = location;
	}

	@Override
	public void update() {
		
		
		
	}
	
	public Location getLocation() {
		return this.location;
	}

}
