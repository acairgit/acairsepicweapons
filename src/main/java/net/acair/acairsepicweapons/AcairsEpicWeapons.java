package net.acair.acairsepicweapons;

import net.acair.acairsepicweapons.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.mojang.logging.LogUtils;

@Mod(AcairsEpicWeapons.MOD_ID)
public class AcairsEpicWeapons {
    public static final String MOD_ID = "acairsepicweapons";

    static {
        LogUtils.getLogger();
    }

    public AcairsEpicWeapons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            ModItems.DAGGERS.forEach(dagger -> event.accept(dagger.get()));
            ModItems.SCYTHES.forEach(dagger -> event.accept(dagger.get()));
        }
    }
}
