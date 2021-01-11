package com.hampushallkvist.biometunes.accessors;

import java.util.UUID;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;

public class AccessorClient extends AccessorFabric {
	public AccessorClient(UUID playerUUID) {
		super(playerUUID);
	}

	@SuppressWarnings("resource")
	@Override
	public boolean updatePlayerInstance() {
		player = MinecraftClient.getInstance().player;
		return player != null;
	}

	@Override
	public void playSound(String sound, float volume, float pitch, boolean fade) {
		MinecraftClient.getInstance().getSoundManager()
			.play(new PositionedSoundInstance(
					new Identifier(sound), 
					SoundCategory.MUSIC, 
					volume, pitch, false, 0,
					SoundInstance.AttenuationType.LINEAR, 
					player.getX(), player.getY(), player.getZ(), 
					false
				)
			);
	}

	@Override
	public void stopSound(String sound, boolean fade) {
		MinecraftClient.getInstance().getSoundManager().stopSounds(new Identifier(sound), SoundCategory.MUSIC);	
	}
}
