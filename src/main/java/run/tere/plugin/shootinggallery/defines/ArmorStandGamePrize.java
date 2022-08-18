package run.tere.plugin.shootinggallery.defines;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import run.tere.plugin.shootinggallery.ShootingGallery;

public class ArmorStandGamePrize {

    public static final NamespacedKey ARMOR_STAND_GAME_PRIZE_KEY = new NamespacedKey(ShootingGallery.getInstance(), "ArmorStandGamePrizeKey");
    private GameStallStatus gameStallStatus;
    private ItemStack prizeStack;
    private ArmorStand prizeStand;
    private boolean going;

    public ArmorStandGamePrize(GameStallStatus gameStallStatus, ItemStack prizeStack, Location startLocation) {
        this.gameStallStatus = gameStallStatus;
        this.prizeStack = prizeStack;
        this.prizeStand = startLocation.getWorld().spawn(startLocation, ArmorStand.class, armorStand -> {
            armorStand.setInvisible(true);
            armorStand.setSilent(true);
            armorStand.getEquipment().setHelmet(prizeStack.clone());
            armorStand.getPersistentDataContainer().set(ARMOR_STAND_GAME_PRIZE_KEY, PersistentDataType.STRING, gameStallStatus.getGameStall().getUUID().toString());
        });
        this.going = true;
    }

    public GameStallStatus getGameStallStatus() {
        return gameStallStatus;
    }

    public ItemStack getPrizeStack() {
        return prizeStack;
    }

    public ArmorStand getPrizeStand() {
        return prizeStand;
    }

    public boolean isGoing() {
        return going;
    }

    public void setGoing(boolean going) {
        this.going = going;
    }

}
