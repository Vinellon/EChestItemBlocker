package ibotus.ibshulker;

import ibotus.ibshulker.configurations.Config;
import ibotus.ibshulker.events.*;
import ibotus.ibshulker.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    private void msg(String msg) {
        String prefix = ChatUtils.color("&aIBShulker &7| ");
        Bukkit.getConsoleSender().sendMessage(ChatUtils.color(prefix + msg));
    }

    @Override
    public void onEnable() {
        Config.loadYaml(this);
        Bukkit.getConsoleSender().sendMessage("");
        this.msg("&fDeveloper: &aIBoTuS");
        this.msg("&fVersion: &dv" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("");
        getServer().getPluginManager().registerEvents(new InventoryClickEventHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEventHandler(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseEventHandler(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenEventHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsEventHandler(), this);
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
        this.msg("&fDisable plugin.");
        Bukkit.getConsoleSender().sendMessage("");
    }

}

