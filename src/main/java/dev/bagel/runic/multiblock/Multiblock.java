package dev.bagel.runic.multiblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Multiblock implements CodecProvider<Multiblock> {

    public static final Codec<Multiblock> CODEC = RecordCodecBuilder.create(inst -> inst.group(Codec.STRING.listOf().fieldOf("list").forGetter(a -> a.list),
            Codec.unboundedMap(Codec.STRING.comapFlatMap(Multiblock::checkInvalid, String::valueOf), BuiltInRegistries.BLOCK.byNameCodec()).fieldOf("map").forGetter(a -> a.map))
        .apply(inst, Multiblock::new));

    public List<String> list;
    public Map<Character, Block> map;

    public Multiblock(List<String> list, Map<Character, Block> map) {
        this.list = list;
        this.map = map;
    }

    public static DataResult<Character> checkInvalid(final String s) {
        return s.length() != 1 ? DataResult.error(() -> "'" + s + "' is an invalid symbol (must be 1 character only).") : DataResult.success(s.charAt(0));
    }

    @Override
    public Codec<? extends Multiblock> getCodec() {
        return CODEC;
    }
}
