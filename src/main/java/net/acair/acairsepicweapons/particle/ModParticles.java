package net.acair.acairsepicweapons.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.acair.acairsepicweapons.AcairsEpicWeapons;
//
public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AcairsEpicWeapons.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLE =
            PARTICLES.register("bleedparticle", () -> new SimpleParticleType(true));
}