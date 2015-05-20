package Eden;

import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;

/**
 * @author Sean Curley
 */
public class LightningRider {

    private LightningStrike strike;

    public LightningRider(Player p){
        p.setPassenger(strike);
    }

}
