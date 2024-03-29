package ibotus.ibshulker.events;

import ibotus.ibshulker.utils.BannedItems;
import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import org.bukkit.event.inventory.InventoryPickupItemEvent;

import java.util.ArrayList;

public class PlayerDeathEventHandler implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Inventory openInventory = player.getOpenInventory().getTopInventory();

        if (openInventory.getType().equals(InventoryType.ENDER_CHEST) || openInventory.getType().equals(InventoryType.SHULKER_BOX)) {
            ArrayList<ItemStack> toDrop = new ArrayList<>();

            for (ItemStack item : openInventory.getContents()) {
                if (item != null && BannedItems.bannedItems.contains(item.getType())) {
                    toDrop.add(item);
                }
            }
            for (ItemStack item : toDrop) {
                openInventory.remove(item);
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onInventoryPickupItem (InventoryPickupItemEvent event)
    {
        InventoryHolder holder = event.getInventory().getHolder();
        if(holder instanceof HopperMinecart || holder instanceof Hopper)
        {
            ItemStack item = event.getItem().getItemStack();
            Material itemType = item.getType();
            if(itemType == Material.DRAGON_EGG)
            {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onInventoryMoveItemEvent (InventoryMoveItemEvent event)
    {
        InventoryHolder holder = event.getSource().getHolder();
        if(holder instanceof HopperMinecart ||holder instanceof Hopper)
        {
            ItemStack item = event.getItem();
            Material itemType = item.getType();
            if(itemType == Material.DRAGON_EGG)
            {
                event.setCancelled(true);
            }
        }
    }
}
