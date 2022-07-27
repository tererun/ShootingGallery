package run.tere.plugin.shootinggallery.defines;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.handlers.GameStallHandler;

import java.util.UUID;

public class GamePrize {

    private String uuidString;
    private String prizeItem;

    public GamePrize(ItemStack prizeItem) {
        this.uuidString = UUID.randomUUID().toString();
        this.prizeItem = NBTItem.convertItemtoNBT(prizeItem).toString();
    }

    public UUID getUUID() {
        return UUID.fromString(uuidString);
    }

    public ItemStack getPrizeItem() {
        return NBTItem.convertNBTtoItem(new NBTContainer(prizeItem));
    }

    public void save() {
        GameStallHandler.saveGameStallHandler(ShootingGallery.getInstance().getGameStallHandler());
    }

}
