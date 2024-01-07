package xyz.nierennakker.kaarten.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import xyz.nierennakker.kaarten.Kaarten;

public record OpenTablePayload(BlockPos pos) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(Kaarten.MOD_ID, "open_table");

    public OpenTablePayload(FriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public ResourceLocation id() {
        return OpenTablePayload.ID;
    }
}
