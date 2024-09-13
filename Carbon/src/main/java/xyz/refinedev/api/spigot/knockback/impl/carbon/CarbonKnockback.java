package xyz.refinedev.api.spigot.knockback.impl.carbon;

import org.bukkit.entity.Player;

import xyz.refinedev.spigot.api.combat.ICombatProfile;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.refinedev.spigot.features.combat.CombatAPI;

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

public class CarbonKnockback implements IKnockbackType {

    @Override
    public void setKnockback(Player player, String knockback) {
        CombatAPI api = CombatAPI.getInstance();
        ICombatProfile<?, ?, ?> profile = api.getProfile(knockback);
        if (profile == null) {
            profile = api.getDefaultProfile(player.getWorld());
        }
        api.setPlayerProfile(player, profile);
    }
}
