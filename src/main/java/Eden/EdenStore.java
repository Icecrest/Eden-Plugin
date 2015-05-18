package Eden;

import Eden.factions.Territory;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sean Curley
 */
public class EdenStore implements Serializable {

    private Set<Territory> set = new HashSet<>();

    public void addTerritory(Territory t){
        set.add(t);
    }

}
