package xyz.refinedev.api.spigot.knockback;

import org.bukkit.entity.Player;

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

public interface IKnockbackType {

    void setKnockback(Player player, String knockback);
}
