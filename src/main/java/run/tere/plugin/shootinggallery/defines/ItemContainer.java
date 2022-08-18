package run.tere.plugin.shootinggallery.defines;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.utils.ItemStackUtil;

import java.util.Collections;

public class ItemContainer {

    public static NamespacedKey itemContainerKey;
    public static NamespacedKey shootingArrowEntityKey;

    private static ItemStack bowItem;
    private static ItemStack arrowItem;

    static {
        itemContainerKey = new NamespacedKey(ShootingGallery.getInstance(), "itemContainerKey");
        shootingArrowEntityKey = new NamespacedKey(ShootingGallery.getInstance(), "shootingArrowEntityKey");

        bowItem = ItemStackUtil.addItemTag(
                ItemStackUtil.createItemStack(
                        Material.BOW,
                        1,
                        "§6§l射的用弓",
                        Collections.singletonList(" §e頑張って的に当てよう!"),
                        2
                ),
                itemContainerKey,
                "Bow"
        );
        ItemMeta bowItemMeta = bowItem.getItemMeta();
        bowItemMeta.setUnbreakable(true);
        bowItem.setItemMeta(bowItemMeta);

        arrowItem = ItemStackUtil.addItemTag(
                ItemStackUtil.createItemStack(
                        Material.ARROW,
                        1,
                        "§6§l射的用矢",
                        Collections.singletonList(" §e頑張って的に当てよう!"),
                        2
                ),
                itemContainerKey,
                "Arrow"
        );
    }

    public static ItemStack getBowItem() {
        return bowItem.clone();
    }

    public static ItemStack getArrowItem() {
        return arrowItem.clone();
    }

}
