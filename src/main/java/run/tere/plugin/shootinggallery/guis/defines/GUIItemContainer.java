package run.tere.plugin.shootinggallery.guis.defines;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.utils.ItemStackUtil;

import java.util.Collections;

public class GUIItemContainer {

    public static final NamespacedKey GUI_CONTROL_ITEM_KEY;
    public static final NamespacedKey GUI_STALL_ITEM_KEY;

    private static ItemStack fromSelectItem;
    private static ItemStack toSelectItem;
    private static ItemStack deleteSelectItem;
    private static ItemStack createSelectItem;
    private static ItemStack changePrizeItem;

    static {
        GUI_CONTROL_ITEM_KEY = new NamespacedKey(ShootingGallery.getInstance(), "guiControlItemKey");
        GUI_STALL_ITEM_KEY = new NamespacedKey(ShootingGallery.getInstance(), "guiStallItemKey");

        fromSelectItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.GREEN_BANNER,
                1,
                "§a§l始点を設定",
                Collections.singletonList("§e現在位置§fを始点に設定"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "fromSelectItem");

        toSelectItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.RED_BANNER,
                1,
                "§c§l終点を設定",
                Collections.singletonList("§e現在位置§fを終点に設定"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "toSelectItem");

        deleteSelectItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.LAVA_BUCKET,
                1,
                "§4§l屋台を削除",
                Collections.singletonList("§eクリックで屋台を削除"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "deleteSelectItem");

        createSelectItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.CRAFTING_TABLE,
                1,
                "§6§l屋台を作成",
                Collections.singletonList("§eクリックで屋台を作成"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "createSelectItem");

        changePrizeItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.CHEST,
                1,
                "§d景品を変更§l",
                Collections.singletonList("§eクリックで景品を変更"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "changePrizeItem");
    }



    public static String getItemKey(ItemStack itemStack, NamespacedKey namespacedKey) {
        if (itemStack == null || !itemStack.hasItemMeta()) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (!persistentDataContainer.has(namespacedKey, PersistentDataType.STRING)) return null;
        return persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
    }

}
