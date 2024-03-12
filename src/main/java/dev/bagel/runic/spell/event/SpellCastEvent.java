package dev.bagel.runic.spell.event;

import dev.bagel.runic.spell.Spell;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class SpellCastEvent extends PlayerEvent {
    protected final Player player;
    protected Spell spell;
    public SpellCastEvent(Player player, Spell spell) {
        super(player);
        this.player = player;
        this.spell = spell;
    }

    public Player getPlayer() {
        return player;
    }

    public Spell getSpell() {
        return spell;
    }
}
