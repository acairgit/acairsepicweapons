package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.effect.CloakOfShadowsEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DaggerItem extends SwordItem {
    private final int cloakLevel;
    private int consecutiveChargedHits = 0;
    private Timer resetTimer = new Timer();

    public DaggerItem(Tier tier, int attackDamage, float attackSpeed, Properties properties, int cloakLevel) {
        super(tier, attackDamage, attackSpeed, properties);
        this.cloakLevel = cloakLevel;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if (!target.level().isClientSide && attacker instanceof Player player) {

            // Проверяем, достаточно ли низкое здоровье для начала серии атак
            if (player.getHealth() >= player.getMaxHealth() * 0.6) {
                player.sendSystemMessage(Component.literal("Health too high to start a charged attack series."));
                consecutiveChargedHits = 0; // Сбрасываем серию, если здоровье слишком высокое
                return super.hurtEnemy(stack, target, attacker);
            }

            if (isFullyCharged(player)) {
                consecutiveChargedHits++;
                player.sendSystemMessage(Component.literal("Consecutive charged hits: " + consecutiveChargedHits));

                // Сбрасываем таймер на 5 секунд
                resetTimer.cancel();
                resetTimer = new Timer();
                resetTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        consecutiveChargedHits = 0;
                        player.sendSystemMessage(Component.literal("Series reset due to inactivity."));
                    }
                }, 5000); // 5000 миллисекунд = 5 секунд

                if (consecutiveChargedHits >= 3) {
                    player.sendSystemMessage(Component.literal("Activating Cloak of Shadows"));

                    // Вызываем метод для активации эффекта
                    CloakOfShadowsEffect.activateCloakOfShadows(player, cloakLevel);

                    consecutiveChargedHits = 0; // Сбрасываем счетчик после активации
                    resetTimer.cancel();
                }
            } else {
                consecutiveChargedHits = 0; // Сбрасываем серию, если атака не была заряжена
                player.sendSystemMessage(Component.literal("Series reset due to non-charged attack."));
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Определяем текст для названия эффекта и его описания
        String cloakText = "effect.acairsepicweapons.cloak_of_shadows";
        String descriptionText = "";

        switch (cloakLevel) {
            case 0 -> {
                cloakText = "effect.acairsepicweapons.cloak_of_shadows_i";
                descriptionText = "item.acairsepicweapons.dagger.tooltip_i";
            }
            case 1 -> {
                cloakText = "effect.acairsepicweapons.cloak_of_shadows_ii";
                descriptionText = "item.acairsepicweapons.dagger.tooltip_ii";
            }
            case 2 -> {
                cloakText = "effect.acairsepicweapons.cloak_of_shadows_iii";
                descriptionText = "item.acairsepicweapons.dagger.tooltip_iii";
            }
            case 3 -> {
                cloakText = "effect.acairsepicweapons.cloak_of_shadows_iv";
                descriptionText = "item.acairsepicweapons.dagger.tooltip_iv";
            }
            case 4 -> {
                cloakText = "effect.acairsepicweapons.cloak_of_shadows_v";
                descriptionText = "item.acairsepicweapons.dagger.tooltip_v";
            }
        }

        // Добавляем название эффекта (коричневый цвет)
        tooltip.add(Component.translatable(cloakText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x8B4513)))); // Коричневый цвет (#8B4513)

        // Добавляем описание эффекта (серый цвет)
        tooltip.add(Component.translatable(descriptionText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA8A8A8)))); // Серый цвет (#A8A8A8)

        super.appendHoverText(stack, level, tooltip, flag);
    }

    private boolean isFullyCharged(Player player) {
        // Проверка на полную зарядку удара
        return player.getAttackStrengthScale(0.5f) >= 1.0f;
    }

    public void onPlayerHurt() {
        consecutiveChargedHits = 0;
        System.out.println("Series reset due to player being hurt.");
        resetTimer.cancel();
    }
}
