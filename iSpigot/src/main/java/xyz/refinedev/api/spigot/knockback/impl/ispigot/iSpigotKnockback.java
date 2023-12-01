package xyz.refinedev.api.spigot.knockback.impl.ispigot;

import dev.imanity.knockback.api.Knockback;
import dev.imanity.knockback.api.KnockbackService;

import org.bukkit.Bukkit;
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
 * @version KnockbackAPI
 */
public class iSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackService knockbackHandler = Bukkit.getServer().imanity().getKnockbackService();
        Knockback knockbackProfile = knockbackHandler.getKnockbackByName(knockback);
        knockbackHandler.setKnockback(player, knockbackProfile);
    }
}
