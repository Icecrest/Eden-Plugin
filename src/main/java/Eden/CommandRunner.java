package Eden;

import Eden.factions.Faction;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Icecrest on 2/25/2015.
 * @author Sean Curley
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;
    private ArrayList<Faction> factions;

    public CommandRunner(Eden e) {
        edenplugin = e;
    }

    /**
     * Has a String that writes out a special sequence
     * to a console if they try to be stupid...
     * @param sender gets the CommandSender
     */
    public void stahpIt(CommandSender sender) {
        for(int i = 0; i < 4; i++) {
            sender.sendMessage(ChatColor.RED + "WOT THE FOK DID YE JUST SAY 2 ME M8!?\n" +
                    ChatColor.GRAY + "i dropped out of newcastle primary skool im the sickest bloke ull ever meet & ive nicked ova 300 " +
                    "chocolate globbernaughts frum tha corner shop. im trained in street fitin' & im the strongest foker in " +
                    "tha entire newcastle gym. yer nothin to me but a cheeky lil bellend w/ a fit mum & fakebling. ill " +
                    "waste u and smash a fokin bottle oer yer head bruv, i swer 2 christ. ya think u can fokin run ya gabber at me " +
                    "whilst sittin on yer arse behind a lil screen? think again wanka. im callin me homeboys rite now preparin " +
                    "for a proper scrap. A roomble thatll make ur nan sore jus hearin about it. yer a waste bruv. me crew be all " +
                    "over tha place & ill beat ya to a proper fokin pulp with me fists wanka. if i aint satisfied w/ that ill borrow" +
                    " me m8s cricket paddle & see if that gets u the fok out o' newcastle ya daft kunt. if ye had seen this bloody " +
                    "fokin mess commin ye might a' kept ya gabber from runnin. but it seems yea stupid lil twat, innit? ima shite " +
                    "fury & ull drown in it m8. ur ina proper mess knob.");
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("killdan")) {
            killPlayer("DiamondDan_", commandSender);
            return true;
        } else if (command.getName().equalsIgnoreCase("curleyfly")) {
            curleyFly(commandSender);
            return true;
        } else if (command.getName().equalsIgnoreCase("tpr")) {
            if (strings.length >= 0 && commandSender instanceof Player) {
                teleportToRandomLocation(strings[0], commandSender);
            } else if (strings.length == 0 && commandSender instanceof Player) {
                teleportToRandomLocation(null, commandSender);
            } else if(strings.length == 0 && commandSender instanceof ConsoleCommandSender){
                stahpIt(commandSender);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("banlist")) {
            edenBanList(commandSender);
            return true;
        } else if (command.getName().equalsIgnoreCase(("testeden"))) {
            edenTest();
            return true;
        } else if (command.getName().equalsIgnoreCase("testplayer")) {
            testPlayer(strings[0], commandSender);
            return true;
        } else if (command.getName().equalsIgnoreCase("smite")) {
            smitePlayer(strings[0]);
            return true;
        } else if (command.getName().equalsIgnoreCase("playgod")) {
            if (commandSender instanceof Player) {
                playGod(commandSender);
            } else {
                stahpIt(commandSender);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("showinv")) {
            if (commandSender instanceof Player) {
                showInventory(strings[0]);
            } else {
                stahpIt(commandSender);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("smitestick")) {
            if (commandSender instanceof Player) {
                smiteArea(commandSender);
            } else {
                stahpIt(commandSender);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("create")) {
            if (commandSender instanceof Player && strings.length >= 0) {
                createWeapon(commandSender, strings);
            } else {
                stahpIt(commandSender);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("setminer")) {
            setMiner(commandSender);
            return true;
        } else if (command.getName().equalsIgnoreCase("seteffect") && commandSender instanceof Player) {
            setEffect(commandSender, strings[0]);
            return true;
        } else if (command.getName().equalsIgnoreCase("enchant") && commandSender instanceof Player){
            enchant(commandSender, strings);
            return true;
        }
        return false;
    }

    /**
     * Searches for a Player and returns returns them if the Player is online.
     * @param player String to search for a player by name.
     * @return searched for Player instance
     */
    public Player findPlayerByName(String player) {
        return Bukkit.getServer().getPlayer(player);
    }

    /**
     * Returns a boolean value if the Player is online.
     * @param player String searches for a player by their username
     * @return true/false if the Player is online or not
     */
    public boolean playerOnline(String player) {
        return findPlayerByName(player) != null;
    }

    /**
     * Kills a player of choice
     * @param player Player to kill.
     * @param sender tracks what/who is sending the command
     */
    public void killPlayer(String player, CommandSender sender) {
        if (playerOnline(player)) {
            findPlayerByName(player).setHealth(0);
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + player + " FATILITY");
            }
        } else {
            sender.sendMessage(ChatColor.GREEN + "This Player is not online!");
        }
    }

    /**
     * Makes Icecrest fly.
     * @param sender tracks who/what is sending the command
     */
    public void curleyFly(CommandSender sender) {
        Player p;
        if (playerOnline("Icecrest")) {
            p = findPlayerByName("Icecrest");
            p.setFlying(true);
            p.setFlySpeed(Float.MAX_VALUE);
        } else {
            sender.sendMessage(ChatColor.BLUE + "That player is not online!");
        }
    }

    /**
     * Teleports you or the player of choice to a random location in the world.
     * @param name searches for a Player to work with.
     * @param sender tracks who/what is sending the command.
     */
    public void teleportToRandomLocation(String name, CommandSender sender) {
        if (name != null) {
            Player p = findPlayerByName(name);
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = p.getWorld().getHighestBlockYAt(x, z);

            p.teleport(new Location(p.getWorld(), x, y, z));
        } else {
            Player p = (Player) sender;
            Random r = new Random();
            int x = r.nextInt(20000);
            int z = r.nextInt(20000);
            int y = p.getWorld().getHighestBlockYAt(x, z);

            p.teleport(new Location(p.getWorld(), x, y, z));

        }
    }

    /**
     * Outputs the bans to the sender
     * @param sender tracks who/what to send the list to.
     */
    public void edenBanList(CommandSender sender) {
        Set<BanEntry> bans = Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries();
        if (!bans.isEmpty()) {
            sender.sendMessage(ChatColor.DARK_RED + "BAN LIST:" +
                    ChatColor.BOLD + bans.toString());
        } else {
            sender.sendMessage("No one is banned on this server");
        }
    }

    @Deprecated
    public void edenTest() {
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "This command has run");
        Bukkit.getMotd().equalsIgnoreCase("Dont use this server dummy");
    }
    @Deprecated
    public void testPlayer(String player, CommandSender sender) {
        if (playerOnline(player)) {
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.MAGIC + "aaaaa" + ChatColor.RED + " " + player + " "
                    + ChatColor.GOLD + "is online! " + ChatColor.MAGIC + "aaaaa" + ChatColor.GOLD + "\nRejoice!");
        } else {
            sender.sendMessage(player + "is not online!");
        }
    }

    /**
     * Strikes a player with lightning.
     * @param player
     */
    public void smitePlayer(String player) {
        Player p = findPlayerByName(player);
        Location temp = p.getBedSpawnLocation();
        p.getWorld().strikeLightningEffect(p.getLocation());
        p.setHealth(0);


        p.getWorld().setThunderDuration(2);
    }

    /**
     * Gives a player a special item if they don't have one.
     * @param sender tracks who/what is sending the command
     */
    public void playGod(CommandSender sender) {
        ItemStack is = EventItems.GOD_FEATHER.getItemStack();
        Player p = (Player) sender;
        if (!p.getInventory().contains(is)) {
            p.getInventory().addItem(is);
            p.updateInventory();
        } else {
            p.sendMessage(ChatColor.AQUA + "You already have a God Feather!");
        }
    }

    /**
     * Shows the inventory of a player.
     * @param player gets a name of a player to search for
     */
    public void showInventory(String player) {
        Player p = findPlayerByName(player);
        p.getOpenInventory();
    }

    /**
     * Checks for, and gives a Smite Stick if the player does not have one
     * @param sender checks if the CommandSender is a player or not
     */
    public void smiteArea(CommandSender sender) {
        Player p = (Player) sender;
        ItemStack is = EventItems.SMITE_STICK.getItemStack();
        if (!p.getInventory().contains(is)) {
            p.getInventory().addItem(is);
            p.updateInventory();
        } else {
            p.sendMessage(ChatColor.AQUA + "You already have a Smite Stick!");
        }
    }

    /**
     * Creates a custom weapon.
     * @param sender checks who/what is sending the command
     * @param strings gets parameters for the weapon
     */
    public void createWeapon(CommandSender sender, String[] strings) {
        Player p = (Player) sender;
        ItemStack itemStack = new ItemStack(Material.valueOf(strings[0].toUpperCase()));
        itemStack.addUnsafeEnchantment(Enchantment.getByName(strings[1].toUpperCase()), Integer.parseInt(strings[2]));

        p.getInventory().addItem(itemStack);
        p.updateInventory();
    }

    public void setMiner(CommandSender sender){
        Player p = (Player)sender;
        Set<Material> mats = null;
        List<Block> area = p.getLastTwoTargetBlocks(mats, 2);
        Player  miner = (Player) p.getWorld().spawnEntity(p.getTargetBlock(mats, 200).getLocation(), EntityType.PLAYER);
        ItemStack itemStack = new ItemStack(p.getItemInHand());
        miner.setItemInHand(itemStack);
        while(miner.getTargetBlock(mats, 10).breakNaturally()){

        }
    }

    /**
     * Creates a new faction.
     * @param sender leader of the new faction.
     * @param name of the new faction.
     */
    public void createFaction(CommandSender sender, String name){
        factions.add(new Faction((Player) sender, name));
        sender.sendMessage("You have successfully created a new faction!");
    }


    public void setEffect(CommandSender sender, String s){
        edenplugin.sendEffects().put((Player)sender, Effect.valueOf(s.toUpperCase()));
    }

    public void lightningRider(CommandSender sender){
        Player p = (Player) sender;
        LightningStrike strike = p.getWorld().strikeLightning(p.getLocation());
        p.setPassenger(strike);
    }

    public void enchant(CommandSender sender, String[] strings){
            Player p = (Player)sender;
        p.getItemInHand().addUnsafeEnchantment(Enchantment.getByName(strings[0]), Integer.parseInt(strings[1]));
    }



}
