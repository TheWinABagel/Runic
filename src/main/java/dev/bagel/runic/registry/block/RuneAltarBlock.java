package dev.bagel.runic.registry.block;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.block.entity.RuneAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RuneAltarBlock extends Block implements EntityBlock {

    public RuneAltarBlock() {
        super(Properties.of().strength(10f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RuneAltarBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> type) {
        return type == RunicRegistry.BlockEntities.RUNE_ALTAR_BE.get() ? RuneAltarBlockEntity::tick : null;
    }
}
