package Eden;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by SCurley on 2/25/2015.
 * @author SeanCurley
 */
public class CommandRunner implements CommandExecutor {

    private Eden edenplugin;

    public CommandRunner(Eden e) {
        edenplugin = e;
    }

    public void stahpIt(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "Stahp tryin' ye dumarse!");
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
            } else {
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
        }
        return false;
    }

    public Player getPlayer(String player) {
        return Bukkit.getServer().getPlayer(player);
    }

    public boolean playerOnline(String player) {
        return getPlayer(player) != null;
    }

    public void killPlayer(String player, CommandSender sender) {
        if (playerOnline(player)) {
            getPlayer(player).setHealth(0);
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + player + " FATILITY");
            }
        } else {
            sender.sendMessage(ChatColor.GREEN + "This Player is not online!");
        }
    }

    public void curleyFly(CommandSender sender) {
        Player p;
        if (playerOnline("Icecrest")) {
            p = getPlayer("Icecrest");
            p.setFlying(true);
            p.setFlySpeed(Float.MAX_VALUE);
        } else {
            sender.sendMessage(ChatColor.BLUE + "That player is not online!");
        }
    }

    public void teleportToRandomLocation(String name, CommandSender sender) {
        if (name != null) {
            Player p = getPlayer(name);
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

    public void edenBanList(CommandSender sender) {
        Set<BanEntry> bans = Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries();
        if (!bans.isEmpty()) {
            sender.sendMessage(ChatColor.DARK_RED + "BAN LIST:" +
                    ChatColor.BOLD + bans.toString());
        } else {
            sender.sendMessage("No one is banned on this server");
        }
    }

    public void edenTest() {
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "This command has run");
        Bukkit.getMotd().equalsIgnoreCase("Dont use this server dummy");
    }

    public void testPlayer(String player, CommandSender sender) {
        if (playerOnline(player)) {
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.MAGIC + "aaaaa" + ChatColor.RED + " " + player + " "
                    + ChatColor.GOLD + "is online! " + ChatColor.MAGIC + "aaaaa" + ChatColor.GOLD + "\nRejoice!");
        } else {
            sender.sendMessage(player + "is not online!");
        }
    }

    public void smitePlayer(String player) {
        Player p = getPlayer(player);
        p.getWorld().strikeLightning(p.getLocation());
        p.getWorld().setThunderDuration(2);
    }

    public void playGod(CommandSender sender) {
        ItemStack is2 = new ItemStack(Material.FEATHER, 1);
        List<String> strings2 = new ArrayList<>(2);
        strings2.add(0, "A feather that was given as a gift");
        strings2.add(1, " to man, so that they could bask in");
        strings2.add(2, " godlike glory. Too bad it's temporary...");
        is2.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
        ItemMeta itemMeta2 = is2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "The God Feather");
        itemMeta2.setLore(strings2);
        is2.setItemMeta(itemMeta2);
        Player p = (Player) sender;
        if (!p.getInventory().contains(is2)) {
            p.setItemInHand(is2);
        } else {
            p.sendMessage(ChatColor.AQUA + "You already have a God Feather!");
        }
    }

    public void showInventory(String s) {
        Player p = getPlayer(s);
        p.getOpenInventory();
    }

    public void smiteArea(CommandSender sender) {
        Player p = (Player) sender;
        ItemStack is = new ItemStack(Material.STICK, 1);
        List<String> strings = new ArrayList<>(2);
        strings.add(0, "An ancient stick said to ");
        strings.add(1, "hold the powers of the gods");
        is.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "The Smite Stick");
        itemMeta.setLore(strings);
        is.setItemMeta(itemMeta);
        if (!p.getInventory().contains(is)) {
            p.setItemInHand(is);
        } else {
            p.sendMessage(ChatColor.AQUA + "You already have a Smite Stick!");
        }
    }

    public void createWeapon(CommandSender sender, String[] strings) {
        Player p = (Player) sender;
        int item = 0;
        int enchant = 1;
        int level = 2;
        String i = strings[item].toUpperCase();
        String e = strings[enchant].toUpperCase();
        String l = strings[level].toUpperCase();
        ItemStack itemStack = null;
        ItemMeta itemMeta = null;
        switch (i) {
            case "WOODENSWORD":
                itemStack = new ItemStack(Material.WOOD_SWORD);
                itemMeta = itemStack.getItemMeta();
                switch (e) {
                    case "DURABILITY":
                        switch (l) {
                            case "1":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                                break;
                            case "2":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
                                break;
                            case "3":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
                                break;
                            case "4":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
                                break;
                            case "5":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                                break;
                            case "6":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
                                break;
                            case "7":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
                                break;
                            case "8":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 8);
                                break;
                            case "9":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
                                break;
                            case "10":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                                break;

                        }
                    case "SHARPNESS":
                        switch (l) {
                            case "1":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                                break;
                            case "2":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                                break;
                            case "3":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
                                break;
                            case "4":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                                break;
                            case "5":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
                                break;
                            case "6":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
                                break;
                            case "7":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
                                break;
                            case "8":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 8);
                                break;
                            case "9":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
                                break;
                            case "10":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                                break;

                        }
                }
                p.setItemInHand(itemStack);
            case "IRONSWORD":
                itemStack = new ItemStack(Material.IRON_SWORD);
                itemMeta = itemStack.getItemMeta();
                switch (e) {
                    case "DURABILITY":
                        switch (l) {
                            case "1":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                                break;
                            case "2":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
                                break;
                            case "3":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
                                break;
                            case "4":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
                                break;
                            case "5":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                                break;
                            case "6":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
                                break;
                            case "7":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
                                break;
                            case "8":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 8);
                                break;
                            case "9":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
                                break;
                            case "10":
                                itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                                break;

                        }
                    case "SHARPNESS":
                        switch (l) {
                            case "1":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                                break;
                            case "2":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                                break;
                            case "3":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
                                break;
                            case "4":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                                break;
                            case "5":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
                                break;
                            case "6":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
                                break;
                            case "7":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
                                break;
                            case "8":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 8);
                                break;
                            case "9":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
                                break;
                            case "10":
                                itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                                break;

                        }
                }
                p.setItemInHand(itemStack);
        }
    }

}
