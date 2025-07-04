package com.alldark.alldarkmod.worldgen;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType.StructurePlacementTypeCodec;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType.StructurePlacementTypeCodec;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class AncientCityGenerator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceKey<Structure> ANCIENT_CITY = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation("ancient_city"));
    
    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            LOGGER.info("Setting up increased Ancient City generation...");
            
            // The Ancient City structure placement will be handled by the biome replacement
            // Since we're making the entire world Deep Dark, Ancient Cities will naturally spawn more frequently
            // We can also modify the structure placement settings if needed
        }
    }
    
    // Custom structure placement that increases Ancient City frequency
    public static class IncreasedAncientCityPlacement extends StructurePlacement {
        public IncreasedAncientCityPlacement() {
            super(
                new net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement(
                    24, // Reduced spacing (vanilla is 32) - increases frequency
                    8,  // Separation
                    net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement.SpreadType.LINEAR,
                    123456789 // Seed
                )
            );
        }
        
        @Override
        public boolean isStructureChunk(StructurePlacement.Context context) {
            // Increase the chance of Ancient City generation
            return context.random().nextFloat() < 0.8f; // 80% chance instead of vanilla's lower chance
        }
    }
} 