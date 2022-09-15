package xyz.refinedev.api.spigot.event.impl;

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

public class PotionEffectExpireEvent extends PotionEffectRemoveEvent {
    private int duration = 0;

    public PotionEffectExpireEvent(LivingEntity entity, PotionEffect effect) {
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