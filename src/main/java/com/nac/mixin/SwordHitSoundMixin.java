package com.nac.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.nac.NacSScimisharks;
import com.nac.sounds.NacSoundEvents;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

@Mixin(Player.class)
public class SwordHitSoundMixin {
	@Inject(at = @At("HEAD"), method = "attack(Lnet/minecraft/world/entity/Entity;)V", cancellable = false)
	private void playBlahajHitSound(Entity entity, CallbackInfo ci) {
		Player self = (Player) (Object) this;

		NacSScimisharks.LOGGER.info("SwordHitSoundMixin fired! entity={}", entity);

		ItemStack stack = self.getWeaponItem();
		SoundEvent sound = resolveSound(stack);
		if (sound == null) {
			return;
		}

		Vec3 pos = entity.position();
		self.level().playSound(
				null,
				pos.x, pos.y, pos.z,
				sound,
				SoundSource.MASTER,
				1.0f,
				1.0f + (self.getRandom().nextFloat() - 0.5f) * 0.2f
		);
	}

	private static SoundEvent resolveSound(ItemStack stack) {
		if (stack.is(Items.WOODEN_SWORD)) return NacSoundEvents.BLAHAJ_HIT_WOOD;
		if (stack.is(Items.STONE_SWORD)) return NacSoundEvents.BLAHAJ_HIT_STONE;
		if (stack.is(Items.IRON_SWORD)) return NacSoundEvents.BLAHAJ_HIT_IRON;
		if (stack.is(Items.GOLDEN_SWORD)) return NacSoundEvents.BLAHAJ_HIT_GOLD;
		if (stack.is(Items.DIAMOND_SWORD)) return NacSoundEvents.BLAHAJ_HIT_DIAMOND;
		if (stack.is(Items.NETHERITE_SWORD)) return NacSoundEvents.BLAHAJ_HIT_NETHERITE;
		if (stack.is(Items.COPPER_SWORD)) return NacSoundEvents.BLAHAJ_HIT_COPPER;
		return null;
	}
}
