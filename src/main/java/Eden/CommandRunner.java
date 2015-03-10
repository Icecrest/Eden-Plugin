package Eden;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import Eden.Schedule;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by SCurley on 2/25/2015.
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;
    public CommandRunner(Eden e){
        edenplugin = e;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("killdan")){
            killPlayer("DiamondDan_", commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase("curleyfly")){
            curleyFly(commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase("tpr") && commandSender instanceof Player){
            if(strings.length>= 0) {
                teleportToRandomLocation(strings[0], commandSender);
            }else{
                teleportToRandomLocation(null,commandSender);
            }
            return true;
        }else if(command.getName().equalsIgnoreCase("banlist")){
            edenBanList(commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase(("testeden"))){
            edenTest();
            return true;
        }else if(command.getName().equalsIgnoreCase("testplayer")){
            testPlayer(strings[0], commandSender);
            return true;
        }
        return false;
    }

    public Player getPlayer(String player){
        return Bukkit.getServer().getPlayer(player);
    }

    public boolean playerOnline(String player){ return getPlayer(player) != null; }

    public void killPlayer(String player, CommandSender sender){
        if(playerOnline(player)){
            getPlayer(player).setHealth(0);
            if(sender instanceof Player){
                sender.sendMessage(ChatColor.RED + player + " FATILITY");
            }
        }
        else{
            sender.sendMessage(ChatColor.GREEN + "This Player is not online!");
        }
    }

    public void curleyFly(CommandSender sender){
        if(playerOnline("Icecrest")){getPlayer("Icecrest").setFlying(true);}
        else{sender.sendMessage(ChatColor.BLUE + "That player is not online!");}
    }

    public void teleportToRandomLocation(String name, CommandSender sender){
        if(name != null) {
            Player p = getPlayer(name);
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = 255;

            p.teleport(new Location(p.getWorld(), x, y, z));
        }else{
            Player p = (Player) sender;
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = 255;

            p.teleport(new Location(p.getWorld(), x, y, z));

        }
    }

    public void edenBanList(CommandSender sender){
        Set<BanEntry> bans = Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries();
        if(!bans.isEmpty()) {
            sender.sendMessage(ChatColor.DARK_RED + "BAN LIST:" +
                    ChatColor.BOLD + bans.toString());
        }
        else{
            sender.sendMessage("No one is banned on this server");
        }
    }

    public void edenTest(){
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "This command has run");
        Bukkit.getMotd().equalsIgnoreCase("Dont use this server dummy");
    }

    public void testPlayer(String player, CommandSender sender){
        if(playerOnline(player)){
            sender.sendMessage(ChatColor.MAGIC+"aaaaa"+player + ChatColor.GOLD+"is online!"+
                                                ChatColor.MAGIC+"aaaaa"+ChatColor.GOLD+"\nRejoice!");
        }else{
            sender.sendMessage(player + "is not online!");
        }
    }
}
