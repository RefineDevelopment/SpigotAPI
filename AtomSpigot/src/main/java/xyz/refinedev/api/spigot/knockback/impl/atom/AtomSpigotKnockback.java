package xyz.refinedev.api.spigot.knockback.impl.atom;

import org.bukkit.entity.Player;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.yooniks.atomspigot.knockback.Knockback;

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

public class AtomSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        Knockback knockbackImplement = Knockback.getKnockback(knockback);
        player.setKnockback(knockbackImplement);
    }
}
