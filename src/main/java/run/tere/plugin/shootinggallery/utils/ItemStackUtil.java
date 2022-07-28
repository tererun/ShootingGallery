package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemStackUtil {

    public static ItemStack createItemStack(Material material, int amount, String displayName, List<String> lore, int customModelData) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (displayName != null) itemMeta.setLore(lore);
        if (customModelData != -1) itemMeta.setCustomModelData(customModelData);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack addItemTag(ItemStack itemStack, NamespacedKey namespacedKey, String itemTag) {
        if (itemStack == null || !itemStack.hasItemMeta()) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, itemTag);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static String getItemKey(ItemStack itemStack, NamespacedKey namespacedKey) {
        if (itemStack == null || !itemStack.hasItemMeta()) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (!persistentDataContainer.has(namespacedKey, PersistentDataType.STRING)) return null;
        return persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
    }

}
