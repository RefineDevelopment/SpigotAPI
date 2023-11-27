package xyz.refinedev.api.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityEvent;
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

public abstract class PotionEffectEvent extends EntityEvent {

    private final PotionEffect effect;

    public PotionEffectEvent(LivingEntity entity, PotionEffect effect) {
        super(entity);
        this.effect = effect;
    }

    public LivingEntity getEntity() {
        return (LivingEntity)super.getEntity();
    }

    public PotionEffect getEffect() {
        return this.effect;
    }
}
