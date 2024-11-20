package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.effect.WitheringWeightEffect;
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

public class SpearItem extends SwordItem {
    private boolean isCooldownActive = false;  // Переменная для перезарядки
    private final Timer cooldownTimer = new Timer(); // Таймер для перезарядки
    private final int witheringWeightLevel;    // Уровень эффекта Withering Weight

    public SpearItem(Tier tier, int attackDamage, float attackSpeed, Properties properties, int witheringWeightLevel) {
        super(tier, attackDamage, attackSpeed, properties);
        this.witheringWeightLevel = witheringWeightLevel;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {

        if (attacker instanceof Player player) {
            if (!isCooldownActive && isFullyCharged(player)) {
                WitheringWeightEffect.applyWitheringWeight(target, witheringWeightLevel);  // Применяем эффект через статический метод
                startCooldown(player);  // Запускаем перезарядку
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    private boolean isFullyCharged(Player player) {
        return player.getAttackStrengthScale(0.5f) >= 1.0f; // Полная зарядка атаки
    }

    private void startCooldown(Player player) {
        isCooldownActive = true;
        player.sendSystemMessage(Component.literal("Spear is on cooldown for 15 seconds"));
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isCooldownActive = false;
                player.sendSystemMessage(Component.literal("Spear is ready again!"));
            }
        }, 15000); // Перезарядка 15 секунд
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Определяем текст для названия эффекта и его описания
        String witheringText = "effect.acairsepicweapons.withering_weight";
        String descriptionText = "";

        // Название и описание в зависимости от уровня эффекта
        switch (witheringWeightLevel) {
            case 0 -> {
                witheringText = "effect.acairsepicweapons.withering_weight_i";
                descriptionText = "item.acairsepicweapons.spear.tooltip_i";
            }
            case 1 -> {
                witheringText = "effect.acairsepicweapons.withering_weight_ii";
                descriptionText = "item.acairsepicweapons.spear.tooltip_ii";
            }
            case 2 -> {
                witheringText = "effect.acairsepicweapons.withering_weight_iii";
                descriptionText = "item.acairsepicweapons.spear.tooltip_iii";
            }
            case 3 -> {
                witheringText = "effect.acairsepicweapons.withering_weight_iv";
                descriptionText = "item.acairsepicweapons.spear.tooltip_iv";
            }
            case 4 -> {
                witheringText = "effect.acairsepicweapons.withering_weight_v";
                descriptionText = "item.acairsepicweapons.spear.tooltip_v";
            }
        }

        // Добавляем название эффекта
        tooltip.add(Component.translatable(witheringText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA52A2A)))); // Коричневый цвет для Withering Weight

        // Добавляем описание эффекта
        tooltip.add(Component.translatable(descriptionText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA8A8A8)))); // Серый цвет для описания

        super.appendHoverText(stack, level, tooltip, flag);
    }
}