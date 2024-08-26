package net.acair.acairsepicweapons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class CloakOfShadowsEffect extends MobEffect {

    public CloakOfShadowsEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x1E1E1E); // Цвет эффекта
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);

        if (entity instanceof Player player) {
            // Устанавливаем невидимость игрока и всех его предметов
            if (!player.isInvisible()) {
                player.setInvisible(true);
            }

            // Проверяем, если эффект "Плащ Теней" заканчивается, снимаем невидимость
            MobEffectInstance cloakEffect = player.getEffect(ModEffects.CLOAK_OF_SHADOWS.get());
            if (cloakEffect != null && cloakEffect.getDuration() <= 20) {
                player.setInvisible(false);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // Вызываем каждый тик
    }

    public static void activateCloakOfShadows(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Устанавливаем длительность эффекта в зависимости от уровня
            int duration = switch (amplifier) {
                case 0 -> 60;  // Cloak of Shadows I: 3 секунды (60 тиков)
                case 1 -> 60;  // Cloak of Shadows II: 3 секунды (60 тиков)
                case 2 -> 70;  // Cloak of Shadows III: 3.5 секунды (70 тиков)
                case 3 -> 70;  // Cloak of Shadows IV: 3.5 секунды (70 тиков)
                case 4 -> 80;  // Cloak of Shadows V: 4 секунды (80 тиков)
                default -> 60; // По умолчанию 3 секунды
            };

            if (!player.level().isClientSide) {
                // Удаляем все существующие эффекты перед наложением нового
                player.removeAllEffects();

                player.addEffect(new MobEffectInstance(ModEffects.CLOAK_OF_SHADOWS.get(), duration, amplifier, false, false));
                player.setInvisible(true); // Устанавливаем невидимость
            }
        }
    }
}
