package com.alldark.alldarkmod.worldgen;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraftforge.common.world.ForgeChunkGenerator;
import net.minecraftforge.event.level.ChunkDataEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class DeepDarkWorldGen {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceKey<Biome> DEEP_DARK = Biomes.DEEP_DARK;
    
    public static void register() {
        LOGGER.info("Registering Deep Dark world generation...");
    }
    
    @SubscribeEvent
    public static void onChunkDataLoad(ChunkDataEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ChunkAccess chunk = event.getChunk();
            
            // Replace all biomes in the chunk with Deep Dark
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = serverLevel.getMinBuildHeight(); y < serverLevel.getMaxBuildHeight(); y++) {
                        chunk.setBiome(new net.minecraft.core.BlockPos(x, y, z), 
                            serverLevel.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(DEEP_DARK));
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onChunkDataSave(ChunkDataEvent.Save event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ChunkAccess chunk = event.getChunk();
            
            // Ensure the chunk is marked as modified so biome changes are saved
            chunk.setUnsaved(true);
        }
    }
    
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            LOGGER.info("Setting up Deep Dark world generation for level: " + serverLevel.dimension().location());
            
            // Register custom chunk generator that ensures Deep Dark biomes
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
            if (chunkGenerator instanceof ForgeChunkGenerator forgeGenerator) {
                // The biome replacement will be handled by the chunk events
                LOGGER.info("Forge chunk generator detected, Deep Dark biomes will be applied");
            }
        }
    }
    
    // Custom biome source that always returns Deep Dark
    public static class DeepDarkBiomeSource extends net.minecraft.world.level.biome.BiomeSource {
        private final Holder<Biome> deepDarkBiome;
        
        public DeepDarkBiomeSource(Registry<Biome> biomeRegistry) {
            super(List.of(biomeRegistry.getHolderOrThrow(DEEP_DARK)));
            this.deepDarkBiome = biomeRegistry.getHolderOrThrow(DEEP_DARK);
        }
        
        @Override
        public Holder<Biome> getNoiseBiome(int x, int y, int z, net.minecraft.world.level.biome.Climate.Sampler sampler) {
            return deepDarkBiome;
        }
        
        @Override
        protected net.minecraft.world.level.biome.Climate.Sampler climateSampler() {
            return net.minecraft.world.level.biome.Climate.empty();
        }
    }
} 