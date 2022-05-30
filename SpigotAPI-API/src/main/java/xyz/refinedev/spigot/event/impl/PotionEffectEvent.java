package xyz.refinedev.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffect;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
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
