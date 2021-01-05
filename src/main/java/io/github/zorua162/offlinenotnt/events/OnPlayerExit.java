package io.github.zorua162.offlinenotnt.events;
import io.github.zorua162.offlinenotnt.OfflineNoTnt;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.eclipse.jdt.annotation.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.eclipse.jdt.annotation.Nullable;
import world.bentobox.bentobox.api.addons.GameModeAddon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.util.Optional;

public class OnPlayerExit implements Listener {

    final private OfflineNoTnt addon;

    public OnPlayerExit(@NonNull OfflineNoTnt addon) {
        this.addon = addon;
    }

    /**
     * Disable TNT flag if all players on an island leave
     *
     * @param e event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerQuit(final PlayerQuitEvent e) {
        // Disable TNT flag if all island players have left
        User user = addon.getPlayers().getUser(e.getPlayer().getUniqueId());
        //Only gets the first addon i.e Bskyblock TODO: Make this more robust if multiple addons are present
        GameModeAddon gameModeAddon = addon.getPlugin().getAddonsManager().getGameModeAddons().get(0);

        if(gameModeAddon == null) {
            return;
        }

        @Nullable Island island = addon.getIslands().getIsland(gameModeAddon.getOverWorld(), user);
        if (island == null) {
            return;
        }
        //TNT hardcoded as currently this is the only flag that it seems this is needed for.
        @NonNull Optional<Flag> tntFlag;
        tntFlag = addon.getPlugin().getFlagsManager().getFlag("TNT_DAMAGE");

        island.setSettingsFlag(tntFlag.get(), false);
    }
}
