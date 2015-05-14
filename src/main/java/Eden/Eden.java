package Eden;

import Eden.factions.TerritoryType;
import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.data.PluginData;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Icecrest on 2/24/2015.
 * @author Sean Curley
 */
public class Eden extends VevoxelPlugin {

    private PluginData data;
    private FileConfiguration config;
    private CommandRunner cmdr;
    private HashMap<Chunk, TerritoryType> map;
    private EdenStore store;

    @Override
    protected void loaded() {
        //For VAM, don't touch dumbass.
    }

    @Override
    public void enabled() {
        cmdr = new CommandRunner(this);

        getCommand("killdan").setExecutor(cmdr);
        getCommand("flysean").setExecutor(cmdr);
        getCommand("tpr").setExecutor(cmdr);
        getCommand("banlist").setExecutor(cmdr);
        getCommand("testeden").setExecutor(cmdr);
        getCommand("testplayer").setExecutor(cmdr);
        getCommand("smite").setExecutor(cmdr);
        getCommand("playgod").setExecutor(cmdr);
        getCommand("showinv").setExecutor(cmdr);
        getCommand("smitestick").setExecutor(cmdr);
        getCommand("create").setExecutor(cmdr);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        this.config = config;
        new Schedule(this).runTaskTimer(this, 0L, config.getInt("time")*60*20);
        getServer().getPluginManager().registerEvents(new Event(this), this);
        try {
            data.load();
        } catch (IOException | ClassNotFoundException e) {
            getConsole().error("Failed to load data!");
            getConsole().exception(e);
            setEnabled(false);
            return;
        }
        //store = data.get("gates") != null ? data.get("store") : new EdenStore();

    }

    @Override
    public void disabled() {

    }
    public FileConfiguration sendConfig(){
        return config;
    }
    public HashMap<Chunk, TerritoryType> sendMap(){return map;}
}
