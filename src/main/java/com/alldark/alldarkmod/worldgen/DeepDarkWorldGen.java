package com.alldark.alldarkmod.worldgen;

import com.alldark.alldarkmod.AllDarkMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.common.world.ForgeChunkGenerator;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class DeepDarkWorldGen {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceKey<Biome> DEEP_DARK_KEY = Biomes.DEEP_DARK;

    public static void register() {
        // This method is now obsolete as event listeners are registered automatically.
        // Kept for now to avoid breaking the main mod file, will be removed later.
        LOGGER.info("Deep Dark world generation registered.");
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            // We only want to modify the overworld
            if (serverLevel.dimension() == Level.OVERWORLD) {
                LOGGER.info("Overriding overworld biome source with Deep Dark biome source.");
                ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();

                // To properly replace the biome source, we need to create a new ChunkGenerator
                // with our custom biome source. This is a complex operation and is best
                // handled through a custom dimension type or by using more advanced hooks.

                // For now, let's log that we would perform the replacement here.
                // A proper implementation requires a custom Codec for the BiomeSource.
                LOGGER.warn("Biome source replacement is a complex operation and is not fully implemented in this event. A custom dimension is the recommended approach.");
            }
        }
    }

    public static class DeepDarkBiomeSource extends BiomeSource {
        private final Holder<Biome> deepDarkBiome;

        // Custom codec for our biome source
        public static final Codec<DeepDarkBiomeSource> CODEC = Registry.BIOME.holderByNameCodec()
            .fieldOf("biome")
            .xmap(DeepDarkBiomeSource::new, (source) -> source.deepDarkBiome)
            .stable();

        public DeepDarkBiomeSource(Holder<Biome> biome) {
            super(Stream.of(biome));
            this.deepDarkBiome = biome;
        }

        @Override
        protected Codec<? extends BiomeSource> codec() {
            return CODEC;
        }

        @Override
        public Holder<Biome> getNoiseBiome(int x, int y, int z, net.minecraft.world.level.biome.Climate.Sampler sampler) {
            return deepDarkBiome;
        }
    }
} 