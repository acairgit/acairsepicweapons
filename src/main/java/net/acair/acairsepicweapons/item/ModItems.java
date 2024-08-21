package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.AcairsEpicWeapons;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AcairsEpicWeapons.MOD_ID);

    private static RegistryObject<Item> registerWeapon(String name, Tiers tier) {
        return ITEMS.register(name, () -> new SwordItem(tier, 2, (float) -2.0, new Item.Properties()));
    }

    // Метод для регистрации кос
    private static RegistryObject<Item> registerScythe(String name, Tiers tier, int bleedingLevel) {
        return ITEMS.register(name, () -> new ScytheItem(tier, 3, (float) -2.8, new Item.Properties(), bleedingLevel));
    }

    // Регистрация кинжалов
    public static final RegistryObject<Item> IRON_DAGGER = registerWeapon("iron_dagger", Tiers.IRON);
    public static final RegistryObject<Item> DIAMOND_DAGGER = registerWeapon("diamond_dagger", Tiers.DIAMOND);
    public static final List<RegistryObject<Item>> DAGGERS = List.of(IRON_DAGGER, DIAMOND_DAGGER);

    // Регистрация кос с эффектом кровотечения
    public static final RegistryObject<Item> IRON_SCYTHE = registerScythe("iron_scythe", Tiers.IRON, 2);
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerScythe("diamond_scythe", Tiers.DIAMOND, 3);
    public static final List<RegistryObject<Item>> SCYTHES = List.of(IRON_SCYTHE, DIAMOND_SCYTHE);
}