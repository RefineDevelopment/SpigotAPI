package xyz.refinedev.api.spigot.event.impl;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

@SuppressWarnings("unused")
public class PotionEffectAddEvent extends PotionEffectEvent implements Cancellable {

    private boolean cancelled;
    private final EffectAddReason reason;
    private static final HandlerList handlers = new HandlerList();

    public PotionEffectAddEvent(LivingEntity what, PotionEffect effect, EffectAddReason reason) {
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
        UNKNOWN;
    }
}