package Eden;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by SCurley3465 on 3/9/2015.
 */
public class Schedule extends BukkitRunnable {
    private Eden edenplugin = new Eden();
    @Override
    public void run() {
        FileConfiguration config = edenplugin.sendConfig();
        ArrayList<String> annoucements = new ArrayList<>();
        annoucements.addAll(config.getStringList("annoucements"));
        Bukkit.getServer().broadcastMessage(org.bukkit.ChatColor.AQUA + "[BROADCAST] ");
        for(int i = 0; i <annoucements.size(); i++){
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + annoucements.get(0));
            config.getInt("time");
        }
    }
}
