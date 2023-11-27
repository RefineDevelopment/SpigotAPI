package xyz.refinedev.api.spigot.knockback.impl.ispigot;

import org.bukkit.entity.Player;
import spg.lgdev.iSpigot;
import spg.lgdev.knockback.Knockback;
import spg.lgdev.knockback.KnockbackHandler;
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
        KnockbackHandler knockbackHandler = iSpigot.INSTANCE.getKnockbackHandler();
        Knockback<?, ?, ?, ?> knockbackImplement = knockbackHandler.getKnockbackProfile(knockback);
        player.setKnockback(knockbackImplement);
    }
}
