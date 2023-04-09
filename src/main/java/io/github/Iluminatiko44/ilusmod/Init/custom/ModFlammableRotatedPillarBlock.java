package io.github.Iluminatiko44.ilusmod.Init.custom;

import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import io.github.Iluminatiko44.ilusmod.base.ModTagsBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock implements ModTagsBase {

    @SafeVarargs
    public ModFlammableRotatedPillarBlock(Properties properties, TagKey<Block>... tags) {
        super(properties);
        this.setTagKeys(List.of(tags));
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {

            // Sets the log to it stripped form and keeps the axis
            if(state.is(BlockInit.HAPPY_LOG.get())) {
                return BlockInit.STRIPPED_HAPPY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(BlockInit.HAPPY_WOOD.get())) {
                return BlockInit.STRIPPED_HAPPY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
