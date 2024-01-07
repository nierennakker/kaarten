package xyz.nierennakker.cards;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.nierennakker.cards.block.GameTableBlock;
import xyz.nierennakker.cards.entity.block.GameTableBlockEntity;

import java.util.function.Supplier;

@Mod(Cards.MOD_ID)
public class Cards {
    public static final String MOD_ID = "cards";

    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cards.MOD_ID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cards.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Cards.MOD_ID);

    public static final DeferredBlock<Block> GAME_TABLE = Cards.BLOCKS.register("game_table", GameTableBlock::new);
    public static final DeferredItem<BlockItem> GAME_TABLE_ITEM = Cards.ITEMS.registerSimpleBlockItem(Cards.GAME_TABLE);
    public static final Supplier<BlockEntityType<GameTableBlockEntity>> GAME_TABLE_ENTITY = Cards.BLOCK_ENTITIES.register("game_table", () -> BlockEntityType.Builder.of(GameTableBlockEntity::new, Cards.GAME_TABLE.get()).build(null));

    public Cards(IEventBus bus) {
        bus.register(this);

        Cards.BLOCKS.register(bus);
        Cards.ITEMS.register(bus);
        Cards.BLOCK_ENTITIES.register(bus);
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Cards.GAME_TABLE_ITEM);
        }
    }
}
