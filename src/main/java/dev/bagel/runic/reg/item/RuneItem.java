package dev.bagel.runic.reg.item;

import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.shadowsoffire.placebo.tabs.ITabFiller;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class RuneItem extends Item implements ITabFiller {
    public RuneItem() {
        super(new Properties());
    }

    @Override
    public void fillItemCategory(CreativeModeTab creativeModeTab, CreativeModeTab.Output output) {
        for (RuneType type : RuneType.values()) {
            output.accept(type.getAsItem());
        }
    }
}
