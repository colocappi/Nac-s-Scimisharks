package com.nac.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class BlahajTooltipComponent implements ClientTooltipComponent {
    private static final Identifier IMAGE = Identifier.fromNamespaceAndPath("nacs-scimisharks", "textures/gui/blahaj.png");
    private static final int WIDTH = 120;
    private static final int HEIGHT = 160;

    @Override
    public int getHeight(Font font) {
        return HEIGHT;
    }

    @Override
    public int getWidth(Font font) {
        return WIDTH;
    }

    @Override
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics graphics) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE, x, y, 0.0f, 0.0f, WIDTH, HEIGHT, WIDTH, HEIGHT);
    }
}