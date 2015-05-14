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

    public Faction getOwner(){
        return owner;
    }

    public void getValue(int i) throws NullTerritoryException {
        switch(i){
         case(0):
             break;
         case(1):
             break;
         case(2):
             break;
         case(3):
             break;
         case(4):
             break;
         case(5):
             break;
         default:
             throw new NullTerritoryException("No territory on this chunk!");
        }
    }
}
