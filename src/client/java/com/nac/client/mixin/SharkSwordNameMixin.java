package com.nac.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.nac.client.SharkSwords;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Overrides the item's display name everywhere it's asked for (tooltip title,
 * hotbar "selected item" popup, death messages, etc.) instead of only the
 * inventory tooltip lines, since those all funnel through Item#getName.
 */
@Mixin(Item.class)
public class SharkSwordNameMixin {
	@Inject(at = @At("HEAD"), method = "getName(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;", cancellable = true)
	private void useSharkSwordName(ItemStack stack, CallbackInfoReturnable<Component> cir) {
		SharkSwords.SwordSkin skin = SharkSwords.get(stack);

		if (skin != null) {
			cir.setReturnValue(skin.name());
		}
	}
}
