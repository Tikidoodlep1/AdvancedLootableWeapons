package tiki.advancedlootableweapons.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleAirBubble extends Particle {
	
	private double maxY;
	//private float originalScale;
	
	public ParticleAirBubble(World worldIn, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, double maxY) {
		super(worldIn, posX, posY, posZ);
		
		//Mineraft bubble texture index
		this.setParticleTextureIndex(32);
		
		this.setRBGColorF(0.6f, 0.8f, 1.0f);
		this.setAlphaF(0.7f);
		
		this.particleMaxAge = 40 + this.rand.nextInt(20);
		
		this.motionX = speedX;
		this.motionY = speedY;
		this.motionZ = speedZ;
		this.canCollide = false;
		
		this.maxY = maxY;
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge || this.posY > this.maxY)
        {
            this.setExpired();
        }
        
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;
	}
	
	public static class Factory implements IParticleFactory {
		
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoord, double yCoord, double zCoord, 
				double xSpeed, double ySpeed, double zSpeed, int... args) {
			
			return new ParticleAirBubble(worldIn, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, 255);
		}
		
	}
}
