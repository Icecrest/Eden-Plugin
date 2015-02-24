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
        new BukkitRunnable(){

            @Override
            public void run() {
                killDaniel();
            }
        }.runTaskTimer(this, 0L, 20L*60L*5L);
    }

    @Override
    public void disable() {

    }

    @Override
    public boolean requiresAuth() {
        return false;
    }

    public Player getDaniel(){
        return Bukkit.getServer().getPlayer("DiamondDan_");
    }

    public boolean isDanielOnline(){
        return getDaniel() != null;
    }

    public void killDaniel(){
        if(isDanielOnline()){
            getDaniel().setHealth(0);
        }
    }

    public void curleyFly(){

    }
}
