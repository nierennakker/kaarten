package xyz.nierennakker.kaarten.client;

import com.mojang.math.Axis;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import xyz.nierennakker.kaarten.Kaarten;

public class GameTableScreen extends Screen {
    public static final ResourceLocation CARD = new ResourceLocation(Kaarten.MOD_ID, "textures/gui/card.png");

    private final BlockPos pos;

    public GameTableScreen(BlockPos pos) {
        super(GameNarrator.NO_TITLE);

        this.pos = pos;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font, this.pos.toShortString(), mouseX, mouseY, 0xffffff);

        for (var i = 0; i < 4; i++) {
            this.drawCard(graphics, "A", i, i * 50, 0, 2f);
        }
    }

    private void drawCard(GuiGraphics graphics, String value, int type, int x, int y, float scale) {
        graphics.pose().pushPose();
        graphics.pose().scale(scale, scale, scale);
        graphics.blit(GameTableScreen.CARD, x, y, 0, 0, 23, 31, 32, 32);
        graphics.blit(GameTableScreen.CARD, x + 8, y + 12, 23, type * 7, 7, 7, 32, 32);
        graphics.drawString(this.font, value, x + 3, y + 3, 0x000000, false);
        graphics.pose().translate(x + 20f, y + 28f, 0f);
        graphics.pose().mulPose(Axis.ZP.rotationDegrees(180f));
        graphics.drawString(this.font, value, 0, 0, 0x000000, false);
        graphics.pose().popPose();
    }
}
