package run.tere.plugin.shootinggallery.listeners;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;
import run.tere.plugin.shootinggallery.ShootingGallery;
import run.tere.plugin.shootinggallery.defines.ArmorStandGamePrize;
import run.tere.plugin.shootinggallery.defines.GameStallStatus;
import run.tere.plugin.shootinggallery.defines.ItemContainer;
import run.tere.plugin.shootinggallery.utils.ChatUtil;
import run.tere.plugin.shootinggallery.utils.GameStallUtil;
import run.tere.plugin.shootinggallery.utils.ItemStackUtil;

import java.util.UUID;

public class ShootingGalleryMainListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity rightClickedEntity = e.getRightClicked();
        UUID gameStallUUID = GameStallUtil.getGameStallUUID(rightClickedEntity);
        if (gameStallUUID == null) return;
        GameStallUtil.buy(player);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        Entity damaged = e.getEntity();
        if (!damaged.getPersistentDataContainer().has(ArmorStandGamePrize.ARMOR_STAND_GAME_PRIZE_KEY, PersistentDataType.STRING)) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) return;
        UUID stallUUID = UUID.fromString(damaged.getPersistentDataContainer().get(ArmorStandGamePrize.ARMOR_STAND_GAME_PRIZE_KEY, PersistentDataType.STRING));
        e.setCancelled(true);
        if (e instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) e;
            Entity damager = entityDamageByEntityEvent.getDamager();
            if (!(damager instanceof Arrow)) return;
            Arrow arrow = (Arrow) damager;
            ProjectileSource projectileSource = arrow.getShooter();
            if (!arrow.getPersistentDataContainer().has(ItemContainer.shootingArrowEntityKey, PersistentDataType.STRING)) return;
            if (!(projectileSource instanceof Player)) return;
            Player shooter = (Player) projectileSource;
            GameStallStatus gameStallStatus = ShootingGallery.getInstance().getGameStallStatusHandler().getGameStallStatus(stallUUID);
            if (gameStallStatus == null) return;
            ArmorStandGamePrize armorStandGamePrize = gameStallStatus.getArmorStandGamePrize(damaged.getUniqueId());
            shooter.getInventory().addItem(armorStandGamePrize.getPrizeStack());
            shooter.getWorld().playSound(shooter.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 2F);
            ChatUtil.sendMessage(shooter, "§a景品ゲット!");
            gameStallStatus.remove(armorStandGamePrize);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();
        Block hitBlock = e.getHitBlock();
        if (!(projectile instanceof Arrow)) return;
        if (hitBlock == null) return;
        if (!projectile.getPersistentDataContainer().has(ItemContainer.shootingArrowEntityKey, PersistentDataType.STRING)) return;
        projectile.remove();
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e) {
        Entity projectile = e.getProjectile();
        ItemStack bowStack = e.getBow();
        ItemStack arrowStack = e.getConsumable();

        String bowContainerTag = ItemStackUtil.getItemKey(bowStack, ItemContainer.itemContainerKey);
        String arrowContainerTag = ItemStackUtil.getItemKey(arrowStack, ItemContainer.itemContainerKey);
        if (bowContainerTag == null || !bowContainerTag.equalsIgnoreCase("Bow")) return;
        if (arrowContainerTag == null || !arrowContainerTag.equalsIgnoreCase("Arrow")) return;
        if (!(projectile instanceof Arrow)) return;
        projectile.getPersistentDataContainer().set(ItemContainer.shootingArrowEntityKey, PersistentDataType.STRING, "Arrow");
    }

}
