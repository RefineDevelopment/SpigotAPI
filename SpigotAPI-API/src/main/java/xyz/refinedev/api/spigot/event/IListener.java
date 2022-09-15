package xyz.refinedev.api.spigot.event;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

public interface IListener {

    void register(JavaPlugin plugin);

    boolean isApplicable();
}
