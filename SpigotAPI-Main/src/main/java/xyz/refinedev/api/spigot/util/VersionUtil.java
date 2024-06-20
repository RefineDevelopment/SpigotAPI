package xyz.refinedev.api.spigot.util;

import org.bukkit.Bukkit;

public class VersionUtil {
    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    public static final int MINOR_VERSION;

    public VersionUtil() {
    }

    public static boolean canHex() {
        return MINOR_VERSION >= 16;
    }

    static {
        MINOR_VERSION = Integer.parseInt(VERSION.split("_")[1]);
    }
}
