package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryUtil {

    public static void closeInventory(Player player) {
        Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), player::closeInventory);
    }

    public static void openInventory(Player player, Inventory inventory) {
        Bukkit.getScheduler().runTask(ShootingGallery.getInstance(), () -> player.openInventory(inventory));
    }

    public static boolean contains(Player player, ItemStack itemStack) {
        List<ItemStack> itemStacks = new ArrayList<>(Arrays.asList(player.getInventory().getContents()));
        return itemStacks.contains(itemStack);
    }

}
