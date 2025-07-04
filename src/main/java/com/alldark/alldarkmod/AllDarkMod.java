package com.alldark.alldarkmod;

import com.alldark.alldarkmod.worldgen.DeepDarkWorldGen;
import com.alldark.alldarkmod.mobspawning.HostileMobSpawning;
import com.alldark.alldarkmod.hunger.HungerManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AllDarkMod.MOD_ID)
public class AllDarkMod {
    public static final String MOD_ID = "alldarkmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public AllDarkMod() {
        LOGGER.info("Initializing All Dark Mod...");
        
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        // Register world generation
        DeepDarkWorldGen.register();
        
        // Register mob spawning modifications
        MinecraftForge.EVENT_BUS.register(new HostileMobSpawning());
        
        // Register hunger modifications
        MinecraftForge.EVENT_BUS.register(new HungerManager());
        
        LOGGER.info("All Dark Mod initialized successfully!");
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up All Dark Mod...");
        
        // Some preinit code
        event.enqueueWork(() -> {
            LOGGER.info("All Dark Mod setup complete!");
        });
    }
} 