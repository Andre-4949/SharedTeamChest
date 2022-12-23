package de.andre.SharedTeamChest.Command;

import de.andre.SharedTeamChest.Controller.MessageController;
import de.andre.SharedTeamChest.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TeamChest implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!Util.controller.getConfig().isTeamChestActivated())return true;
        if (!(sender instanceof Player player))return true;
        Team playerTeam = player.getScoreboard().getPlayerTeam(player);
        if (playerTeam!=null) {
            player.openInventory(Util.controller.getSharedChestController().getInvFromTeam(playerTeam));
        } else {
            player.sendMessage(MessageController.getSERVERPREFIX()+"You belong to no team, therefore you can't use this feature.");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
