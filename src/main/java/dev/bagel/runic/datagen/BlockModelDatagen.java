package dev.bagel.runic.datagen;

import dev.bagel.runic.Runic;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModelDatagen extends BlockModelProvider {
    public BlockModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Runic.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
