package xyz.refinedev.api.spigot.knockback.impl.fox;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pt.foxspigot.jar.knockback.KnockbackModule;
import pt.foxspigot.jar.knockback.KnockbackProfile;
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
 * @version KnockbackAPI
 */

public class FoxSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackProfile profile = KnockbackModule.getByName(knockback);
        ((CraftPlayer)player).getHandle().setKnockback(profile);
    }
}
