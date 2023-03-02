package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import io.github.Iluminatiko44.ilusmod.Init.ItemInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Drops itself
        dropSelf(BlockInit.HAPPY_BLOCK.get());
        // Only drops itself when silk touched
        add(BlockInit.PSEUDO_ICE.get(), (block) -> createSilkTouchOnlyTable(BlockInit.PSEUDO_ICE.get()));
        // Drops happy ball
        add(BlockInit.HAPPY_ORE.get(), (block) -> createMultipleOreDrops(BlockInit.HAPPY_ORE.get(), ItemInit.HAPPY_BALL.get(), 3.0f, 4.0f));
        add(BlockInit.DEEPSLATE_HAPPY_ORE.get(), (block) -> createMultipleOreDrops(BlockInit.DEEPSLATE_HAPPY_ORE.get(), ItemInit.HAPPY_BALL.get(), 3.0f, 4.0f));
        add(BlockInit.ENDSTONE_HAPPY_ORE.get(), (block) -> createMultipleOreDrops(BlockInit.ENDSTONE_HAPPY_ORE.get(), ItemInit.HAPPY_BALL.get(), 3.0f, 4.0f));
        add(BlockInit.NETHERRACK_HAPPY_ORE.get(), (block) -> createMultipleOreDrops(BlockInit.NETHERRACK_HAPPY_ORE.get(), ItemInit.HAPPY_BALL.get(), 3.0f, 4.0f));
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    // This has to be here, because there is no standard method for creating ore drops with multiple items
    @SuppressWarnings("SameParameterValue")
    protected LootTable.Builder createMultipleOreDrops(Block p_251906_, Item drop, float min, float max) {
        return createSilkTouchDispatchTable(p_251906_, this.applyExplosionDecay(p_251906_, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
