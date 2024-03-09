package dev.bagel.runic.registry.item;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.shadowsoffire.placebo.tabs.ITabFiller;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RuneItem extends Item implements ITabFiller {
    public RuneType type;
    public RuneItem(RuneType type) {
        super(new Properties());
    }

    @Override
    public void fillItemCategory(CreativeModeTab creativeModeTab, CreativeModeTab.Output output) {
        for (RuneType type : RuneType.values()) {
            output.accept(new ItemStack(new RuneItem(type)));
        }
    }
}
