package xyz.refinedev.spigot.knockback.impl.fox;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionEffectAddEvent;
import org.bukkit.event.entity.PotionEffectExpireEvent;
import org.bukkit.event.entity.PotionEffectExtendEvent;
import org.bukkit.event.entity.PotionEffectRemoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.spigot.event.IListener;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: KnockbackAPI
 */

public class FoxSpigotListener implements IListener, Listener {

    @EventHandler
    public void onExpire(PotionEffectExpireEvent event) {
        xyz.refinedev.spigot.event.impl.PotionEffectExpireEvent custom = new xyz.refinedev.spigot.event.impl.PotionEffectExpireEvent(event.getEntity(), event.getEffect());
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onAdd(PotionEffectAddEvent event) {
        xyz.refinedev.spigot.event.impl.PotionEffectAddEvent.EffectAddReason reason = xyz.refinedev.spigot.event.impl.PotionEffectAddEvent.EffectAddReason.UNKNOWN;
        xyz.refinedev.spigot.event.impl.PotionEffectAddEvent custom = new xyz.refinedev.spigot.event.impl.PotionEffectAddEvent(event.getEntity(), event.getEffect(), reason);
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onExtend(PotionEffectExtendEvent event) {
        xyz.refinedev.spigot.event.impl.PotionEffectAddEvent.EffectAddReason reason = xyz.refinedev.spigot.event.impl.PotionEffectAddEvent.EffectAddReason.UNKNOWN;
        xyz.refinedev.spigot.event.impl.PotionEffectExtendEvent custom = new xyz.refinedev.spigot.event.impl.PotionEffectExtendEvent(event.getEntity(), event.getEffect(), reason, event.getOldEffect());
        Bukkit.getPluginManager().callEvent(custom);
    }

    @EventHandler
    public void onRemove(PotionEffectRemoveEvent event) {
        xyz.refinedev.spigot.event.impl.PotionEffectRemoveEvent custom = new xyz.refinedev.spigot.event.impl.PotionEffectRemoveEvent(event.getEntity(), event.getEffect());
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
