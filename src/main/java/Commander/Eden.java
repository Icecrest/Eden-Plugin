package Commander;

import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.data.PluginConfiguration;
import io.vevox.vevoxel.vpm.VPMMeta;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Created by SCurley3465 on 2/24/2015.
 */
public class Eden extends VevoxelPlugin {


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
        saveDefaultConfig();
        FileConfiguration config = getConfig();
    }

    @Override
    public void disabled() {

    }
}
