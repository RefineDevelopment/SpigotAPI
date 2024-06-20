package xyz.refinedev.api.spigot;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.refinedev.api.spigot.event.IListener;
import xyz.refinedev.api.spigot.equipment.EquipmentListener;
import xyz.refinedev.api.spigot.hider.EntityHider;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.refinedev.api.spigot.util.VersionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

@Getter @Log4j2
public class SpigotHandler {

    private final JavaPlugin plugin;
    /**
     * The knockbackAPI instance through which
     * plugins can use knockback methods
     */
    private IKnockbackType knockback;
    /**
     * The listener instance if its in HCF Mode
     * I don't see a need for this in plugins but its
     * there in case someone wants it lel
     */
    private IListener listener;
    /**
     * An enum that detects the correct spigot and
     * gives its API instance
     */
    protected SpigotType type;

    public SpigotHandler(JavaPlugin plugin) {
        Preconditions.checkNotNull(plugin, "Plugin instance can not be null!");
        this.plugin = plugin;
    }

    /**
     * Initiate in non-listener mode, meaning
     * this won't, register or check for, the HCF events listener
     */
    public void init() {
        this.init(false);
    }

    /**
     * Main initiate method that will hook into the correct spigot
     *
     * @param teamFight {@link Boolean} should we register a listener for custom events
     */
    public void init(boolean teamFight) {
        if (VersionUtil.MINOR_VERSION > 8) {
            this.type = SpigotType.Default;
        } else {
            this.type = SpigotType.get(); // We call the static method that checks for the supported spigot and returns the correct type
        }

        this.knockback = type.getKnockbackType();

        if (!teamFight || this.type == SpigotType.Default) return; // Don't do HCF events if not required

        this.listener = type.getListener();
        this.listener.register(plugin);

        // Register our custom event listener for equipment set event
        EquipmentListener equipmentListener = new EquipmentListener();
        plugin.getServer().getPluginManager().registerEvents(equipmentListener, plugin);
    }

    /**
     * Start our custom entity hider which patches visibility bugs
     * in default paper, bukkit and spigot software
     *
     * @deprecated This is no longer used for Refine as we use Itzel NMS API.
     */
    @Deprecated
    public void initiateEntityHider() {
        boolean protocolLib = plugin.getServer().getPluginManager().isPluginEnabled("ProtocolLib");
        Preconditions.checkArgument(protocolLib, "ProtocolLib is required for the EntityHider!");
        if (!this.isEntityHiderRequired()) {
            log.info("[EntityHider] Detected a decent spigot, using spigot-sided entity hider!");
            return;
        }

        EntityHider entityHider = new EntityHider(plugin, EntityHider.Policy.BLACKLIST);
        entityHider.startListening();

        log.info("[EntityHider] Successfully enabled the custom Entity Hider, intercepting packets...");
    }


    /**
     * Just a random check I made, which should work
     * for most spigots using same structure as SpigotX
     *
     * @return {@link Boolean}
     */
    public boolean isEntityHiderRequired() {
        boolean required = true;

        // If this fails for a spigot, it's a shit spigot.
        try {
            Class<?> classInstance = Class.forName("net.minecraft.server.v1_8_R3.EntityItem");
            Field field = classInstance.getDeclaredField("owner"); // Most, if not all spigot entity hiders have this to track if owner is visible to viewer
            required = false;
        } catch (ClassNotFoundException | NoSuchFieldException ignored) {}

        try {
            Class<?> classInstance = Class.forName("net.minecraft.server.v1_8_R3.PlayerList");
            Method method = classInstance.getDeclaredMethod("sendPacketNearbyIncludingSelf"); // Most, if not all spigot entity hiders have this to track if owner is visible to viewer
            required = false;
        } catch (ClassNotFoundException | NoSuchMethodException ignored) {}

        try {
            Class<?> classInstance = Class.forName("org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer");
            Method method = classInstance.getDeclaredMethod("canSeeEntity"); // Most, if not all spigot entity hiders have this to track if owner is visible to viewer
            required = false;
        } catch (ClassNotFoundException | NoSuchMethodException ignored) {}

        return required;
    }
}
