package net.acair.acairsepicweapons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class OverpowerEffect extends MobEffect {

    public OverpowerEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF4500); // Цвет эффекта (оранжевый)
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Вызываем каждый тик
    }

    public static void activateOverpower(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            int duration = 200;

            if (!player.level().isClientSide) {
                // Удаляем предыдущий эффект
                player.removeEffect(ModEffects.OVERPOWER.get());

                // Накладываем новый эффект
                player.addEffect(new MobEffectInstance(ModEffects.OVERPOWER.get(), duration, amplifier, false, true));
            }
        }
    }
}
