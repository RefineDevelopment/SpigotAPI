package xyz.refinedev.api.spigot.knockback.impl.fox;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pt.foxspigot.jar.knockback.KnockbackModule;
import pt.foxspigot.jar.knockback.KnockbackProfile;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: KnockbackAPI
 */

public class FoxSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackProfile profile = KnockbackModule.getByName(knockback);
        ((CraftPlayer)player).getHandle().setKnockback(profile);
    }
}
