package de.andre.SharedTeamChest.Controller;

import de.andre.SharedTeamChest.Util;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigController {
    private final PluginController controller;
    public static final int SECONDSTOTICKS = 20;

    //----------------------------------------------------------//
    //Loaded from Config (with defaults if nothing is defined)
    private boolean teamChestActivated = true;
    private int rows = 6;
    private boolean rowsAmountPerPerson = false;
    //----------------------------------------------------------//
    private final File config;
    private final YamlConfiguration ymlConfig;

    public ConfigController(PluginController controller) {
        this.controller = controller;

        config = new File(controller.getMain().getDataFolder(), ConfigPaths.configFileName);
        ymlConfig = YamlConfiguration.loadConfiguration(config);
    }

    public void onEnable() {
        load();
        saveConfig();
    }

    /**
     * load Config
     */

    public void load() {
        this.teamChestActivated = this.ymlConfig.getBoolean(ConfigPaths.teamChestActivated, this.teamChestActivated);
        this.rows = this.ymlConfig.getInt(ConfigPaths.rows, this.rows);
        this.rowsAmountPerPerson = this.ymlConfig.getBoolean(ConfigPaths.rowsAmountPerPerson, this.rowsAmountPerPerson);
    }


    /**
     * save Config
     */

    public void saveConfig() {
        this.ymlConfig.set(ConfigPaths.teamChestActivated, this.teamChestActivated);
        this.ymlConfig.set(ConfigPaths.rows, this.rows);
        this.ymlConfig.set(ConfigPaths.rowsAmountPerPerson, this.rowsAmountPerPerson);
        try {
            ymlConfig.save(this.config.getAbsoluteFile());
            Util.sendInfoLogMessage(this.config.getAbsolutePath());
            Util.sendInfoLogMessage("Config saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getConfig() {
        return config;
    }


    public boolean isTeamChestActivated() {
        return teamChestActivated;
    }

    public int getRows() {
        return rows;
    }

    public boolean isRowsAmountPerPerson() {
        return rowsAmountPerPerson;
    }

}
