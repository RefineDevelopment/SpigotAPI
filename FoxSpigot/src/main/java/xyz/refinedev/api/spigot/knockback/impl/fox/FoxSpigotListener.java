package xyz.refinedev.api.spigot.knockback.impl.fox;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionEffectAddEvent;
import org.bukkit.event.entity.PotionEffectExpireEvent;
import org.bukkit.event.entity.PotionEffectExtendEvent;
import org.bukkit.event.entity.PotionEffectRemoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.api.spigot.event.IListener;
import xyz.refinedev.api.spigot.event.impl.RefinePotionAddEvent;
import xyz.refinedev.api.spigot.event.impl.RefinePotionExpireEvent;
import xyz.refinedev.api.spigot.event.impl.RefinePotionExtendEvent;
import xyz.refinedev.api.spigot.event.impl.RefinePotionRemoveEvent;

/**
 * <p>
 * This Project is property of Refine Development.<br>
 * Copyright © 2023, All Rights Reserved.<br>
 * Redistribution of this Project is not allowed.<br>
 * </p>
 *
 * @author Drizzy
 * @since 4/30/2022
 * @version KnockbackAPI
 */

public class FoxSpigotListener implements IListener, Listener {

    @EventHandler
    public void onExpire(PotionEffectExpireEvent event) {
        RefinePotionExpireEvent custom = new RefinePotionExpireEvent(event.getEntity(), event.getEffect());
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onAdd(PotionEffectAddEvent event) {
        RefinePotionAddEvent.EffectAddReason reason = RefinePotionAddEvent.EffectAddReason.UNKNOWN;
        RefinePotionAddEvent custom = new RefinePotionAddEvent(event.getEntity(), event.getEffect(), reason);
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onExtend(PotionEffectExtendEvent event) {
        RefinePotionAddEvent.EffectAddReason reason = RefinePotionAddEvent.EffectAddReason.UNKNOWN;
        RefinePotionExtendEvent custom = new RefinePotionExtendEvent(event.getEntity(), event.getEffect(), reason, event.getOldEffect());
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onRemove(PotionEffectRemoveEvent event) {
        RefinePotionRemoveEvent custom = new RefinePotionRemoveEvent(event.getEntity(), event.getEffect());
        Bukkit.getPluginManager().callEvent(custom);
    }

    @Override
    public void register(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }
}
