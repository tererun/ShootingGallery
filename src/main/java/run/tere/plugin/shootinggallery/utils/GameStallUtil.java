package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.GameStall;
import run.tere.plugin.shootinggallery.defines.ItemContainer;

import java.util.UUID;

public class GameStallUtil {

    public static final NamespacedKey GAME_STALL_CLERK_KEY;

    static {
        GAME_STALL_CLERK_KEY = new NamespacedKey(ShootingGallery.getInstance(), "GameStallClerkKey");
    }

    public static Villager spawnGameStallClerk(Location location, GameStall gameStall) {
        return location.getWorld().spawn(location, Villager.class, villager -> {
            villager.getPersistentDataContainer().set(GAME_STALL_CLERK_KEY, PersistentDataType.STRING, gameStall.getUUID().toString());
            villager.setAI(false);
            villager.setInvulnerable(true);
        });
    }

    public static UUID getGameStallUUID(Entity entity) {
        PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
        if (!persistentDataContainer.has(GAME_STALL_CLERK_KEY, PersistentDataType.STRING)) return null;
        String uuidString = persistentDataContainer.get(GAME_STALL_CLERK_KEY, PersistentDataType.STRING);
        return uuidString == null ? null : UUID.fromString(uuidString);
    }

    public static void buy(Player player) {
        ItemStack handItem = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
        if (handItem == null || !handItem.hasItemMeta()) {
            ChatUtil.sendClerkMessage(player, "§c通貨を持ちながら話しかけたら弓矢渡すで!");
            return;
        }

        ItemMeta handItemMeta = handItem.getItemMeta();
        Material handItemType = handItem.getType();
        if (!handItemMeta.hasCustomModelData() || handItemMeta.getCustomModelData() != 2) {
            ChatUtil.sendClerkMessage(player, "§c通貨を持ちながら話しかけたら弓矢渡すで!");
            return;
        }
        if (handItemType == Material.IRON_NUGGET) {
            giveShootingItem(player, 5);
        } else if (handItemType == Material.GOLD_NUGGET) {
            giveShootingItem(player, 25);
        } else {
            ChatUtil.sendClerkMessage(player, "§c通貨を持ちながら話しかけたら弓矢渡すで!");
            return;
        }
        handItem.setAmount(handItem.getAmount() - 1);
        SoundUtil.playPurchaseSound(player);
        ChatUtil.sendClerkMessage(player, "§aまいどあり! 頑張って当ててくれよ〜");
    }

    public static void giveShootingItem(Player player, int amount) {
        PlayerInventory inventory = player.getInventory();
        if (!inventory.contains(ItemContainer.getBowItem())) {
            inventory.addItem(ItemContainer.getBowItem());
        }
        ItemStack arrowStack = ItemContainer.getArrowItem();
        arrowStack.setAmount(amount);
        inventory.addItem(arrowStack);
    }

}
