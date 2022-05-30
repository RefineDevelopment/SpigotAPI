package xyz.refinedev.spigot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.refinedev.spigot.event.IListener;
import xyz.refinedev.spigot.knockback.IKnockbackType;
import xyz.refinedev.spigot.knockback.impl.atom.AtomSpigotKnockback;
import xyz.refinedev.spigot.knockback.impl.atom.AtomSpigotListener;
import xyz.refinedev.spigot.knockback.impl.carbon.CarbonSpigotKnockback;
import xyz.refinedev.spigot.knockback.impl.carbon.CarbonSpigotListener;
import xyz.refinedev.spigot.knockback.impl.fox.FoxSpigotKnockback;
import xyz.refinedev.spigot.knockback.impl.fox.FoxSpigotListener;
import xyz.refinedev.spigot.knockback.impl.ispigot.iSpigotKnockback;
import xyz.refinedev.spigot.knockback.impl.ispigot.iSpigotListener;

import java.util.Arrays;

/**
 * This Project is property of Refine Development © 2021 - 2022
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 4/30/2022
 * Project: SpigotAPI
 */

@Getter
@RequiredArgsConstructor
public enum SpigotType {

    CarbonSpigot("CarbonSpigot", "xyz.refinedev.spigot.CarbonSpigot", new CarbonSpigotKnockback(), new CarbonSpigotListener()),
    FoxSpigot("FoxSpigot", "pt.foxspigot.jar.knockback.KnockbackModule", new FoxSpigotKnockback(), new FoxSpigotListener()),
    AtomSpigot("AtomSpigot", "xyz.yooniks.atomspigot.AtomSpigot", new AtomSpigotKnockback(), new AtomSpigotListener()),
    iSpigot("iSpigot", "spg.lgdev.iSpigot", new iSpigotKnockback(), new iSpigotListener()),
    Default("None", "none", null, null);

    private final String name;
    private final String packageName;
    private final IKnockbackType knockbackType;
    private final IListener listener;

    /**
     * Detect which spigot is being used and initialize
     * {@link IKnockbackType} according to the spigot's type
     */
    public static SpigotType get() {
        return Arrays
                .stream(SpigotType.values())
                .filter(kSpigotType -> !kSpigotType.equals(SpigotType.Default) && check(kSpigotType.getPackageName()))
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
        try {
            Class.forName(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
