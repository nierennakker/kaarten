package xyz.nierennakker.cards.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.nierennakker.cards.Cards;

public class GameTableBlockEntity extends BlockEntity {
    public GameTableBlockEntity(BlockPos pos, BlockState state) {
        super(Cards.GAME_TABLE_ENTITY.get(), pos, state);
    }
}
