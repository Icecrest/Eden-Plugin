package Eden;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Icecrest on 3/20/2015.
 * @author Sean Curley
 */
public class Event implements Listener {

    /**
     *
     * @param s gives a string to make an item
     * @return custom ItemStack
     */
    public ItemStack createCustomItem(String s){
        switch (s.toUpperCase()) {
            case "SMITESTICK":
                ItemStack is = new ItemStack(Material.STICK, 1);
                List<String> strings = new ArrayList<>(2);
                strings.add(0, "An ancient stick said to ");
                strings.add(1, "hold the powers of the gods");
                is.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                ItemMeta itemMeta = is.getItemMeta();
                itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "The Smite Stick");
                itemMeta.setLore(strings);
                is.setItemMeta(itemMeta);
                return is;
            case "GODFEATHER":
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
                return is2;
        }
        return null;
    }

    /**
     * Strikes lightning at a target block up to 200 blocks away.
     * @param event that tracks right clicks done by players
     */
    @EventHandler
        public void onRightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack is = createCustomItem("smitestick");
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Set<Material> mats = null;
            if (p.getItemInHand().isSimilar(is)) {
                p.getWorld().strikeLightning(p.getTargetBlock(mats,200).getLocation());
            }
        }
    }

    /**
     * Strikes a LightningEffect at your location.
     * @param event that tracks right clicks done by players
     */
    @EventHandler
    public void playGod(PlayerInteractEvent event){
        Player p = event.getPlayer();
        ItemStack is = createCustomItem("godfeather");
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(p.getItemInHand().isSimilar(is)){
                p.getWorld().strikeLightningEffect(p.getLocation());
            }
        }
    }


}
