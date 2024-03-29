package ibotus.ibshulker.events;

import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryMoveItemHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryMoveItemEvent (InventoryMoveItemEvent event)
    {
        InventoryHolder holder = event.getSource().getHolder();
        if(holder instanceof Hopper || holder instanceof HopperMinecart)
        {
            ItemStack item = event.getItem();
            Material itemType = item.getType();
            if(itemType == Material.DRAGON_EGG || itemType == Material.BUNDLE)
            {
                event.setCancelled(true);
            }
        }
    }
}
