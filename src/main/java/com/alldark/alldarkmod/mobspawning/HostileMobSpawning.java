package com.alldark.alldarkmod.mobspawning;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class HostileMobSpawning {
    private static final Logger LOGGER = LogManager.getLogger();
    
    // List of hostile mobs that should spawn in Deep Dark
    private static final EntityType<?>[] HOSTILE_MOBS = {
        EntityType.ZOMBIE,
        EntityType.SKELETON,
        EntityType.SPIDER,
        EntityType.CAVE_SPIDER,
        EntityType.CREEPER,
        EntityType.ENDERMAN,
        EntityType.WITCH,
        EntityType.SLIME,
        EntityType.SILVERFISH,
        EntityType.WITHER_SKELETON,
        EntityType.BLAZE,
        EntityType.GUARDIAN,
        EntityType.ELDER_GUARDIAN,
        EntityType.DROWNED,
        EntityType.HUSK,
        EntityType.STRAY,
        EntityType.PHANTOM,
        EntityType.GHAST,
        EntityType.MAGMA_CUBE,
        EntityType.HOGLIN,
        EntityType.ZOMBIFIED_PIGLIN,
        EntityType.PIGLIN,
        EntityType.PIGLIN_BRUTE,
        EntityType.VINDICATOR,
        EntityType.EVOKER,
        EntityType.VEX,
        EntityType.PILLAGER,
        EntityType.RAVAGER,
        EntityType.WARDEN // Keep the Warden as well
    };
    
    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            BlockPos pos = event.getPos();
            
            // Check if we're in a Deep Dark biome
            if (serverLevel.getBiome(pos).is(Biomes.DEEP_DARK)) {
                // Allow all hostile mobs to spawn in Deep Dark
                if (isHostileMob(event.getEntity().getType())) {
                    // Increase spawn rates for hostile mobs in Deep Dark
                    if (event.getSpawnType() == MobSpawnType.NATURAL) {
                        // 50% chance to allow the spawn (doubles spawn rate)
                        if (serverLevel.random.nextFloat() < 0.5f) {
                            event.setResult(Event.Result.ALLOW);
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            LOGGER.info("Setting up hostile mob spawning for Deep Dark biomes...");
            
            // Register spawn placements for hostile mobs in Deep Dark
            for (EntityType<?> mobType : HOSTILE_MOBS) {
                if (mobType instanceof EntityType<Mob> mobEntityType) {
                    // Override spawn placement rules for Deep Dark
                    SpawnPlacements.register(mobEntityType, 
                        SpawnPlacements.Type.ON_GROUND, 
                        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        (entityType, level, spawnType, pos, random) -> {
                            // Allow spawning in Deep Dark biomes
                            return level.getBiome(pos).is(Biomes.DEEP_DARK) && 
                                   Mob.checkMobSpawnRules(entityType, level, spawnType, pos, random);
                        });
                }
            }
        }
    }
    
    private static boolean isHostileMob(EntityType<?> entityType) {
        for (EntityType<?> hostileMob : HOSTILE_MOBS) {
            if (entityType == hostileMob) {
                return true;
            }
        }
        return false;
    }
    
    // Custom spawn settings for Deep Dark
    public static class DeepDarkSpawnSettings {
        public static MobSpawnSettings.Builder createDeepDarkSpawnSettings() {
            MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
            
            // Add hostile mobs to Deep Dark spawn settings
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 100, 4, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 20, 1, 3));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 10, 1, 2));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 1, 1));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 40, 1, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.SILVERFISH, 60, 8, 8));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 10, 3, 3));
            builder.addSpawn(MobSpawnSettings.MobSpawnType.MONSTER, 
                new MobSpawnSettings.SpawnerData(EntityType.WARDEN, 1, 1, 1));
            
            return builder;
        }
    }
} 