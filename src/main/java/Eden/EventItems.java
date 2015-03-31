package Eden;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCurley3465 on 3/27/2015.
 */
public enum EventItems {

    SMITE_STICK, GOD_FEATHER;

    public ItemStack getItemStack(){
        switch(this){
            case SMITE_STICK:
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
            case GOD_FEATHER:
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

}
