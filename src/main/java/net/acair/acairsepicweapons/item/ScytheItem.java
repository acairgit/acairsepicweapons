package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.effect.ModEffects;
import net.acair.acairsepicweapons.particle.BloodParticles;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//
public class ScytheItem extends SwordItem {
    private final int bleedingLevel;

    public ScytheItem(Tier tier, int attackDamage, float attackSpeed, net.minecraft.world.item.Item.Properties properties, int bleedingLevel) {
        super(tier, attackDamage, attackSpeed, properties);
        this.bleedingLevel = bleedingLevel;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        // Наложение эффекта кровотечения на сущность при ударе
        target.addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), 100, this.bleedingLevel));

        if (target.level().isClientSide) {
            BloodParticles.spawnBloodParticles(target, target.level());
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Определяем текст "Bleeding" с указанием уровня эффекта
        String bleedingText = "effect.acairsepicweapons.bleeding";
        switch (bleedingLevel) {
            case 0 -> bleedingText = "effect.acairsepicweapons.bleeding_i";
            case 1 -> bleedingText = "effect.acairsepicweapons.bleeding_ii";
            case 2 -> bleedingText = "effect.acairsepicweapons.bleeding_iii";
            case 3 -> bleedingText = "effect.acairsepicweapons.bleeding_iv";
            case 4 -> bleedingText = "effect.acairsepicweapons.bleeding_v";
        }

        // Добавляем текст "Bleeding" с уровнем красным цветом
        tooltip.add(Component.translatable(bleedingText)
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF5555))));

        // Добавляем описание эффекта белым цветом
        tooltip.add(Component.translatable("item.acairsepicweapons.scythe.tooltip")
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFFFF))));

        super.appendHoverText(stack, level, tooltip, flag);
    }
}