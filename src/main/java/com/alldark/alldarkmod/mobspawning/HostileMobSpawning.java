package com.alldark.alldarkmod.mobspawning;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class HostileMobSpawning {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        // We want to add spawns to the Deep Dark biome
        if (event.getName() != null && event.getName().equals(Biomes.DEEP_DARK.location())) {
            LOGGER.info("Modifying mob spawns for Deep Dark biome.");

            // Add a wide variety of hostile mobs.
            // Weights are balanced to provide variety without overwhelming the player.
            // Zombie, Skeleton, Creeper, Spider are already common.
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 2));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.WITCH, 8, 1, 1));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 2, 4));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 70, 1, 3));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.SILVERFISH, 30, 1, 2));

            // Unnatural/Nether spawns for a more dangerous world
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 15, 1, 2));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.STRAY, 40, 1, 3));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.HUSK, 40, 1, 3));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 20, 1, 2));

            // Really mean spawns, low weight
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.GHAST, 3, 1, 1));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 5, 1, 2));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 5, 1, 2));
            event.getSpawns().addSpawn(MobSpawnSettings.MobSpawnType.MONSTER,
                    new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 10, 1, 3));
        }
    }
} 