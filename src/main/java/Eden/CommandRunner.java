package Eden;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by SCurley on 2/25/2015.
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;
    private Event event;

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
        }else if(command.getName().equalsIgnoreCase("smite")){
            smitePlayer(strings[0]);
            return true;
        }else if(command.getName().equalsIgnoreCase("playgod") && commandSender instanceof Player){
            playGod(commandSender);
            return true;
        }else if(command.getName().equalsIgnoreCase("showinv") && commandSender instanceof Player){
            showInventory(strings[0]);
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

    public void smiteBlock(Player p){
        Set<Material> mats = new Set<Material>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Material> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Material material) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Material> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

        };
        mats.add(Material.GRASS);mats.add(Material.GRAVEL);
        mats.add(Material.STONE);mats.add(Material.DIRT);
        mats.add(Material.SNOW);
        if(p.getItemInHand().getType() == Material.STICK){
            if(event.onRightClick() == true)
            Location loc = p.getTargetBlock(mats, 1).getLocation();
            p.getWorld().strikeLightning(loc);
        }

    }
}
