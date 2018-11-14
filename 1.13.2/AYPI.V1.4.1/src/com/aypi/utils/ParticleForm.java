package com.aypi.utils;

import org.bukkit.Particle;

public abstract class ParticleForm {
	
	private Particle particle;
	
	public ParticleForm(Particle particle) {
		this.particle = particle;
	}
	
	public Particle getParticle() {
		return particle;
	}
	
}
