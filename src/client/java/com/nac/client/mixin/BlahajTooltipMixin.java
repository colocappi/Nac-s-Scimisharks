package com.nac.client.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.nac.client.BlahajTooltipData;
import com.nac.client.SharkSwords;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(Item.class)
public class BlahajTooltipMixin {
    @Inject(at = @At("HEAD"), method = "getTooltipImage(Lnet/minecraft/world/item/ItemStack;)Ljava/util/Optional;", cancellable = true)
    private void addBlahajTooltipImage(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        SharkSwords.SwordSkin skin = SharkSwords.get(stack);

        if (stack.is(Items.DIAMOND_SWORD) && skin != null && "Blåhaj".equals(skin.name().getString())) {
            cir.setReturnValue(Optional.of(new BlahajTooltipData()));
        }
    }
}