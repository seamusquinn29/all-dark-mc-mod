package com.alldark.alldarkmod.hunger;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class HungerManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int HUNGER_TICK_RATE = 600; // Every 30 seconds (20 ticks per second * 30)
    private static final int HUNGER_AMOUNT = 1; // Amount of hunger to drain
    
    private static int tickCounter = 0;
    
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer serverPlayer) {
            // Check if player is in Deep Dark biome
            if (serverPlayer.level().getBiome(serverPlayer.blockPosition()).is(Biomes.DEEP_DARK)) {
                tickCounter++;
                
                // Drain hunger every HUNGER_TICK_RATE ticks
                if (tickCounter >= HUNGER_TICK_RATE) {
                    tickCounter = 0;
                    
                    FoodData foodData = serverPlayer.getFoodData();
                    int currentFoodLevel = foodData.getFoodLevel();
                    
                    // Only drain hunger if player has food
                    if (currentFoodLevel > 0) {
                        foodData.setFoodLevel(Math.max(0, currentFoodLevel - HUNGER_AMOUNT));
                        
                        // Send hunger update to client
                        serverPlayer.connection.send(new net.minecraft.network.protocol.game.ClientboundSetHealthPacket(
                            serverPlayer.getHealth(), 
                            foodData.getFoodLevel(), 
                            foodData.getSaturationLevel()
                        ));
                        
                        LOGGER.debug("Drained hunger from player " + serverPlayer.getName().getString() + 
                                   " in Deep Dark biome. New food level: " + foodData.getFoodLevel());
                    }
                }
            } else {
                // Reset counter when not in Deep Dark
                tickCounter = 0;
            }
        }
    }
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // Global server tick counter for hunger management
            // This ensures consistent timing across all players
        }
    }
} 