package com.aypi.utils.particle;

import org.bukkit.Particle;

import com.aypi.Aypi;

public abstract class ParticleForm {
	
	private Particle particle;
	private boolean play;
	
	public ParticleForm(Particle particle) {
		this.particle = particle;
		this.play = false;
		Aypi.getParticleManager().addParticleForm(this);
	}
	
	public abstract void update();
	
	public void play() {
		this.play = true;
	}
	
	public void pause() {
		this.play = false;
	}
	
	public boolean isPlayed() {
		return this.play;
	}
	
	public void destroy() {
		Aypi.getParticleManager().removeParticleForm(this);
	}
	
	public Particle getParticle() {
		return particle;
	}
	
}
