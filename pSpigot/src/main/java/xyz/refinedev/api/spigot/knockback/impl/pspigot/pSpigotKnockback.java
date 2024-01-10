package xyz.refinedev.api.spigot.knockback.impl.pspigot;

import me.scalebound.pspigot.KnockbackProfile;
import me.scalebound.pspigot.knockback.CraftKnockbackProfile;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.SpigotConfig;
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

public class pSpigotKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        KnockbackProfile craftKnockbackProfile = SpigotConfig.getKbProfileByName(knockback);
        if (craftKnockbackProfile == null) {
            craftKnockbackProfile = SpigotConfig.globalKbProfile;
        }

        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityLiving living = craftPlayer.getHandle();
        if (player.isOnline()) {
            living.setKbProfile(craftKnockbackProfile);
        }
    }
}
