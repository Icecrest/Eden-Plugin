package Eden;

import io.vevox.vevoxel.api.VevoxelPlugin;
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
        cmdr = new CommandRunner(this);
        getCommand("killdan").setExecutor(cmdr);
        getCommand("flysean").setExecutor(cmdr);
        getCommand("tpr").setExecutor(cmdr);
        getCommand("banlist").setExecutor(cmdr);
        getCommand("testeden").setExecutor(cmdr);
        getCommand("testplayer").setExecutor(cmdr);
        getCommand("smite").setExecutor(cmdr);
        getCommand("playgod").setExecutor(cmdr);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        this.config = config;
        new Schedule(this).runTaskTimer(this, 0L, config.getInt("time")*60*20);
    }

    @Override
    public void disabled() {

    }
    public FileConfiguration sendConfig(){
        return config;
    }
}
