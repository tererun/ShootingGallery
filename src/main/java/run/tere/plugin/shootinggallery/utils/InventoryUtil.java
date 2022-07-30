package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import run.tere.plugin.shootinggallery.ShootingGallery;

public class InventoryUtil {

    public static void closeInventory(Player player) {
        Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), player::closeInventory);
    }

    public static void openInventory(Player player, Inventory inventory) {
        Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), () -> player.openInventory(inventory));
    }

}
