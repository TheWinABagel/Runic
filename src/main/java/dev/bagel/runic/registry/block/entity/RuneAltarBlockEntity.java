package dev.bagel.runic.registry.block.entity;

import dev.bagel.runic.registry.RunicRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;

public class RuneAltarBlockEntity extends BlockEntity {
    protected BlockPattern pattern;
    public RuneAltarBlockEntity(BlockPos pPos, BlockState pBlockState, BlockPattern pattern) {
        this(pPos, pBlockState);
        this.pattern = pattern;
    }
    public RuneAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(RunicRegistry.BlockEntities.RUNE_ALTAR_BE.get(), pPos, pBlockState);
    }

    public void test() {

    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
        RuneAltarBlockEntity be = (RuneAltarBlockEntity) blockEntity;

    }


}
