package com.nac;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

import com.nac.sounds.NacSoundEvents;

public class NacSScimisharks implements ModInitializer {
	public static final String MOD_ID = "nacs-scimisharks";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// Force NacSoundEvents' static fields to initialize now, while the
		// sound registry is still open, instead of lazily on the first
		// sword hit (which happens after the registry is frozen and
		// crashes the server).
		NacSoundEvents.touch();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, Objects.requireNonNull(path, "path"));
	}
}
