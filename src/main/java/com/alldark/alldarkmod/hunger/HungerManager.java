package com.alldark.alldarkmod.hunger;

import com.alldark.alldarkmod.AllDarkMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = AllDarkMod.MOD_ID)
public class HungerManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int HUNGER_TICK_RATE = 600; // Every 30 seconds (20 ticks per second * 30)
    private static final int HUNGER_AMOUNT = 1; // Amount of hunger to drain

    // Use a Map to track ticks for each player individually
    private static final Map<UUID, Integer> playerTickCounters = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayer serverPlayer) {
            UUID playerUUID = serverPlayer.getUUID();

            // Check if player is in Deep Dark biome
            if (serverPlayer.level().getBiome(serverPlayer.blockPosition()).is(Biomes.DEEP_DARK)) {
                int ticks = playerTickCounters.getOrDefault(playerUUID, 0);
                ticks++;

                // Drain hunger every HUNGER_TICK_RATE ticks
                if (ticks >= HUNGER_TICK_RATE) {
                    ticks = 0; // Reset counter for this player

                    FoodData foodData = serverPlayer.getFoodData();
                    int currentFoodLevel = foodData.getFoodLevel();

                    // Only drain hunger if player has food
                    if (currentFoodLevel > 0) {
                        foodData.setFoodLevel(Math.max(0, currentFoodLevel - HUNGER_AMOUNT));

                        // The client should update automatically, but we can send a packet to be sure.
                        // The old packet was incorrect for just sending hunger.
                        // Let's rely on the vanilla sync for now, it's generally sufficient.

                        LOGGER.debug("Drained hunger from player {} in Deep Dark biome. New food level: {}",
                                   serverPlayer.getName().getString(), foodData.getFoodLevel());
                    }
                }
                playerTickCounters.put(playerUUID, ticks);
            } else {
                // Remove player from tracker if they are not in the deep dark
                playerTickCounters.remove(playerUUID);
            }
        }
    }
} 