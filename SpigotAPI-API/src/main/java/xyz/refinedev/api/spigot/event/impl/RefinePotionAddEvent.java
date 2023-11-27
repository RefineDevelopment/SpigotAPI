package xyz.refinedev.api.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
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

@SuppressWarnings("unused")
public class RefinePotionAddEvent extends PotionEffectEvent implements Cancellable {

    private boolean cancelled;
    private final EffectAddReason reason;
    private static final HandlerList handlers = new HandlerList();

    public RefinePotionAddEvent(LivingEntity what, PotionEffect effect, EffectAddReason reason) {
        super(what, effect);
        this.reason = reason;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public EffectAddReason getReason() {
        return this.reason;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static enum EffectAddReason {
        COMMAND,
        GOLDEN_APPLE,
        BEACON,
        WITHER_SKULL,
        WITHER_SKELETON,
        VILLAGER_CURE,
        VILLAGER_LEVELUP,
        SPIDER_POWERUP,
        POTION_SPLASH,
        POTION_DRINK,
        CUSTOM,
        PLUGIN,
        UNKNOWN;
    }
}