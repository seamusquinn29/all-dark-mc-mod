package com.alldark.alldarkmod.worldgen.biome;

import com.alldark.alldarkmod.core.registries.ModBiomeModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record MobSpawnBiomeModifier(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            for (MobSpawnSettings.SpawnerData spawner : this.spawners) {
                builder.getMobSpawnSettings().addSpawn(spawner.type().getCategory(), spawner);
            }
        }
    }

    @Override
    public MapCodec<MobSpawnBiomeModifier> codec() {
        return ModBiomeModifiers.MOB_SPAWN_MODIFIER.get();
    }
} 