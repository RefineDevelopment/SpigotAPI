package xyz.refinedev.spigot.knockback.impl.carbon;

import org.bukkit.entity.Player;
import xyz.refinedev.spigot.knockback.IKnockbackType;
import xyz.refinedev.spigot.knockback.IKnockback;
import xyz.refinedev.spigot.knockback.KnockbackAPI;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

public class CarbonSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        IKnockback<?, ?, ?, ?> knockbackImplement = KnockbackAPI.getByName(knockback);
        KnockbackAPI.applyKnockback(knockbackImplement, player);
    }
}
