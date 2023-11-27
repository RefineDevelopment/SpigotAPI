package xyz.refinedev.api.spigot.event;

import org.bukkit.plugin.java.JavaPlugin;

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

public interface IListener {

    void register(JavaPlugin plugin);

    boolean isApplicable();
}
