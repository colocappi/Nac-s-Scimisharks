package com.nac.client;

import com.nac.sounds.NacSoundEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NacSScimisharksClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        NacSoundEvents.touch();

        AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            // Ensure this only triggers locally on your machine
            if (level.isClientSide()) {
                ItemStack stack = player.getItemInHand(hand);
                SoundEvent sound = resolveSound(stack);

                if (sound != null) {
                    level.playLocalSound(
                            entity.getX(), entity.getY(), entity.getZ(),
                            sound,
                            SoundSource.PLAYERS,
                            1.0f,
                            1.0f + (player.getRandom().nextFloat() - 0.5f) * 0.2f,
                            false
                    );
                }
            }
            return InteractionResult.PASS;
        });
    }

    private SoundEvent resolveSound(ItemStack stack) {
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