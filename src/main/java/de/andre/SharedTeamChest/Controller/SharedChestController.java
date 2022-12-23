package de.andre.SharedTeamChest.Controller;

import de.andre.SharedTeamChest.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SharedChestController {

    private File file;
    private YamlConfiguration config;

    private HashMap<Team, Inventory> teamInventories = new HashMap<>();


    public SharedChestController() {
        this.file = new File(Util.controller.getMain().getDataFolder().getAbsolutePath(), "sharedChestStorage.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        if (Util.controller.getConfig().isTeamChestActivated())
            load();
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        for (Team team : Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeams()) {
            if (config.getKeys(false).contains(team.getName())) {
                loadTeam(team);
            }
        }
    }

    public void save() {
        for (Team team : this.teamInventories.keySet()) {
            this.config.set(team.getName() + ".content", this.teamInventories.get(team).getContents());
        }
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void loadTeam(Team team) {
//        ItemStack[] itemStacks = new ItemStack[54];
//        List<ItemStack> content = ((List<ItemStack>) this.config.get(team.getName() + ".content"));
//        for (int i = 0; content != null && i < content.size() && i < 53; i++) {
//            itemStacks[i] = content.get(i);
//        }
//        Inventory inv = Bukkit.createInventory(null, 54);
//        inv.setContents(itemStacks);
//        this.teamInventories.put(team, inv);
//    }

    public void loadTeam(Team team) {
        ConfigController config = Util.controller.getConfig();
        List<ItemStack> content = (List<ItemStack>) this.config.get(team.getName() + ".content");
        if (content != null) {
            int rows = config.isRowsAmountPerPerson() ? Math.max((int) Math.ceil(content.stream().filter(Objects::nonNull).toList().size() / 9D), team.getSize()) : config.getRows();

            int itemSlots = Math.min(rows * 9, 54);

            ItemStack[] itemStacks = new ItemStack[itemSlots];

            content = content.stream().filter(Objects::nonNull).toList();

            for (int i = 0; i < content.size(); i++) {
                itemStacks[i] = content.get(i);
            }

            Inventory inv = Bukkit.createInventory(null, itemSlots);
            inv.setContents(itemStacks);
            this.teamInventories.put(team, inv);
            return;
        }
        this.teamInventories.put(team, Bukkit.createInventory(null, Math.min(team.getSize() * 9, 54)));
    }

    public void createNewInv(Team team) {
        this.teamInventories.put(team, Bukkit.createInventory(null, 54));
    }

    public Inventory getInvFromTeam(Team team) {
        if (!this.teamInventories.containsKey(team)) {
            createNewInv(team);
        }
        return this.teamInventories.get(team);
    }

    public void removeTeam(Team team) {
        this.teamInventories.remove(team);
    }

    public void removeTeam(String teamName) {
        Team t = getTeamByName(teamName);
        if (t != null) {
            removeTeam(t);
        }
    }

    public Team getTeamByName(String teamName) {
        Team t = null;

        for (Team team : this.teamInventories.keySet()) {
            if (team != null && team.getName().equals(teamName)) {
                t = team;
            }
        }
        if (t == null) {
            if (Bukkit.getScoreboardManager().getMainScoreboard().getTeams().stream().anyMatch(x -> x.getName().equals(teamName))) {
                t = Bukkit.getScoreboardManager().getMainScoreboard().getTeams().stream().filter(x -> x.getName().equals(teamName)).toList().get(0);
                this.createNewInv(t);
                return t;
            }
        }

        return t;
    }

}
