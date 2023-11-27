package xyz.refinedev.api.spigot.knockback.impl.carbon;

import org.bukkit.entity.Player;
import xyz.refinedev.spigot.api.knockback.KnockbackAPI;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.refinedev.spigot.knockback.KnockbackProfile;

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

public class CarbonSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackAPI api = KnockbackAPI.getInstance();
        KnockbackProfile profile = api.getProfile(knockback);
        if (profile == null) {
            profile = api.getDefaultProfile();
        }
        api.setPlayerProfile(player, profile);
    }
}
