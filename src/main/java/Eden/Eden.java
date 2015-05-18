package Eden;

import Eden.factions.TerritoryType;
import io.vevox.vevoxel.api.VevoxelPlugin;
import io.vevox.vevoxel.data.PluginData;
import io.vevox.vevoxel.math.TimeUtil;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Created by Icecrest on 2/24/2015.
 * @author Sean Curley
 */
public class Eden extends VevoxelPlugin {

    private static final Random r = new Random();
    private PluginData data;
    private FileConfiguration config;
    private CommandRunner cmdr;
    private HashMap<Chunk, TerritoryType> map;
    private EdenStore store;
    private HashMap<Player, Effect> effects;

    @Override
    protected void loaded() {
        //For VAM, don't touch dumbass.
    }

    @Override
    public void enabled() {
        cmdr = new CommandRunner(this);
        data = getData();

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

        store = data.get("territories") != null ? data.get("territories", EdenStore.class) : new EdenStore();

        if(Bukkit.getOnlinePlayers().size() != 0) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
                @Override
                public void run() {
                    r.setSeed(System.nanoTime());
                    Player p = Bukkit.getOnlinePlayers().toArray(new Player[0])[r.nextInt(Bukkit.getOnlinePlayers().size())];
                    FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).flicker(true).trail(true)
                            .withFade(Color.RED, Color.WHITE).with(FireworkEffect.Type.STAR).build();
                    Firework f = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
                    FireworkMeta meta = f.getFireworkMeta();
                    meta.addEffect(effect);
                    f.detonate();
                }
            }, 5L, TimeUtil.TICKS_PER_MINUTE * 5);
        }
        //store = data.get("gates") != null ? data.get("store") : new EdenStore();

    }

    @Override
    public void disabled() {
        try {
            data.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileConfiguration sendConfig(){
        return config;
    }
    public HashMap<Chunk, TerritoryType> sendMap(){return map;}
    public HashMap<Player, Effect> sendEffects(){return effects;}
}
