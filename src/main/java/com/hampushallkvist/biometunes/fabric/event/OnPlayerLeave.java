package com.hampushallkvist.biometunes.fabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface OnPlayerLeave {
	public static final Event<OnPlayerLeave> EVENT = EventFactory.createArrayBacked(OnPlayerLeave.class,
			(listeners) -> {
				return (serverPlayerEntity) -> {
					for (OnPlayerLeave event : listeners)
						event.onPlayerLeave(serverPlayerEntity);
				};
			});

	void onPlayerLeave(ServerPlayerEntity serverPlayerEntity);
}
