package dev.bagel.runic.multiblock;

import dev.bagel.runic.registry.RunicRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;

public class MultiblockTypes {

    public static BlockPattern FIRE_RUNE_ALTAR = BlockPatternBuilder.start()
            .aisle("#F F#", "# $ #", "#F F#")
            .aisle("#####", "#####", "#####")
            .where('#', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.STONE)))
            .where('$', BlockInWorld.hasState(BlockPredicate.forBlock(RunicRegistry.Blocks.RUNE_ALTAR.get())))
            .where('F', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.FIRE)))
            .build();

}
