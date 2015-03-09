package Eden;

import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.data.ConfigHandler;
import io.vevox.vevoxel.data.ConfigurationSystem;
import io.vevox.vevoxel.data.PluginConfiguration;
import org.bukkit.configuration.file.FileConfiguration;


/**
 * Created by SCurley3465 on 2/24/2015.
 */
public class Eden extends VevoxelPlugin {

    private FileConfiguration config;
    private CommandRunner cmdr;

    @Override
    protected void loaded() {
        //For VAM, don't touch dumbass.
    }

    @Override
    public void enabled() {
        getCommand("killdan").setExecutor(cmdr);
        getCommand("flysean").setExecutor(cmdr);
        getCommand("tpr").setExecutor(cmdr);
        getCommand("banlist").setExecutor(cmdr);
        getCommand("testeden").setExecutor(cmdr);
        getCommand("testplayer").setExecutor(cmdr);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        this.config = config;
        new Schedule().runTaskTimer(this, 0L, config.getInt("time")*60*20);
    }

    @Override
    public void disabled() {

    }
    public FileConfiguration sendConfig(){
        return config;
    }
}
