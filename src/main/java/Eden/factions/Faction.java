package Eden.factions;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author Sean Curley
 */
public class Faction {
    private Player leader;
    private ArrayList<Player> members;
    private ArrayList<Player> officers;
    private String name;
    private Faction nameFac;

    public Faction(Player p, String name){
        leader = p;
        this.name = name;
        nameFac = new Faction(name);
    }

    public Faction(String name){

    }

    public void setLeader(Player p){
        leader = p;
    }

    public void addMember(Player p, Player other){
        if(p.equals(leader)){
            members.add(other);
        }
        else{
            p.sendMessage("Sorry, you don't have permission to do this.");
        }
    }
}
