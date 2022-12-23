package de.andre.SharedTeamChest.Controller;

import org.bukkit.ChatColor;

public class MessageController {
    public static String SERVERPREFIX = "["+ ChatColor.RED+ "SharedTeamChest" + ChatColor.RESET + "] ";

    public static String getSERVERPREFIX() {
        return SERVERPREFIX;
    }
}
