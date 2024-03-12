package dev.bagel.runic.datagen;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelDatagen extends ItemModelProvider {

    public ItemModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Runic.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RunicRegistry.Items.FIRE_STAFF.asItem());
        basicItem(RunicRegistry.Items.AIR_RUNE.asItem());
        basicItem(RunicRegistry.Items.BLANK_RUNE.asItem());
        basicItem(RunicRegistry.Items.EARTH_RUNE.asItem());
        basicItem(RunicRegistry.Items.FIRE_RUNE.asItem());
        basicItem(RunicRegistry.Items.LAW_RUNE.asItem());
        basicItem(RunicRegistry.Items.WATER_RUNE.asItem());
        basicItem(RunicRegistry.Items.TIER_1_POUCH.asItem());
    }
}
