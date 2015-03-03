package Commander;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

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
        }else if(command.getName().equalsIgnoreCase("banlist")){
            banList();
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

    public void announce(String[] ss){
         String s = ss[0];
         Bukkit.getServer().broadcastMessage(org.bukkit.ChatColor.AQUA+"[BROADCAST] "+
                                             org.bukkit.ChatColor.GREEN+s);
    }
    public void banList(){
        Set<BanEntry> bans = Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries();
        if(!bans.isEmpty()) {
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "BAN LIST:" +
                                                ChatColor.BOLD + bans.toString());
        }
        else{
            Bukkit.getServer().broadcastMessage("No one is banned on this server");
        }
    }
}