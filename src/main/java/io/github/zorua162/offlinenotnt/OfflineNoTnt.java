package io.github.zorua162.offlinenotnt;

import io.github.zorua162.offlinenotnt.events.OnPlayerExit;
import world.bentobox.bentobox.api.addons.Addon;

public class OfflineNoTnt extends Addon {

    @Override
    public void onEnable() {
        // Check if it is enabled - it might be loaded, but not enabled.
        if (this.getPlugin() == null || !this.getPlugin().isEnabled()) {
            this.logError("BentoBox is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Check if addon is not disabled before.
        if (this.getState().equals(State.DISABLED))
        {
            this.logError("Challenges Addon is not available or disabled!");
            return;
        }
        getLogger().info("Loaded addon!");
        //Register player leave event
        this.registerListener(new OnPlayerExit(this));
    }

    @Override
    public void onDisable(){
        getLogger().info("Unloaded addon!");
    }
}
