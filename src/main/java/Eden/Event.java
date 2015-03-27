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
 * Created by SCurley3465 on 3/20/2015.
 * @author SeanCurley
 */
public class Event implements Listener {

    @EventHandler
        public void onRightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Set<Material> mats = null;
            if (p.getItemInHand().isSimilar(EventItems.SMITE_STICK.getItemStack())) {
                p.getWorld().strikeLightning(p.getTargetBlock(mats,200).getLocation());
            }else if(p.getItemInHand().isSimilar(EventItems.GOD_FEATHER.getItemStack())){
                p.getWorld().strikeLightningEffect(p.getLocation());
            }
        }
    }


}
