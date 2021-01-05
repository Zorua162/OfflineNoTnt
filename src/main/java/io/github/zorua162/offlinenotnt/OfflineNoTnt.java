package io.github.zorua162.offlinenotnt;

import io.github.zorua162.offlinenotnt.events.OnPlayerExit;
import world.bentobox.bentobox.api.addons.Addon;

public class OfflineNoTnt extends Addon {


    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        // Check if BentoBox is enabled - it might be loaded, but not enabled.
        if (this.getPlugin() == null || !this.getPlugin().isEnabled()) {
            this.logError("BentoBox is not available or disabled!");
            //Disable this addon as BentoBox was not enabled
            this.setState(State.DISABLED);
            return;
        }

        // Check if addon is not disabled before loading
        if (this.getState().equals(State.DISABLED))
        {
            this.logError("OfflineNoTnt Addon is not available or disabled!");
            return;
        }
        getLogger().info("Loaded OfflineNoTnt addon!");
        //Register player leave event
        this.registerListener(new OnPlayerExit(this));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable(){
        getLogger().info("Unloaded OfflineNoTnt addon!");
        //Not sure if the event needs to be unregistered, but I can't see a way to do this.
    }
}
