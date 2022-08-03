package run.tere.plugin.shootinggallery.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import run.tere.plugin.shootinggallery.ShootingGallery;

public class SoundUtil {

    public static void playPurchaseSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1F, 0.793701F);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1F, 1.587401F);
            }
        }.runTaskLater(ShootingGallery.getInstance(), 2L);
    }

}
