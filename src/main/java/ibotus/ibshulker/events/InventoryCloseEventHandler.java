package ibotus.ibshulker.events;

import ibotus.ibshulker.configurations.Config;
import ibotus.ibshulker.utils.BannedItems;
import ibotus.ibshulker.utils.ChatUtils;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryCloseEventHandler implements Listener {

    @EventHandler
    public void onInventoryCloseEvent(org.bukkit.event.inventory.InventoryCloseEvent event) {
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            List<HumanEntity> viewers = event.getInventory().getViewers();
            if (!viewers.isEmpty()) {
                Player player = (Player) viewers.get(0);
                if (!player.hasPermission("ibshulker.bypass")) {
                    ItemStack[] enderChestItems = event.getInventory().getContents();
                    ArrayList<ItemStack> enderChestArray = new ArrayList<>();

                    ArrayList<ItemStack> toGive = new ArrayList<>();

                    for (ItemStack item : enderChestItems) {
                        if (item != null) {
                            if (BannedItems.bannedItems.contains(item.getType())) {
                                toGive.add(item);

                                String pName = event.getInventory().getViewers().get(0).getName();
                                System.out.print("Illegal ShulkerBox moved from " + pName + "'s EnderChest to their inventory!");
                            } else {
                                enderChestArray.add(item);
                            }
                        }
                    }

                    ItemStack[] enderChestItemsNew = enderChestArray.toArray(new ItemStack[0]);
                    event.getInventory().setContents(enderChestItemsNew);

                    for (ItemStack item : toGive) {
                        HashMap<Integer, ItemStack> notAdded = player.getInventory().addItem(item);
                        if (!notAdded.isEmpty()) {
                            player.getWorld().dropItem(player.getLocation(), notAdded.get(0));
                        }
                    }

                    if (!toGive.isEmpty()) {
                        player.closeInventory();
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
