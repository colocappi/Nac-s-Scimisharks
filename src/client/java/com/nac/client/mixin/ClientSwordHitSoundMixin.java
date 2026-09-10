package com.nac.client.mixin; // Ensure this matches your actual package path

import com.nac.sounds.NacSoundEvents;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class ClientSwordHitSoundMixin {

    @Inject(method = "attack", at = @At("HEAD"))
    private void onAttack(Player player, Entity targetEntity, CallbackInfo ci) {
        if (player.level().isClientSide()) {
            ItemStack stack = player.getMainHandItem();
            SoundEvent sound = null;

            if (stack.is(Items.WOODEN_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_WOOD;
            else if (stack.is(Items.STONE_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_STONE;
            else if (stack.is(Items.IRON_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_IRON;
            else if (stack.is(Items.GOLDEN_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_GOLD;
            else if (stack.is(Items.DIAMOND_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_DIAMOND;
            else if (stack.is(Items.NETHERITE_SWORD)) sound = NacSoundEvents.BLAHAJ_HIT_NETHERITE;

            if (sound != null) {
                player.level().playLocalSound(
                        targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(),
                        sound,
                        SoundSource.PLAYERS,
                        1.0f,
                        1.0f + (player.getRandom().nextFloat() - 0.5f) * 0.2f,
                        false
                );
            }
        }
    }
}