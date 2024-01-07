package xyz.nierennakker.kaarten.client;

import com.mojang.math.Axis;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import xyz.nierennakker.kaarten.Card;
import xyz.nierennakker.kaarten.CardKind;
import xyz.nierennakker.kaarten.CardValue;
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

        for (var kind : CardKind.values()) {
            for (var value : CardValue.values()) {
                this.drawCard(graphics, new Card(kind, value), value.ordinal() * 23, kind.ordinal() * 31, 1f);
            }
        }
    }

    private void drawCard(GuiGraphics graphics, Card card, int x, int y, float scale) {
        graphics.pose().pushPose();
        graphics.pose().scale(scale, scale, scale);
        graphics.blit(GameTableScreen.CARD, x, y, 0, 0, 23, 31, 32, 32);
        graphics.blit(GameTableScreen.CARD, x + 8, y + 12, 23, card.kind().ordinal() * 7, 7, 7, 32, 32);
        graphics.drawString(this.font, card.value().display, x + 3, y + 3, 0x000000, false);
        graphics.pose().translate(x + 20f, y + 28f, 0f);
        graphics.pose().mulPose(Axis.ZP.rotationDegrees(180f));
        graphics.drawString(this.font, card.value().display, 0, 0, 0x000000, false);
        graphics.pose().popPose();
    }
}
