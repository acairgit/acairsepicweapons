package net.acair.acairsepicweapons.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BloodParticles {

    public static void spawnBloodParticles(LivingEntity entity, Level level) {
        ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
        SimpleParticleType bloodParticleType = ModParticles.BLOOD_PARTICLE.get();

        for (int i = 0; i < 5; i++) {
            double offsetX = (level.random.nextDouble() - 0.5D) * 2.0D;
            double offsetY = level.random.nextDouble();
            double offsetZ = (level.random.nextDouble() - 0.5D) * 2.0D;

            particleEngine.createParticle(bloodParticleType,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() + offsetZ,
                    0, 0, 0);
        }
    }
}