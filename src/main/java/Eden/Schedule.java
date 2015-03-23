package Eden;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by SCurley3465 on 3/9/2015.
 * @author SeanCurley
 */
public class Schedule extends BukkitRunnable {
    private Eden edenplugin;
    public Schedule(Eden e){edenplugin = e;}
    int inc = 0;
    @Override
    public void run() {
        FileConfiguration config = edenplugin.sendConfig();
        ArrayList<String> announcements = new ArrayList<>();
        announcements.addAll(config.getStringList("announcements"));
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[BROADCAST] " + ChatColor.GREEN + announcements.get(inc++));
        if(inc>announcements.size()-1) inc = 0;
    }
}
