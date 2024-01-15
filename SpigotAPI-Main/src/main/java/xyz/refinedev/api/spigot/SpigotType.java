package xyz.refinedev.api.spigot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.refinedev.api.spigot.event.IListener;
import xyz.refinedev.api.spigot.knockback.IKnockbackType;
import xyz.refinedev.api.spigot.knockback.impl.atom.AtomSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.atom.AtomSpigotListener;
import xyz.refinedev.api.spigot.knockback.impl.carbon.CarbonSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.carbon.CarbonSpigotListener;
import xyz.refinedev.api.spigot.knockback.impl.fox.FoxSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.fox.FoxSpigotListener;
import xyz.refinedev.api.spigot.knockback.impl.ispigot.iSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.ispigot.iSpigotListener;
import xyz.refinedev.api.spigot.knockback.impl.paper.PaperKnockback;
import xyz.refinedev.api.spigot.knockback.impl.pspigot.pSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.pspigot.pSpigotListener;
import xyz.refinedev.api.spigot.knockback.impl.zortex.ZortexSpigotKnockback;
import xyz.refinedev.api.spigot.knockback.impl.zortex.ZortexSpigotListener;

import java.util.Arrays;

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

@Getter
@RequiredArgsConstructor
public enum SpigotType {

    CarbonSpigot("CarbonSpigot", "xyz.refinedev.spigot.api.knockback.KnockbackAPI", new CarbonSpigotKnockback(), new CarbonSpigotListener()),
    FoxSpigot("FoxSpigot", "pt.foxspigot.jar.knockback.KnockbackModule", new FoxSpigotKnockback(), new FoxSpigotListener()),
    AtomSpigot("AtomSpigot", "xyz.yooniks.atomspigot.AtomSpigot", new AtomSpigotKnockback(), new AtomSpigotListener()),
    iSpigot("ImanitySpigot", "org.imanity.imanityspigot.ImanitySpigot", new iSpigotKnockback(), new iSpigotListener()),
    ZortexSpigot("ZortexSpigot", "club.zortex.spigot.ZortexSpigot", new ZortexSpigotKnockback(), new ZortexSpigotListener()),
    pSpigot("pSpigot", "me.scalebound.pspigot.KnockbackProfile", new pSpigotKnockback(), new pSpigotListener()),
    Default("Paper", "", new PaperKnockback(), null);

    private final String name;
    private final String classToCheck;
    private final IKnockbackType knockbackType;
    private final IListener listener;

    /**
     * Detect which spigot is being used and initialize
     * {@link IKnockbackType} according to the spigot's type
     */
    public static SpigotType get() {
        return Arrays
                .stream(SpigotType.values())
                .filter(spigot -> !spigot.equals(SpigotType.Default) && check(spigot.getClassToCheck()))
                .findFirst()
                .orElse(SpigotType.Default);
    }

    /**
     * Checks if a class exists or not
     *
     * @param string The class's package path
     * @return {@link Boolean}
     */
    public static boolean check(String string) {
        if (string.isEmpty()) return false;

        try {
            Class.forName(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
