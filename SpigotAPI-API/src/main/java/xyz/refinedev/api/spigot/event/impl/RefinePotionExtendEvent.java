package xyz.refinedev.api.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * <p>
 * This Project is property of Refine Development.<br>
 * Copyright © 2023, All Rights Reserved.<br>
 * Redistribution of this Project is not allowed.<br>
 * </p>
 *
 * @author Drizzy
 * @since 4/30/2022
 * @version SpigotAPI
 */

public class RefinePotionExtendEvent extends RefinePotionAddEvent {

    private final PotionEffect oldEffect;

    public RefinePotionExtendEvent(LivingEntity what, PotionEffect effect, EffectAddReason reason, PotionEffect oldEffect) {
        super(what, effect, reason);
        this.oldEffect = oldEffect;
    }

    public PotionEffect getOldEffect() {
        return this.oldEffect;
    }
}
