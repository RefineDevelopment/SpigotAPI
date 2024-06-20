package xyz.refinedev.api.spigot.knockback.impl.pspigot;

import org.bukkit.entity.Player;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;

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

public class pSpigotKnockback implements IKnockbackType {

    private pSpigotKnockbackFix fix;

    @Override
    public void setKnockback(Player player, String knockback) {
        if (fix == null) {
            fix = new pSpigotKnockbackFix();
        }
        fix.setKnockback(player, knockback);
    }
}
