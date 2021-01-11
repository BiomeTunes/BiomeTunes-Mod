package com.hampushallkvist.biometunes.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerPlayerEntity;

public interface OnPlayerJoin {
	public static final Event<OnPlayerJoin> EVENT = EventFactory.createArrayBacked(OnPlayerJoin.class, 
		(listeners) -> {
			return (clientConnection, serverPlayerEntity) -> {
				for (OnPlayerJoin event : listeners)
					event.onPlayerJoin(clientConnection, serverPlayerEntity);
			};
		}
	);

	void onPlayerJoin(ClientConnection clientConnection, ServerPlayerEntity serverPlayerEntity);
}
