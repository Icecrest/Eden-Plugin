package Eden;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.*;

import org.bukkit.Material;

/**
 * Created by SCurley on 2/25/2015.
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;
    private Event event;
    private CommandSender sender;

    public CommandRunner(Eden e){
        edenplugin = e;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        this.sender = sender;
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
        }else if(command.getName().equalsIgnoreCase("smite")){
            smitePlayer(strings[0]);
            return true;
        }else if(command.getName().equalsIgnoreCase("playgod") && commandSender instanceof Player){
            playGod(commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase("showinv") && commandSender instanceof Player){
            showInventory(strings[0]);
            return true;
        }else if(command.getName().equalsIgnoreCase("smitestick") && commandSender instanceof Player){
            smiteArea(sender);
        }
        return false;
    }
    public CommandSender getSender(){
        return sender;
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
        Player p;
        if(playerOnline("Icecrest")){
            p = getPlayer("Icecrest");
            p.setFlying(true);
            p.setFlySpeed(Float.MAX_VALUE);
        }
        else{sender.sendMessage(ChatColor.BLUE + "That player is not online!");}
    }

    public void teleportToRandomLocation(String name, CommandSender sender){
        if(name != null) {
            Player p = getPlayer(name);
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = p.getWorld().getHighestBlockYAt(x,z);

            p.teleport(new Location(p.getWorld(), x, y, z));
        }else{
            Player p = (Player) sender;
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = p.getWorld().getHighestBlockYAt(x,z);

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
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.MAGIC + "aaaaa" + ChatColor.RED + " " + player + " "
                    + ChatColor.GOLD + "is online! " + ChatColor.MAGIC + "aaaaa" + ChatColor.GOLD + "\nRejoice!");
        }else{
            sender.sendMessage(player + "is not online!");
        }
    }

    public void smitePlayer(String player){
        Player p = getPlayer(player);
        p.getWorld().strikeLightning(p.getLocation());
        p.getWorld().setThunderDuration(2);
    }

    public void playGod(CommandSender sender){
        if(sender instanceof Player) {
            Player p = (Player) sender;
            p.getWorld().strikeLightningEffect(p.getLocation());
        }
        else{
            Bukkit.getConsoleSender().sendMessage("You are not a player!");
        }
    }

    public void showInventory(String s){
        Player p = getPlayer(s);
        p.getOpenInventory();
    }

    public void smiteArea(CommandSender sender){
        Player p = (Player) sender;
        ItemStack is = new ItemStack(Material.STICK, 1);
        List<String> strings = new ArrayList<String>(2);
        strings.add(0,"An ancient stick said to ");
        strings.add(1,"hold the powers of the gods");
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "The Smite Stick");
        itemMeta.setLore(strings);
        if(p.getItemInHand().getType() != Material.STICK){
            p.setItemInHand(is);
        }else{
            p.sendMessage(ChatColor.AQUA + "You already have a stick in hand!");
        }
    }

}
