package xyz.nierennakker.kaarten;

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
import xyz.nierennakker.kaarten.block.GameTableBlock;
import xyz.nierennakker.kaarten.entity.block.GameTableBlockEntity;

import java.util.function.Supplier;

@Mod(Kaarten.MOD_ID)
public class Kaarten {
    public static final String MOD_ID = "kaarten";

    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Kaarten.MOD_ID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Kaarten.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Kaarten.MOD_ID);

    public static final DeferredBlock<Block> GAME_TABLE = Kaarten.BLOCKS.register("game_table", GameTableBlock::new);
    public static final DeferredItem<BlockItem> GAME_TABLE_ITEM = Kaarten.ITEMS.registerSimpleBlockItem(Kaarten.GAME_TABLE);
    public static final Supplier<BlockEntityType<GameTableBlockEntity>> GAME_TABLE_ENTITY = Kaarten.BLOCK_ENTITIES.register("game_table", () -> BlockEntityType.Builder.of(GameTableBlockEntity::new, Kaarten.GAME_TABLE.get()).build(null));

    public Kaarten(IEventBus bus) {
        bus.register(this);

        Kaarten.BLOCKS.register(bus);
        Kaarten.ITEMS.register(bus);
        Kaarten.BLOCK_ENTITIES.register(bus);
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Kaarten.GAME_TABLE_ITEM);
        }
    }
}
