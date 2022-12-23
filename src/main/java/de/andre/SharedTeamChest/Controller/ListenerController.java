package de.andre.SharedTeamChest.Controller;

import de.andre.SharedTeamChest.Listener.SharedTeamChestListener;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class ListenerController {
    private final PluginController controller;

    private ArrayList<SharedTeamChestListener> listeners = new ArrayList<>();

    public ListenerController(PluginController controller) {
        this.controller = controller;
    }

    public void onEnable(){
        registerListener();
    }

    private void registerListener(){
        HandlerList.unregisterAll(controller.getMain());
        listeners.forEach(listener->controller.getMain().getServer().getPluginManager().registerEvents(listener, controller.getMain()));
    }

    public void addListener(SharedTeamChestListener listener){
        this.listeners.add(listener);
        controller.getMain().getServer().getPluginManager().registerEvents(listener,controller.getMain());
    }

    public SharedTeamChestListener getListener(Class<? extends SharedTeamChestListener> c){
        for (SharedTeamChestListener listener : listeners) {
            if (listener.getClass().equals(c))return listener;
        }
        return null;
    }

}
