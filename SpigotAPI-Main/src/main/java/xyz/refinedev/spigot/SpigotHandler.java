package xyz.refinedev.spigot;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.spigot.event.IListener;
import xyz.refinedev.spigot.event.impl.equipment.EquipmentListener;
import xyz.refinedev.spigot.hider.EntityHider;
import xyz.refinedev.spigot.knockback.IKnockbackType;

import java.lang.reflect.Field;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

@Getter
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
     * gives it's API instance
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
     * @param hcfMode {@link Boolean} should we register a listener for custom events
     */
    public void init(boolean hcfMode) {
        this.type = SpigotType.get(); // We call the static method that checks for the supported spigot and returns the correct type

        if (type == SpigotType.Default) return;

        this.knockback = type.getKnockbackType();
        this.listener = type.getListener();
        this.listener.register(plugin);

        if (!hcfMode) return;

        // Register our custom event listener for equipment set event
        EquipmentListener equipmentListener = new EquipmentListener();
        plugin.getServer().getPluginManager().registerEvents(equipmentListener, plugin);
    }

    /**
     * Start our custom entity hider which patches visibility bugs
     * in default paper, bukkit and spigot software
     */
    public void initiateEntityHider() {
        boolean protocolLib = plugin.getServer().getPluginManager().isPluginEnabled("ProtocolLib");
        Preconditions.checkArgument(protocolLib, "ProtocolLib is required for the EntityHider!");
        if (!this.isEntityHiderRequired()) {
            plugin.getLogger().warning("[EntityHider] The spigot you are using, already has a built-in entity hider!");
            plugin.getLogger().warning("[EntityHider] Please refrain from using the API's Custom Entity Hider.");
        }

        EntityHider entityHider = new EntityHider(plugin, EntityHider.Policy.BLACKLIST);
        entityHider.startListening();

        plugin.getLogger().info("[EntityHider] Successfully enabled the custom Entity Hider, intercepting packets...");
    }


    /**
     * Just a random check I made, which should work
     * for most spigots using same structure as SpigotX
     *
     * @return {@link Boolean}
     */
    public boolean isEntityHiderRequired() {
        try {
            Class<?> classInstance = Class.forName("net.minecraft.server.v1_8_R3.EntityItem");
            classInstance.getField("owner"); // Most, if not all spigot entity hiders have this to track if owner is visible to viewer
            return true;
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }
}
