package Commander;

import io.vevox.vevoxel.api.VevoxPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Created by SCurley3465 on 2/24/2015.
 */
public class CurlyFry extends VevoxPlugin {


    @Override
    public void enable() {
        getCommand("killdan").setExecutor(new CommandRunner());
        getCommand("flysean").setExecutor(new CommandRunner());
    }

    @Override
    public void disable() {

    }

    @Override
    public boolean requiresAuth() {
        return false;
    }




}
