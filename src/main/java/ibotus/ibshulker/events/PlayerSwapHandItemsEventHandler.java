package ibotus.ibshulker.events;

import ibotus.ibshulker.configurations.Config;
import ibotus.ibshulker.utils.BannedItems;
import ibotus.ibshulker.utils.ChatUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerSwapHandItemsEventHandler implements Listener {

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("ibshulker.bypass")) {
            ItemStack mainHandItem = event.getMainHandItem();
            ItemStack offHandItem = event.getOffHandItem();

            assert mainHandItem != null;
            if (BannedItems.bannedItems.contains(mainHandItem.getType()) || BannedItems.bannedItems.contains(Objects.requireNonNull(offHandItem).getType())) {
                event.setCancelled(true);
                String soundName = Config.getConfig().getString("sound");
                Sound sound = Sound.valueOf(soundName);
                event.getPlayer().playSound(event.getPlayer().getLocation(), sound, 0.5f, 1.0f);

                String message = Config.getConfig().getString("messages");
                String coloredMessage = ChatUtils.color(message);
                player.sendMessage(coloredMessage);
            }
        }
    }
}
