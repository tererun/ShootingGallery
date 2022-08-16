package run.tere.plugin.shootinggallery.guis.defines;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
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
    private static ItemStack changeStallNameItem;

    private static ItemStack spawnClerkItem;

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
                "§d§l景品を変更",
                Collections.singletonList("§eクリックで景品を変更"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "");

        changeStallNameItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.NAME_TAG,
                1,
                "§9§l屋台名称を変更",
                Collections.singletonList("§eクリックで名称を変更"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "changeStallNameItem");

        spawnClerkItem = ItemStackUtil.addItemTag(ItemStackUtil.createItemStack(
                Material.VILLAGER_SPAWN_EGG,
                1,
                "§b§l店員をスポーン",
                Collections.singletonList("§eクリックで店員をスポーン"),
                -1
        ), GUI_CONTROL_ITEM_KEY, "spawnClerkItem");
    }

    public static ItemStack getFromSelectItem() {
        return fromSelectItem.clone();
    }

    public static ItemStack getToSelectItem() {
        return toSelectItem.clone();
    }

    public static ItemStack getDeleteSelectItem() {
        return deleteSelectItem.clone();
    }

    public static ItemStack getCreateSelectItem() {
        return createSelectItem.clone();
    }

    public static ItemStack getChangePrizeItem() {
        return changePrizeItem.clone();
    }

    public static ItemStack getChangeStallNameItem() {
        return changeStallNameItem.clone();
    }

    public static ItemStack getSpawnClerkItem() {
        return spawnClerkItem.clone();
    }

    public static ItemStack createGameStallItem(GameStall gameStall) {
        ItemStack itemStack = ItemStackUtil.createItemStack(Material.WHITE_BANNER, 1, gameStall.getName(), null, -1);
        return ItemStackUtil.addItemTag(itemStack, GUI_STALL_ITEM_KEY, gameStall.getUUID().toString());
    }

}
