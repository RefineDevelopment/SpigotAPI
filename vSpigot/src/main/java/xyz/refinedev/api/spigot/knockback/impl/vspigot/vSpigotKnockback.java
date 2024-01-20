package xyz.refinedev.api.spigot.knockback.impl.vspigot;

import org.bukkit.entity.Player;
import ru.vspigot.api.KnockbackAPI;
import ru.vspigot.knockback.KnockbackProfile;
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

public class vSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackProfile knockbackProfile = KnockbackAPI.getByName(knockback);
        if (knockbackProfile == null) {
            knockbackProfile = KnockbackAPI.getDefault();
        }
        KnockbackAPI.applyKnockback(knockbackProfile, player);
    }
}
