package Eden;


import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.Set;

/**
 * Created by SCurley3465 on 3/20/2015.
 * @author SeanCurley
 */
public class Event implements Listener {

    @EventHandler
        public void onRightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack is = new ItemStack(Material.STICK, 1);
        List<String> strings = new ArrayList<>(2);
        strings.add(0,"An ancient stick said to ");
        strings.add(1,"hold the powers of the gods");
        is.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.ITALIC + "The Smite Stick");
        itemMeta.setLore(strings);
        is.setItemMeta(itemMeta);
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Set<Material> mats = null;
            if (p.getItemInHand().isSimilar(is)) {

                p.getWorld().strikeLightning(p.getTargetBlock(mats,200).getLocation());
            }

        }
    }

}
