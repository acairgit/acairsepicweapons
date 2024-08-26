package net.acair.acairsepicweapons.event;

import net.acair.acairsepicweapons.effect.ModEffects;
import net.acair.acairsepicweapons.item.DaggerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "acairsepicweapons")
public class DaggerEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            // Проверяем, держит ли игрок кинжал
            if (player.getMainHandItem().getItem() instanceof DaggerItem dagger) {
                dagger.onPlayerHurt();
            }

            // Проверка на неуязвимость игрока под действием CloakOfShadows
            if (player.hasEffect(ModEffects.CLOAK_OF_SHADOWS.get())) {
                // Если игрок под действием эффекта CloakOfShadows, отменяем урон
                event.setCanceled(true);
                player.sendSystemMessage(Component.literal("Cloak of Shadows effect active, damage prevented."));
            }
        }

        // Проверка на нанесение урона другим существам (игроком)
        if (event.getSource().getEntity() instanceof Player attacker) {
            // Проверка, активен ли эффект CloakOfShadows у атакующего игрока
            MobEffectInstance cloakEffect = attacker.getEffect(ModEffects.CLOAK_OF_SHADOWS.get());
            if (cloakEffect != null) {
                int amplifier = cloakEffect.getAmplifier();
                // Увеличиваем урон в зависимости от уровня эффекта
                float additionalDamage = amplifier + 1.0f;
                event.setAmount(event.getAmount() + additionalDamage);

                // Логирование для проверки
                attacker.sendSystemMessage(Component.literal("Cloak of Shadows active, damage increased by " + additionalDamage + "."));

                // Снимаем эффект CloakOfShadows при нанесении урона
                attacker.removeEffect(ModEffects.CLOAK_OF_SHADOWS.get());
                attacker.sendSystemMessage(Component.literal("Cloak of Shadows effect removed due to attack."));
            }
        }
    }
}
