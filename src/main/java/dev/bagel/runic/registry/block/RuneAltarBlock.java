package dev.bagel.runic.registry.block;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.block.entity.RuneAltarBlockEntity;
import dev.bagel.runic.registry.rune_registry.RuneType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class RuneAltarBlock extends Block implements EntityBlock {
    protected BlockPattern pattern;
    public RuneAltarBlock() {
        super(Properties.of().strength(10f));
        this.pattern = pattern;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RuneAltarBlockEntity(pPos, pState, pattern);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> type) {
        return type == RunicRegistry.BlockEntities.RUNE_ALTAR_BE.get() ? RuneAltarBlockEntity::tick : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof RuneAltarBlockEntity runeAltar) {

        }
        return super.use(state, level, pos, player, hand, hit);
    }
}
