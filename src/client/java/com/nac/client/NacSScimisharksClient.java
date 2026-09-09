package com.nac.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;

public class NacSScimisharksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TooltipComponentCallback.EVENT.register(data -> data instanceof BlahajTooltipData
				? new BlahajTooltipComponent()
				: null);

		// Replace the vanilla name and add lore in the client tooltip without a mapped ItemStack mixin.
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			SharkSwords.SwordSkin skin = SharkSwords.get(stack);

			if (skin == null || lines.isEmpty()) {
				return;
			}

			lines.set(0, skin.name());
			lines.add(1, skin.lore());
		});
	}
}
