package com.alldark.alldarkmod.core.registries;

import com.alldark.alldarkmod.AllDarkMod;
import com.alldark.alldarkmod.worldgen.biome.MobSpawnBiomeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AllDarkMod.MOD_ID);

    public static final RegistryObject<Codec<MobSpawnBiomeModifier>> MOB_SPAWN_MODIFIER =
            BIOME_MODIFIER_SERIALIZERS.register("mob_spawns", () ->
                    RecordCodecBuilder.create(builder -> builder.group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(MobSpawnBiomeModifier::biomes),
                            Codec.list(MobSpawnSettings.SpawnerData.CODEC).fieldOf("spawners").forGetter(MobSpawnBiomeModifier::spawners)
                    ).apply(builder, MobSpawnBiomeModifier::new)));
} 