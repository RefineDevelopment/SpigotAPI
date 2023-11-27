package xyz.refinedev.api.spigot.knockback.impl.zortex;

import club.zortex.spigot.ZortexSpigot;
import club.zortex.spigot.knockback.zKnockbackProfile;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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

public class ZortexSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        zKnockbackProfile profile = ZortexSpigot.getKbProfileByName(knockback);
        if (profile == null) {
            profile = ZortexSpigot.globalKbProfile;
        }
        ((CraftPlayer)player).getHandle().setKbProfile(profile);
    }
}
