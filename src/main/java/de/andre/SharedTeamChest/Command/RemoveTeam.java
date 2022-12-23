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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveTeam implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!Util.controller.getConfig().isTeamChestActivated())return true;
        if (!(sender instanceof Player player))return true;
        if (!player.isOp())return true;
        if (args.length!=2)return true;

        String teamString = args[0];
        Team t = Util.controller.getSharedChestController().getTeamByName(teamString);
        int dropItems = args[1].equalsIgnoreCase("true")?1:args[1].equalsIgnoreCase("false")?-1:0; //true 1 | -1 false | 0 undefined
        if (dropItems==0 || t==null){
            player.sendMessage(MessageController.getSERVERPREFIX()+"Please type in correct values.");
            return true;
        }
        if (dropItems==1){
            Util.controller.getSharedChestController().getInvFromTeam(t).forEach(x-> {
                if (x!=null)
                    player.getInventory().addItem(x).forEach((k, v) -> {
                        if (v!=null)
                            player.getWorld().dropItem(player.getLocation(), v);
                    });
            });
        }
        Util.controller.getSharedChestController().removeTeam(teamString);
        t.unregister();
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length==1) {
            return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeams().stream().map(Team::getName).toList();
        } else if (args.length==2){
            return new ArrayList<>(Arrays.asList("true", "false"));
        } else {
            return new ArrayList<>();
        }
    }
}
