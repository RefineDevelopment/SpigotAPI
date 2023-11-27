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

public class RefineRefinePotionExpireEvent extends RefinePotionRemoveEvent {
    private int duration = 0;

    public RefineRefinePotionExpireEvent(LivingEntity entity, PotionEffect effect) {
        super(entity, effect);
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = Math.max(0, duration);
    }

    public boolean isCancelled() {
        return this.duration > 0;
    }

    public void setCancelled(boolean cancel) {
        this.duration = cancel ? 2147483647 : 0;
    }
}