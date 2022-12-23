package de.andre.SharedTeamChest;

import de.andre.SharedTeamChest.Controller.MessageController;
import de.andre.SharedTeamChest.Controller.PluginController;
import org.bukkit.Bukkit;

public class Util {
    public static PluginController controller = null;

    public static void sendInfoLogMessage(Object o){
        Bukkit.getLogger().info(MessageController.getSERVERPREFIX()+o.toString());
    }
}
