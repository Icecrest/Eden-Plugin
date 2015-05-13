package Eden.factions;

import org.bukkit.Chunk;

/**
 * @author Sean Curley
 */
public class Territory {
    private Chunk chunk;
    private Faction owner;

    public Territory(Chunk c){
       chunk = c;
    }

    public void setOwner(Faction f){
        if(owner != null){
            owner = f;
        }
    }

}
