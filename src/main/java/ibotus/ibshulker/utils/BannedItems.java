package ibotus.ibshulker.utils;

import org.bukkit.Material;

import java.util.ArrayList;

public class BannedItems {

    public static ArrayList<Material> bannedItems = new ArrayList<>();
    static {
        bannedItems.add(Material.DRAGON_EGG);
        bannedItems.add(Material.BUNDLE);
    }
}
