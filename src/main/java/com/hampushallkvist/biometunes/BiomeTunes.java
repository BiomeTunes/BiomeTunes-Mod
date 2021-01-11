package com.hampushallkvist.biometunes;

import java.util.UUID;

import com.hampushallkvist.biometunes.accessors.AccessorServer;
import com.hampushallkvist.biometunes.fabric.event.OnPlayerJoin;
import com.hampushallkvist.biometunes.accessors.AccessorFabric;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class BiomeTunes implements ModInitializer, ClientModInitializer {
	private static final Logger LOGGER = LogManager.getLogger();
	private static BiomeTunesLogger logger;

	public static MinecraftServer server;
	public static BiomeTunes instance;

	private int ticker;
	private long lastTick;

	private ModMode modMode = ModMode.CLIENT;

	@Override
	public void onInitialize() {
		System.out.println("YO THIS IS A TEST");
		
		onInitializeCommon();
		
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			BiomeTunes.server = server;
			tick();
		});

		OnPlayerJoin.EVENT.register((connection, player) -> {
			// register player
			BiomeTunes.addPlayer(player.getUuid(), new AccessorServer(player.getUuid()));
		});
	}

	@Override
	public void onInitializeClient() {
		onInitializeCommon();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			// Don't play sounds while not in world
			if (client.isIntegratedServerRunning() || client.world == null || client.player == null
				&& modMode == ModMode.CLIENT) {
				logger.log("Mod mode set to server side, client side disabled");
				modMode = ModMode.SERVER;
			}

			if (modMode == ModMode.CLIENT)
				tick();
		});
	}

	public void onInitializeCommon() {
		instance = this;
		logger = new BiomeTunesLogger(LOGGER::info, LOGGER::error);
	}

	private void tick() {
		// Skip updating when minecraft is recovering
		if (System.currentTimeMillis() - lastTick < 25) 
			return;

		lastTick = System.currentTimeMillis();

		ticker++;
		if (ticker >= 20) {
			ticker = 0;
		}
	}

	public static void addPlayer(UUID player, AccessorFabric accessor) {
		
	}
}
