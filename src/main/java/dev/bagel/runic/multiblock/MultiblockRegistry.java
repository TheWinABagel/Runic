package dev.bagel.runic.multiblock;

import dev.bagel.runic.Runic;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;
import org.apache.logging.log4j.Logger;

public class MultiblockRegistry extends DynamicRegistry<Multiblock> {
    public static final MultiblockRegistry INSTANCE = new MultiblockRegistry();
    private MultiblockRegistry() {
        super(Runic.LOGGER, "multiblocks", false, false);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(Runic.loc("multiblocks"), Multiblock.CODEC);
    }
}
