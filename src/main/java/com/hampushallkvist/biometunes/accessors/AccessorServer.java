package com.hampushallkvist.biometunes.accessors;

import java.util.UUID;

import com.hampushallkvist.biometunes.BiomeTunes;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundCategory;

public class AccessorServer extends AccessorFabric {
	private ServerPlayerEntity serverPlayer;

	public AccessorServer(UUID playerUUID) {
		super(playerUUID);
	}

	@Override
	public boolean updatePlayerInstance() {
		player = serverPlayer = BiomeTunes.server.getPlayerManager().getPlayer(playerUUID);
		return player != null;
	}

	@Override
	public void playSound(String sound, float volume, float pitch, boolean fade) {
		serverPlayer.networkHandler.sendPacket(
			new PlaySoundIdS2CPacket(
				new Identifier(sound), 
				SoundCategory.MUSIC, 
				player.getPos(), 
				volume, 
				pitch
			)
		);
	}

	@Override
	public void stopSound(String sound, boolean fade) {
		serverPlayer.networkHandler.sendPacket(
			new StopSoundS2CPacket(
				new Identifier(sound),
				SoundCategory.MASTER
			)
		);
	}
}
