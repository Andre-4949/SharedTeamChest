package de.andre.SharedTeamChest.Controller;

import de.andre.SharedTeamChest.Main;

public class PluginController {
    private final Main main;
    private ConfigController config;
    private ListenerController listenerController;
    private SharedChestController sharedChestController;

    public PluginController(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public ConfigController getConfig() {
        return this.config;
    }

    public void setConfig(ConfigController config){
        this.config = config;
        config.onEnable();
        this.listenerController = new ListenerController(this);
        listenerController.onEnable();
        this.sharedChestController = new SharedChestController();
    }

    public ListenerController getListenerController() {
        return listenerController;
    }

    public SharedChestController getSharedChestController() {
        return sharedChestController;
    }

}
