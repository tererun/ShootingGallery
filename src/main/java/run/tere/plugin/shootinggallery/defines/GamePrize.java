package run.tere.plugin.shootinggallery.defines;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GamePrize {

    private String uuidString;
    private String prizeItem;

    public GamePrize(UUID uuid, ItemStack prizeItem) {
        this.uuidString = uuid.toString();
        this.prizeItem = NBTItem.convertItemtoNBT(prizeItem).toString();
    }



}
