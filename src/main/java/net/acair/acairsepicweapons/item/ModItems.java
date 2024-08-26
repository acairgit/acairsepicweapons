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
        return ITEMS.register(name, () -> new SwordItem(tier, 1, (float) -1.5, new Item.Properties()));
    }
    // Регистрация чеканов
    public static final RegistryObject<Item> STONE_CHEKAN = registerWeapon("stone_chekan", Tiers.STONE);
    public static final RegistryObject<Item> IRON_CHEKAN = registerWeapon("iron_chekan", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_CHEKAN = registerWeapon("gold_chekan", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_CHEKAN = registerWeapon("diamond_chekan", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_CHEKAN = registerWeapon("netherite_chekan", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> CHEKANS = List.of(STONE_CHEKAN, IRON_CHEKAN, GOLD_CHEKAN, DIAMOND_CHEKAN, NETHERITE_CHEKAN);

    // Регистрация кинжалов
    private static RegistryObject<Item> registerDagger(String name, Tiers tier, int cloakLevel) {
        return ITEMS.register(name, () -> new DaggerItem(tier, 1, (float) -1.5, new Item.Properties(), cloakLevel));
    }
    public static final RegistryObject<Item> STONE_DAGGER = registerDagger("stone_dagger", Tiers.STONE, 1);
    public static final RegistryObject<Item> IRON_DAGGER = registerDagger("iron_dagger", Tiers.IRON, 2);
    public static final RegistryObject<Item> GOLD_DAGGER = registerDagger("gold_dagger", Tiers.GOLD, 3);
    public static final RegistryObject<Item> DIAMOND_DAGGER = registerDagger("diamond_dagger", Tiers.DIAMOND, 3);
    public static final RegistryObject<Item> NETHERITE_DAGGER = registerDagger("netherite_dagger", Tiers.NETHERITE, 4);
    public static final List<RegistryObject<Item>> DAGGERS = List.of(STONE_DAGGER, IRON_DAGGER, GOLD_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER);

    // Регистрация катан
    public static final RegistryObject<Item> STONE_KATANA = registerWeapon("stone_katana", Tiers.STONE);
    public static final RegistryObject<Item> IRON_KATANA = registerWeapon("iron_katana", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_KATANA = registerWeapon("gold_katana", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_KATANA = registerWeapon("diamond_katana", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_KATANA = registerWeapon("netherite_katana", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> KATANAS = List.of(STONE_KATANA, IRON_KATANA, GOLD_KATANA, DIAMOND_KATANA, NETHERITE_KATANA);

    // Регистрация катаров
    public static final RegistryObject<Item> STONE_KATAR = registerWeapon("stone_katar", Tiers.STONE);
    public static final RegistryObject<Item> IRON_KATAR = registerWeapon("iron_katar", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_KATAR = registerWeapon("gold_katar", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_KATAR = registerWeapon("diamond_katar", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_KATAR = registerWeapon("netherite_katar", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> KATARS = List.of(STONE_KATAR, IRON_KATAR, GOLD_KATAR, DIAMOND_KATAR, NETHERITE_KATAR);

    // Регистрация древних мечей
    public static final RegistryObject<Item> STONE_OLDSWORD = registerWeapon("stone_oldsword", Tiers.STONE);
    public static final RegistryObject<Item> IRON_OLDSWORD = registerWeapon("iron_oldsword", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_OLDSWORD = registerWeapon("gold_oldsword", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_OLDSWORD = registerWeapon("diamond_oldsword", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_OLDSWORD = registerWeapon("netherite_oldsword", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> OLDSWORDS = List.of(STONE_OLDSWORD, IRON_OLDSWORD, GOLD_OLDSWORD, DIAMOND_OLDSWORD, NETHERITE_OLDSWORD);

    // Регистрация сабель
    public static final RegistryObject<Item> STONE_SABER = registerWeapon("stone_saber", Tiers.STONE);
    public static final RegistryObject<Item> IRON_SABER = registerWeapon("iron_saber", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_SABER = registerWeapon("gold_saber", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_SABER = registerWeapon("diamond_saber", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_SABER = registerWeapon("netherite_saber", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> SABERS = List.of(STONE_SABER, IRON_SABER, GOLD_SABER, DIAMOND_SABER, NETHERITE_SABER);

    // Регистрация кос
    private static RegistryObject<Item> registerScythe(String name, Tiers tier, int bleedingLevel) {
        return ITEMS.register(name, () -> new ScytheItem(tier, 3, (float) -2.9, new Item.Properties(), bleedingLevel));
    }
    public static final RegistryObject<Item> STONE_SCYTHE = registerScythe("stone_scythe", Tiers.STONE, 1);
    public static final RegistryObject<Item> IRON_SCYTHE = registerScythe("iron_scythe", Tiers.IRON, 2);
    public static final RegistryObject<Item> GOLD_SCYTHE = registerScythe("gold_scythe", Tiers.GOLD, 3);
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerScythe("diamond_scythe", Tiers.DIAMOND, 3);
    public static final RegistryObject<Item> NETHERITE_SCYTHE = registerScythe("netherite_scythe", Tiers.NETHERITE, 4);
    public static final List<RegistryObject<Item>> SCYTHES = List.of(STONE_SCYTHE, IRON_SCYTHE, GOLD_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE);

    // Регистрация копий
    public static final RegistryObject<Item> STONE_SPEAR = registerWeapon("stone_spear", Tiers.STONE);
    public static final RegistryObject<Item> IRON_SPEAR = registerWeapon("iron_spear", Tiers.IRON);
    public static final RegistryObject<Item> GOLD_SPEAR = registerWeapon("gold_spear", Tiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_SPEAR = registerWeapon("diamond_spear", Tiers.DIAMOND);
    public static final RegistryObject<Item> NETHERITE_SPEAR = registerWeapon("netherite_spear", Tiers.NETHERITE);
    public static final List<RegistryObject<Item>> SPEARS = List.of(STONE_SPEAR, IRON_SPEAR, GOLD_SPEAR, DIAMOND_SPEAR, NETHERITE_SPEAR);
}