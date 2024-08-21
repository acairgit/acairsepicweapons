package net.acair.acairsepicweapons.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BleedingEffect extends MobEffect {

    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // Красный цвет эффекта
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);

        Level level = entity.level();

        // Получаем зарегистрированный DamageType
        Holder<DamageType> damageTypeHolder = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("minecraft", "generic")));

        // Создаем DamageSource с использованием Holder<DamageType>
        DamageSource damageSource = new DamageSource(damageTypeHolder);

        // Наносим урон в зависимости от уровня эффекта (amplifier + 1)
        float damage = switch (amplifier) {
            case 0 -> 0.3f; // Bleeding I
            case 1 -> 0.5f; // Bleeding II
            case 2 -> 0.7f; // Bleeding III
            case 3 -> 0.9f; // Bleeding IV
            case 4 -> 1.0f; // Bleeding V
            default -> 0.5f;
        };
        entity.hurt(damageSource, damage); // Наносим урон с использованием кастомного DamageSource
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 10 == 0; // Эффект активируется каждые 0.5 секунд (10 тиков)
    }
}
