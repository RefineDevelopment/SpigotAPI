package xyz.refinedev.api.spigot.knockback.impl.paper;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.api.spigot.event.IListener;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

public class PaperListener implements IListener, Listener {

    @Override
    public void register(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean isApplicable() {
        return false;
    }
}
