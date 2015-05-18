package Eden;


import Eden.factions.TerritoryType;
import io.vevox.vevoxel.math.TimeUtil;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by SCurley3465 on 3/20/2015.
 * @author SeanCurley
 */
public class Event implements Listener {

    private Eden plugin;
    public Event(Eden plugin){
        this.plugin = plugin;
    }

    @EventHandler
        public void onRightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Set<Material> mats = null;
            if (p.getItemInHand().isSimilar(EventItems.SMITE_STICK.getItemStack())) {
                plugin.getConsole().debug("Smite stick right clicked!");
                p.getWorld().strikeLightning(p.getTargetBlock(mats,200).getLocation());
            }else if(p.getItemInHand().isSimilar(EventItems.GOD_FEATHER.getItemStack())){
                p.getWorld().strikeLightningEffect(p.getLocation());
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        int num = 0;
        Player p = e.getPlayer();
        if(p.getItemInHand() != null) {
            ItemStack is = p.getItemInHand();
            if(is.getType().equals(Material.DIAMOND_PICKAXE)){
                num++;
                ItemMeta im = is.getItemMeta();
                List<String> lore = new ArrayList<String>();
                lore.add("Number of blocks broken\nwith this pickaxe: " + num);
                im.setLore(lore);
            }
        }


    }

    /**
     * @deprecated
     * @param event
     */
    public void onLogin(PlayerLoginEvent event){
        Player p = event.getPlayer();
        if(p.hasPlayedBefore()){
            p.getInventory().addItem(EventItems.EASTER_EGG.getItemStack());
            p.updateInventory();
        }
        p.sendMessage(ChatColor.LIGHT_PURPLE + "HAPPY EASTER!");
    }

    @EventHandler
    public void onSight(ChunkLoadEvent e){
        if(!plugin.sendMap().containsKey(e.getChunk()))
            plugin.sendMap().put(e.getChunk(), TerritoryType.NEUTRAL);
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(plugin.sendEffects() != null) {
            p.playEffect(p.getLocation(), plugin.sendEffects().get(p), p);
        }

    }


}
