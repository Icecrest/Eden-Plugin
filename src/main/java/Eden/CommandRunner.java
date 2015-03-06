package Eden;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.Random;
import java.util.Set;

/**
 * Created by SCurley3465 on 2/25/2015.
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;
    public CommandRunner(Eden e){
        edenplugin = e;
    }

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
            edenBanList();
            return true;
        }else if(command.getName().equalsIgnoreCase(("testeden"))){
            edenTest();
            return true;
        }else if(command.getName().equalsIgnoreCase("testplayer")){
            testPlayer(strings[0], commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase("announce")){
            announce();
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
        else{
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN+"This Player is not online!");
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

        player.teleport(new Location(player.getWorld(), x, y, z));
    }

    public void announce(){
        FileConfiguration config = edenplugin.sendConfig();
         Bukkit.getServer().broadcastMessage(org.bukkit.ChatColor.AQUA + "[BROADCAST] ");
        for(int i = 0; i <config.getStringList("announcements").size(); i++){


        }
    }

    public void edenBanList(){
        Set<BanEntry> bans = Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries();
        if(!bans.isEmpty()) {
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "BAN LIST:" +
                                                ChatColor.BOLD + bans.toString());
        }
        else{
            Bukkit.getServer().broadcastMessage("No one is banned on this server");
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
    public void showHelpFile(CommandSender sender){
        sender.sendMessage("");
    }
}
