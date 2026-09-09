package com.nac.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;

public class ClientBlahajTooltipData implements ClientTooltipComponent {
    private final BlahajTooltipData data;

    public ClientBlahajTooltipData(BlahajTooltipData data) {
        this.data = data;
    }

    @Override
    public int getHeight(Font font) {
        return 20;
    }

    @Override
    public int getWidth(Font font) {
        return 100;
    }

    @Override
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics graphics) {
        // Add your custom rendering/blit logic here if needed
    }
}