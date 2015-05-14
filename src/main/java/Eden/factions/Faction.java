package Eden.factions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * @author Sean Curley
 */
public class Faction implements Listener{

    private Player leader;
    private ArrayList<Player> members;
    private ArrayList<Player> officers;
    private ArrayList<Player> faction = new ArrayList<>();
    private String name;
    private int power;

    public Faction(Player p, String name){
        leader = p;
        this.name = name;
        faction.add(p);
    }

    public Faction(String name){
        this.name = name;
    }

    public void setLeader(Player p){
        if(!faction.contains(p)){
            faction.add(p);
        }
    }

    public void addMember(Player p, Player other){
        if(p.equals(leader)){
            members.add(other);
            faction.add(other);
        }
        else{
            p.sendMessage("Sorry, you don't have permission to do this.");
        }
    }

    public void promote(Player p, Player other){
        if(p.equals(leader) | officers.contains(p)){
            members.remove(other);
            officers.add(other);
            sendFactionMessage(ChatColor.BLUE + other.getDisplayName() + " has been promoted to Officer!");
        }
    }

    public void demote(Player p, Player other){
        if(p.equals(leader) | officers.contains(p)){
            officers.remove(other);
            members.add(other);
            sendFactionMessage(ChatColor.RED + other.getDisplayName() + " has been demoted to Member!");
        }
    }

    public void displayFaction(){
    }

    public void sendFactionMessage(String s){
        for(Player p: faction){
            p.sendMessage(s);
        }
    }

    public void transfer(Player p, Player other){
        if(p.equals(leader)){
            leader = other;
            sendFactionMessage(p.getDisplayName() + " has stepped down! Hail " + other.getDisplayName());
        }
    }

    @EventHandler
    public void addPower(Player p){

    }
}
