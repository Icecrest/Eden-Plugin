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

    private Eden eden;

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
            mats.add(Material.SNOW); mats.add(Material.LEAVES);
            mats.add(Material.LEAVES_2); mats.add(Material.WOOD);
            if (p.getItemInHand().isSimilar(is)) {
                int x; int y; int z;
                x = p.getTargetBlock(mats, 200).getX();
                y = p.getTargetBlock(mats, 200).getY();
                z = p.getTargetBlock(mats, 200).getZ();
                Location loc = new Location(p.getWorld(), (double)x,(double)y,(double)z);
                p.getWorld().strikeLightning(loc);
            }

        }
    }

}
