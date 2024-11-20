package net.acair.acairsepicweapons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class WitheringWeightEffect extends MobEffect {

    private static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("b6685fb8-dbf3-4c4e-8f9c-3a7a03f1f23e");

    public WitheringWeightEffect() {
        super(MobEffectCategory.HARMFUL, 0xA52A2A); // Цвет эффекта (коричневый)
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Вызываем метод каждую секунду (20 тиков)
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Проверяем, есть ли уже модификатор скорости
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (movementSpeedAttribute != null && movementSpeedAttribute.getModifier(MOVEMENT_SPEED_MODIFIER_UUID) == null) {
                double slowAmount = 0.15 * (amplifier + 1); // Замедление от 15% до 50%
                AttributeModifier modifier = new AttributeModifier(MOVEMENT_SPEED_MODIFIER_UUID, "Withering Weight Slowdown", -slowAmount, AttributeModifier.Operation.MULTIPLY_TOTAL);
                movementSpeedAttribute.addTransientModifier(modifier);
            }

            // Наносим урон по каждому элементу брони
            int damagePerTick = switch (amplifier) {
                case 0 -> 4;  // Withering Weight I
                case 1 -> 7;  // Withering Weight II
                case 2 -> 10; // Withering Weight III
                case 3 -> 13; // Withering Weight IV
                case 4 -> 16; // Withering Weight V
                default -> 0;
            };

            // Урон по каждому элементу брони
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (player.getItemBySlot(slot).isDamageableItem()) {
                    player.getItemBySlot(slot).hurtAndBreak(damagePerTick, player, (e) -> e.broadcastBreakEvent(slot));
                }
            }
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entity, @NotNull AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Player player) {
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (movementSpeedAttribute != null) {
                movementSpeedAttribute.removeModifier(MOVEMENT_SPEED_MODIFIER_UUID);
            }
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    // Метод для применения эффекта с заданным уровнем
    public static void applyWitheringWeight(LivingEntity target, int amplifier) {
        int duration = 60; // 3 секунды (60 тиков)
        target.addEffect(new MobEffectInstance(ModEffects.WITHERING_WEIGHT.get(), duration, amplifier, false, true));
    }
}
