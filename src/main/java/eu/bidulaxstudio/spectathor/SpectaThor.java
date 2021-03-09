package eu.bidulaxstudio.spectathor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpectaThor extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();
        if (joinedPlayer.getGameMode() == GameMode.SPECTATOR) {
            event.setJoinMessage(null);
            for (Player randomPlayer : Bukkit.getOnlinePlayers())
            if (randomPlayer.getGameMode() != GameMode.SPECTATOR)
            randomPlayer.hidePlayer(this, joinedPlayer);
        }
        else {
            for (Player randomPlayer : Bukkit.getOnlinePlayers())
            if (randomPlayer.getGameMode() == GameMode.SPECTATOR) joinedPlayer.hidePlayer(this, randomPlayer);
            else joinedPlayer.showPlayer(this, randomPlayer);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            event.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player gamemodePlayer = event.getPlayer();
        if (event.getNewGameMode() == GameMode.SPECTATOR) {
            for (Player randomPlayer : Bukkit.getOnlinePlayers()) {
                if (randomPlayer.getGameMode() != GameMode.SPECTATOR) {
                    randomPlayer.hidePlayer(this, gamemodePlayer);
                }
            }
        }
        else {
            for (Player randomPlayer : Bukkit.getOnlinePlayers())
            randomPlayer.showPlayer(this, gamemodePlayer);
        }
    }

}
