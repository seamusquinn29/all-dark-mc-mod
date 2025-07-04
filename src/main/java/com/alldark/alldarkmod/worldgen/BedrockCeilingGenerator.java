package com.alldark.alldarkmod.worldgen;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.event.level.ChunkDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class BedrockCeilingGenerator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BEDROCK_CEILING_Y = 320;
    private static final int CEILING_THICKNESS = 5; // 5 blocks thick bedrock ceiling
    
    @SubscribeEvent
    public static void onChunkDataLoad(ChunkDataEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ChunkAccess chunk = event.getChunk();
            
            // Add bedrock ceiling at Y=320
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = BEDROCK_CEILING_Y; y < BEDROCK_CEILING_Y + CEILING_THICKNESS; y++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        chunk.setBlockState(pos, Blocks.BEDROCK.defaultBlockState(), false);
                    }
                }
            }
            
            // Mark chunk as modified
            chunk.setUnsaved(true);
        }
    }
    
    @SubscribeEvent
    public static void onChunkDataSave(ChunkDataEvent.Save event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ChunkAccess chunk = event.getChunk();
            
            // Ensure the chunk is marked as modified so bedrock ceiling is saved
            chunk.setUnsaved(true);
        }
    }
} 