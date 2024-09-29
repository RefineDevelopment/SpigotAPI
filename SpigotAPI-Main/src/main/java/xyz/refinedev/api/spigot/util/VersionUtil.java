package xyz.refinedev.api.spigot.util;

import org.bukkit.Bukkit;

public class VersionUtil {

    public static String VERSION;
    public static int MINOR_VERSION;

    public static boolean canHex() {
        return MINOR_VERSION >= 16;
    }

    static {
        try {
            String versionName = Bukkit.getServer().getClass().getPackage().getName();
            VERSION = versionName.length() < 4 ? versionName.split("\\.")[2] : versionName.split("\\.")[3];
            MINOR_VERSION = Integer.parseInt(VERSION.split("_")[1]);
        } catch (Exception e) {
            VERSION = "v" + Bukkit.getServer().getBukkitVersion().replace("-SNAPSHOT", "").replace("-R0.", "_R").replace(".", "_");
            MINOR_VERSION = Integer.parseInt(VERSION.split("_")[1]);
        }
    }
}
