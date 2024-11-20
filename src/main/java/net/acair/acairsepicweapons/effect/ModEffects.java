package net.acair.acairsepicweapons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "acairsepicweapons", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "acairsepicweapons");

    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding", BleedingEffect::new);
    public static final RegistryObject<MobEffect> CLOAK_OF_SHADOWS = MOB_EFFECTS.register("cloak_of_shadows", CloakOfShadowsEffect::new);
    public static final RegistryObject<MobEffect> OVERPOWER = MOB_EFFECTS.register("overpower", OverpowerEffect::new);
    public static final RegistryObject<MobEffect> WITHERING_WEIGHT = MOB_EFFECTS.register("withering_weight", WitheringWeightEffect::new);
}
