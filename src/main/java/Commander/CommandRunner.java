package Commander;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CORBA.UserException;

import java.util.Random;
/**
 * Created by SCurley3465 on 2/25/2015.
 */
public class CommandRunner implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("killdan")){
            killPlayer("DiamondDan_");
            return true;
        }else if(command.getName().equalsIgnoreCase("curleyfly")){
            curleyFly();
            return true;
        }else if(command.getName().equalsIgnoreCase("tpr") && commandSender instanceof Player){
            teleportToRandomLocation((Player) commandSender);
            return true;
        }
        return false;
    }

    public Player getPlayer(String player){
        return Bukkit.getServer().getPlayer(player);
    }

    public boolean playerOnline(String player){ return getPlayer(player) != null; }

    public void killPlayer(String player){
        if(playerOnline(player)){
            getPlayer(player).setHealth(0);
            Bukkit.getServer().broadcastMessage(org.bukkit.ChatColor.RED+player+" FATILITY");
        }
    }

    public void curleyFly(){
        getPlayer("Icecrest").setFlying(true);
    }

    public void teleportToRandomLocation(Player player){
        Random r = new Random();
        int x = r.nextInt(20000);
        int z = r.nextInt(20000);
        int y = 255;

        player.teleport(new Location(player.getWorld(),x,y,z));
    }
}
