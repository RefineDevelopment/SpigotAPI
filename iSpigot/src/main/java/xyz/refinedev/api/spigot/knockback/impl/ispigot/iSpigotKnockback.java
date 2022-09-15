package xyz.refinedev.api.spigot.knockback.impl.ispigot;

import org.bukkit.entity.Player;
import spg.lgdev.iSpigot;
import spg.lgdev.knockback.Knockback;
import spg.lgdev.knockback.KnockbackHandler;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: KnockbackAPI
 */

public class iSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackHandler knockbackHandler = iSpigot.INSTANCE.getKnockbackHandler();
        Knockback<?, ?, ?, ?> knockbackImplement = knockbackHandler.getKnockbackProfile(knockback);
        player.setKnockback(knockbackImplement);
    }
}
