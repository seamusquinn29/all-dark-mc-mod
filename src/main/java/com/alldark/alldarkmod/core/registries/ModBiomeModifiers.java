package com.alldark.alldarkmod.core.registries;

import com.alldark.alldarkmod.AllDarkMod;
import com.alldark.alldarkmod.worldgen.biome.MobSpawnBiomeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(Registries.BIOME_MODIFIER_SERIALIZER, AllDarkMod.MOD_ID);

    public static final RegistryObject<Codec<MobSpawnBiomeModifier>> MOB_SPAWN_MODIFIER =
            BIOME_MODIFIER_SERIALIZERS.register("mob_spawns", () ->
                    RecordCodecBuilder.create(builder -> builder.group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(MobSpawnBiomeModifier::biomes),
                            MobSpawnSettings.SpawnerData.CODEC.listOf().fieldOf("spawners").forGetter(MobSpawnBiomeModifier::spawners)
                    ).apply(builder, MobSpawnBiomeModifier::new)));
} 