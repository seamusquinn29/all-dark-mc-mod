package com.alldark.alldarkmod.core.registries;

import com.alldark.alldarkmod.AllDarkMod;
import com.alldark.alldarkmod.worldgen.biome.MobSpawnBiomeModifier;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AllDarkMod.MOD_ID);

    public static final RegistryObject<MapCodec<MobSpawnBiomeModifier>> MOB_SPAWN_MODIFIER =
            BIOME_MODIFIER_SERIALIZERS.register("mob_spawns", () ->
                    RecordCodecBuilder.mapCodec(builder -> builder.group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(MobSpawnBiomeModifier::biomes),
                            MobSpawnSettings.SpawnerData.CODEC.listOf().fieldOf("spawners").forGetter(MobSpawnBiomeModifier::spawners)
                    ).apply(builder, MobSpawnBiomeModifier::new)));
} 