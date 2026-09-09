package com.nac.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.nac.sounds.NacSoundEvents;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

@Mixin(Item.class)
public class SwordHitSoundMixin {
	@Inject(at = @At("HEAD"), method = "hurtEnemy(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)V", cancellable = false)
	private void playBlahajHitSound(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfo ci) {
		if (!(attacker instanceof Player)) {
			return;
		}

		SoundEvent sound = resolveSound(stack);
		if (sound == null) {
			return;
		}

		Vec3 pos = target.position();
		attacker.level().playSound(
				null,
				pos.x, pos.y, pos.z,
				sound,
				SoundSource.PLAYERS,
				1.0f,
				1.0f + (attacker.getRandom().nextFloat() - 0.5f) * 0.2f
		);
	}

	private static SoundEvent resolveSound(ItemStack stack) {
		if (stack.is(Items.WOODEN_SWORD)) return NacSoundEvents.BLAHAJ_HIT_WOOD;
		if (stack.is(Items.STONE_SWORD)) return NacSoundEvents.BLAHAJ_HIT_STONE;
		if (stack.is(Items.IRON_SWORD)) return NacSoundEvents.BLAHAJ_HIT_IRON;
		if (stack.is(Items.GOLDEN_SWORD)) return NacSoundEvents.BLAHAJ_HIT_GOLD;
		if (stack.is(Items.DIAMOND_SWORD)) return NacSoundEvents.BLAHAJ_HIT_DIAMOND;
		if (stack.is(Items.NETHERITE_SWORD)) return NacSoundEvents.BLAHAJ_HIT_NETHERITE;
		// Copper sword: adjust this to your actual Item reference if it's from
		// vanilla (newer MC versions) or your own registered item.
		if (stack.is(Items.COPPER_SWORD)) return NacSoundEvents.BLAHAJ_HIT_COPPER;
		return null;
	}
}
