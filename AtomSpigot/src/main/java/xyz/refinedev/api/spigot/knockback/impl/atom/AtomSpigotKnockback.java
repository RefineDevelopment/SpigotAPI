package xyz.refinedev.api.spigot.knockback.impl.atom;

import org.bukkit.entity.Player;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.yooniks.atomspigot.knockback.Knockback;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: KnockbackAPI
 */

public class AtomSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        Knockback knockbackImplement = Knockback.getKnockback(knockback);
        player.setKnockback(knockbackImplement);
    }
}
