package net.acair.acairsepicweapons.event;

import net.acair.acairsepicweapons.effect.ModEffects;
import net.acair.acairsepicweapons.item.GreatSwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "acairsepicweapons")
public class GreatSwordEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        // Проверяем, если атакующим является игрок
        if (event.getSource().getEntity() instanceof Player attacker) {
            // Проверяем, если игрок использует Великий Меч
            if (attacker.getMainHandItem().getItem() instanceof GreatSwordItem) {
                MobEffectInstance overpowerEffect = attacker.getEffect(ModEffects.OVERPOWER.get());

                // Если эффект Overpower активен, увеличиваем урон
                if (overpowerEffect != null) {
                    int amplifier = overpowerEffect.getAmplifier();
                    float damageMultiplier = switch (amplifier) {
                        case 0 -> 1.2f;  // Overpower I
                        case 1 -> 1.3f;  // Overpower II
                        case 2 -> 1.4f;  // Overpower III
                        case 3 -> 1.5f;  // Overpower IV
                        case 4 -> 1.6f;  // Overpower V
                        default -> 1.0f; // Без усиления
                    };

                    // Увеличиваем урон
                    event.setAmount(event.getAmount() * damageMultiplier);
                    attacker.sendSystemMessage(Component.literal("Overpower effect active, damage increased by " + damageMultiplier + "x."));

                    // Удаляем эффект после нанесения урона
                    attacker.removeEffect(ModEffects.OVERPOWER.get());
                    attacker.sendSystemMessage(Component.literal("Overpower effect removed after attack."));
                }
            }
        }
    }
}
