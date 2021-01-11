package com.hampushallkvist.biometunes.accessors;

import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.Registry;

public abstract class AccessorFabric {
	protected UUID playerUUID;
	protected PlayerEntity player;

	public AccessorFabric(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}

	public abstract boolean updatePlayerInstance();

	public abstract void playSound(String sound, float volume, float pitch, boolean fade);
	public abstract void stopSound(String sound, boolean fade);

	public double getX() { return player.getX(); }
	public double getY() { return player.getY(); }
	public double getZ() { return player.getZ(); }

	public boolean isSubmergedInWater() { return player.isSubmergedIn(FluidTags.WATER); }

	public String getBiome(int x, int y, int z) {
		Biome biome = player.getEntityWorld().getBiomeAccess().getBiome(new BlockPos(x, y, z));

		Registry<Biome> registry = player.getEntityWorld().getRegistryManager().get(Registry.BIOME_KEY);
		return registry.getId(biome).toString();
	}

	public int getBlockLight(int x, int y, int z) {
		return player.getEntityWorld().getLightLevel(LightType.BLOCK, new BlockPos(x, y, z));
	}

	public int getSkyLight(int x, int y, int z) {
		return player.getEntityWorld().getLightLevel(LightType.SKY, new BlockPos(x, y, z));
	}

	public double getTemperature(int x, int y, int z) {
		return player.getEntityWorld().getBiomeAccess().getBiome(new BlockPos(x, y, z)).getTemperature();
	}

	public double getHumidity(int x, int y, int z) {
		return player.getEntityWorld().getBiomeAccess().getBiome(new BlockPos(x, y, z)).getDownfall();
	}
}
