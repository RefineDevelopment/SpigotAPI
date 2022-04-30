package xyz.refinedev.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

public class PotionEffectExtendEvent extends PotionEffectAddEvent {

    private final PotionEffect oldEffect;

    public PotionEffectExtendEvent(LivingEntity what, PotionEffect effect, PotionEffectAddEvent.EffectAddReason reason, PotionEffect oldEffect) {
        super(what, effect, reason);
        this.oldEffect = oldEffect;
    }

    public PotionEffect getOldEffect() {
        return this.oldEffect;
    }
}
