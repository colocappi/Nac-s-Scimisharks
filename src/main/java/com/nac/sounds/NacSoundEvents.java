package com.nac.sounds;

import com.nac.NacSScimisharks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class NacSoundEvents {
	public static final SoundEvent BLAHAJ_HIT = registerSound("entity.blahaj.hit");
	public static final SoundEvent BLAHAJ_HIT_WOOD = registerSound("entity.blahaj.hit_wood");
	public static final SoundEvent BLAHAJ_HIT_STONE = registerSound("entity.blahaj.hit_stone");
	public static final SoundEvent BLAHAJ_HIT_IRON = registerSound("entity.blahaj.hit_iron");
	public static final SoundEvent BLAHAJ_HIT_COPPER = registerSound("entity.blahaj.hit_copper");
	public static final SoundEvent BLAHAJ_HIT_GOLD = registerSound("entity.blahaj.hit_gold");
	public static final SoundEvent BLAHAJ_HIT_DIAMOND = registerSound("entity.blahaj.hit_diamond");
	public static final SoundEvent BLAHAJ_HIT_NETHERITE = registerSound("entity.blahaj.hit_netherite");

	private static SoundEvent registerSound(String id) {
		return Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				NacSScimisharks.id(id),
				SoundEvent.createVariableRangeEvent(NacSScimisharks.id(id))
		);
	}

	/**
	 * Does nothing on its own -- but calling this from the mod's main
	 * onInitialize() forces this class's static fields (and therefore
	 * all registerSound calls above) to run during mod startup, while
	 * the registry is still open, instead of lazily the first time a
	 * sword hit triggers the mixin (which happens after the registry
	 * is frozen and crashes the server).
	 */
	public static void touch() {
	}
}
