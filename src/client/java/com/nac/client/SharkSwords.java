package com.nac.client;

import java.util.Map;
import java.util.Objects;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * The shark sword name/lore table.
 *
 * <p>These used to be applied by a datapack ({@code shark_swords:apply_names}), which only worked in
 * singleplayer: a datapack inside a mod jar is loaded by whoever runs the <em>server</em>, so joining
 * someone else's server never ran it. Everything here is purely visual and client-side instead, so it
 * works on any server without the server knowing anything about it.
 */
public final class SharkSwords {
	/** A name and lore line to draw in place of a vanilla sword's. */
	public record SwordSkin(Component name, Component lore) {
	}

	@SuppressWarnings("null")
	private static final Map<Item, SwordSkin> SKINS = Map.of(
			Items.WOODEN_SWORD, skin(
					"Sawtooth Blade", 0x8B5A2B,
					"Cut from the bone-bright edge of the shallows.", 0xD8B08C),
			Items.STONE_SWORD, skin(
					"Hammerhead Cleaver", 0x8A8A8A,
					"Built to split rock and reef alike.", 0xC7C7C7),
			Items.IRON_SWORD, skin(
					"Whitefang", 0xD7D7D7,
					"A blade that knows the cold hunger of the deep.", 0xE7E7E7),
			Items.GOLDEN_SWORD, skin(
					"Lemon Edge", 0xF6D64A,
					"Brilliant as a stray sun on open water.", 0xF9E98A),
			Items.COPPER_SWORD, skin(
					"Nurse's Scalpel", 0xD9774E,
					"A careful edge for the patient hunters of the tide.", 0xF3B08A),
			Items.DIAMOND_SWORD, skin(
					"Blåhaj", 0x5AA9FF,
					"blahaj :3", 0xA7D7FF),
			Items.NETHERITE_SWORD, skin(
					"Magmatic Fang", 0xB58BFF,
					"Wrought in magma, sharpened by a predator's hunger.", 0xD9C3FF));

	private SharkSwords() {
	}

	/**
	 * The skin to draw for {@code stack}, or {@code null} to leave the stack alone.
	 *
	 * <p>Stacks that already carry a name of their own are skipped, so an anvil rename, a name set by
	 * the server, and anything the old datapack already baked in all survive untouched.
	 */
	public static SwordSkin get(ItemStack stack) {
		SwordSkin skin = SKINS.get(stack.getItem());

		if (skin == null || stack.has(DataComponents.CUSTOM_NAME) || hasServerItemName(stack)) {
			return null;
		}

		return skin;
	}

	/**
	 * Whether something has deliberately overridden this stack's {@code item_name}.
	 *
	 * <p>Careful: since 1.21.9 <em>every</em> item is registered with an {@code item_name} component
	 * holding its normal name, so {@code stack.has(ITEM_NAME)} is always true and cannot be used to
	 * detect a rename. The only way to tell is to compare against the item's own default.
	 */
	private static boolean hasServerItemName(ItemStack stack) {
		Component itemName = stack.get(DataComponents.ITEM_NAME);

		return itemName != null && !itemName.equals(stack.getItem().components().get(DataComponents.ITEM_NAME));
	}

	private static SwordSkin skin(String name, int nameColor, String lore, int loreColor) {
		return new SwordSkin(
				Component.literal(Objects.requireNonNull(name)).withStyle(style(nameColor).withBold(true)),
				Component.literal(Objects.requireNonNull(lore)).withStyle(Objects.requireNonNull(style(loreColor))));
	}

	/**
	 * Vanilla italicises the names of items that carry a {@code custom_name}. We deliberately do not set
	 * one, so nothing would italicise these anyway, but the datapack spelled out {@code italic:false} and
	 * pinning it here keeps that true no matter what draws the text.
	 */
	private static Style style(int color) {
		return Style.EMPTY.withColor(TextColor.fromRgb(color)).withItalic(false);
	}
}
