package de.andre.SharedTeamChest.Command;

import de.andre.SharedTeamChest.Controller.MessageController;
import de.andre.SharedTeamChest.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ViewTeamChest implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!Util.controller.getConfig().isTeamChestActivated())return true;
        if (!(sender instanceof Player player))return true;
        if (!player.isOp())return true;
        if (args.length!=1){
            player.sendMessage(MessageController.getSERVERPREFIX()+"This command only takes one argument - the team name.");
            return true;
        }
        String teamName = args[0];
        Team team = Util.controller.getSharedChestController().getTeamByName(teamName);
        if (team==null){
            player.sendMessage(MessageController.getSERVERPREFIX()+"The team you entered doesn't exist.");
        }
        player.openInventory(Util.controller.getSharedChestController().getInvFromTeam(team));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeams().stream().map(Team::getName).toList();
    }
}
