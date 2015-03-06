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

    @Override
    protected void loaded() {
        //For VPM, don't touch dumbass.
    }

    @Override
    public void enabled() {
        getCommand("killdan").setExecutor(new CommandRunner());
        getCommand("flysean").setExecutor(new CommandRunner());
        getCommand("tpr").setExecutor(new CommandRunner());
        getCommand("banlist").setExecutor(new CommandRunner());
        getCommand("testeden").setExecutor(new CommandRunner());
        getCommand("testplayer").setExecutor(new CommandRunner());
        getCommand("announce").setExecutor(new CommandRunner());
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        this.config = config;
    }

    @Override
    public void disabled() {

    }
    public FileConfiguration sendConfig(){
        return config;
    }
}
