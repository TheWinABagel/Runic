package dev.bagel.runic.mixin;

import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Mixin(BlockPatternBuilder.class)
public interface BlockPatternBuilderAccessor {
    @Accessor
    List<String[]> getPattern();

    @Accessor
    Map<Character, Predicate<BlockInWorld>> getLookup();

    @Accessor
    int getHeight();

    @Accessor
    int getWidth();

    @Invoker
    Predicate<BlockInWorld>[][][] callCreatePattern();
}
