package xyz.refinedev.api.spigot.util;

import lombok.experimental.UtilityClass;

/**
 * This Project is property of Refine Development © 2021 - 2023
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * @since 9/12/2023
 * @version SpigotAPI
 */

@UtilityClass
public class MathUtil {

    public int floor(double var0) {
        int var2 = (int)var0;
        return var0 < (double)var2 ? var2 - 1 : var2;
    }

}
