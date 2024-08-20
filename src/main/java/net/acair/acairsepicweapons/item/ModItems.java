package net.acair.acairsepicweapons.item;

import net.acair.acairsepicweapons.AcairsEpicWeapons;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AcairsEpicWeapons.MOD_ID);

    // Generic method to register weapons
    private static RegistryObject<Item> registerWeapon(String name, Tiers tier, int attackDamage, float attackSpeed) {
        return ITEMS.register(name, () -> new SwordItem(tier, attackDamage, attackSpeed, new Item.Properties()));
    }

    // Регистрация кинжалов
    public static final RegistryObject<Item> IRON_DAGGER = registerWeapon("iron_dagger", Tiers.IRON, 2, -2.0F);
    public static final RegistryObject<Item> DIAMOND_DAGGER = registerWeapon("diamond_dagger", Tiers.DIAMOND, 2, -2.0F);
    public static final List<RegistryObject<Item>> DAGGERS = List.of(IRON_DAGGER, DIAMOND_DAGGER);

    // Регистрация кос
    public static final RegistryObject<Item> IRON_SCYTHE = registerWeapon("iron_scythe", Tiers.IRON, 3, -2.80F);
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerWeapon("diamond_scythe", Tiers.DIAMOND, 3, -2.80F);
    public static final List<RegistryObject<Item>> SCYTHES = List.of(IRON_SCYTHE, DIAMOND_SCYTHE);
}



