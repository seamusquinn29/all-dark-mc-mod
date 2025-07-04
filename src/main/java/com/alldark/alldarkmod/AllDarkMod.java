package com.alldark.alldarkmod;

import com.alldark.alldarkmod.core.registries.ModBiomeModifiers;
import com.alldark.alldarkmod.hunger.HungerManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AllDarkMod.MOD_ID)
public class AllDarkMod {
    public static final String MOD_ID = "alldarkmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public AllDarkMod(IEventBus modEventBus) {
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the deferred registry
        ModBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }
}