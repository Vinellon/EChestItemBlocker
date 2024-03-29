package ibotus.ibshulker.events;

import ibotus.ibshulker.utils.BannedItems;
import ibotus.ibshulker.utils.ChatUtils;
import ibotus.ibshulker.configurations.Config;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickEventHandler implements Listener {

    @EventHandler
    public void onInventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission("ibshulker.bypass")) {
            if (event.getInventory().getType().equals(InventoryType.ENDER_CHEST) || event.getInventory().getType().equals(InventoryType.SHULKER_BOX)) {
                if (event.getCurrentItem() != null && BannedItems.bannedItems.contains(event.getCurrentItem().getType())) {
                    if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                        event.setCancelled(true);
                        String soundName = Config.getConfig().getString("sound");
                        Sound sound = Sound.valueOf(soundName);
                        player.playSound(player.getLocation(), sound, 0.5f, 1.0f);

                        String message = Config.getConfig().getString("messages");
                        String coloredMessage = ChatUtils.color(message);
                        player.sendMessage(coloredMessage);
                    }
                }
            }
        }
    }
}
