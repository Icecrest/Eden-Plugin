package Eden;


import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by SCurley3465 on 3/20/2015.
 */
public class Event implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction()== Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ){
            return true;
        }else{
            return false;
        }

    }

}
