package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.effect.OverpowerEffect;
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

public class GreatSwordItem extends SwordItem {
    private int consecutiveChargedHits = 0; // Счетчик заряженных атак
    private Timer resetTimer = new Timer(); // Таймер для сброса серии ударов
    private final int overpowerLevel; // Уровень эффекта Overpower

    public GreatSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties, int overpowerLevel) {
        super(tier, attackDamage, attackSpeed, properties);
        this.overpowerLevel = overpowerLevel; // Устанавливаем уровень эффекта Overpower
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if (!target.level().isClientSide && attacker instanceof Player player) {

            if (isFullyCharged(player)) {
                consecutiveChargedHits++;
                player.sendSystemMessage(Component.literal("Consecutive charged hits: " + consecutiveChargedHits));

                resetTimer.cancel(); // Сбрасываем таймер
                resetTimer = new Timer();
                resetTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        consecutiveChargedHits = 0;
                        player.sendSystemMessage(Component.literal("Series reset due to inactivity."));
                    }
                }, 5000); // Таймер на 5 секунд

                if (consecutiveChargedHits >= 2) {
                    player.sendSystemMessage(Component.literal("Overpower effect activated!"));

                    OverpowerEffect.activateOverpower(player, overpowerLevel);

                    consecutiveChargedHits = 0; // Сброс после активации эффекта
                    resetTimer.cancel();
                }
            } else {
                consecutiveChargedHits = 0; // Сброс серии при незаряженной атаке
                player.sendSystemMessage(Component.literal("Series reset due to non-charged attack."));
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    private boolean isFullyCharged(Player player) {
        return player.getAttackStrengthScale(0.5f) >= 1.0f; // Проверка полной зарядки атаки
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        String overpowerText = "effect.acairsepicweapons.overpower";
        String descriptionText = "";

        // Используем уровень эффекта Overpower
        switch (overpowerLevel) {
            case 0 -> {
                overpowerText = "effect.acairsepicweapons.overpower_i";
                descriptionText = "item.acairsepicweapons.greatsword.tooltip_i";
            }
            case 1 -> {
                overpowerText = "effect.acairsepicweapons.overpower_ii";
                descriptionText = "item.acairsepicweapons.greatsword.tooltip_ii";
            }
            case 2 -> {
                overpowerText = "effect.acairsepicweapons.overpower_iii";
                descriptionText = "item.acairsepicweapons.greatsword.tooltip_iii";
            }
            case 3 -> {
                overpowerText = "effect.acairsepicweapons.overpower_iv";
                descriptionText = "item.acairsepicweapons.greatsword.tooltip_iv";
            }
            case 4 -> {
                overpowerText = "effect.acairsepicweapons.overpower_v";
                descriptionText = "item.acairsepicweapons.greatsword.tooltip_v";
            }
        }

        // Добавляем название эффекта
        tooltip.add(Component.translatable(overpowerText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD700)))); // Золотой цвет

        // Добавляем описание эффекта
        tooltip.add(Component.translatable(descriptionText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA8A8A8)))); // Серый цвет

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
