package net.acair.acairsepicweapons.event;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

    public static void register() {
        // Регистрируем все события, связанные с кинжалом
        MinecraftForge.EVENT_BUS.register(DaggerEvents.class);
        MinecraftForge.EVENT_BUS.register(GreatSwordEvents.class);
    }
}
