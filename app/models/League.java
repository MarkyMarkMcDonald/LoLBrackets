package models;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/24/12
 * Time: 11:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class League extends Model {

    @Unique
    @Required(message = "Please give your league a name")
    @MinSize(value = 3, message = "Please enter a longer league name")
    @MaxSize(value = 20, message = "Please enter a shorter league name")
    private String name;

    @MinSize(value = 3, message = "Please enter a longer league password")
    @MaxSize(value = 3, message = "Please enter a shorter league password")
    private String leaguePassword;

    @ManyToMany(mappedBy = "leagues")
    private List<User> members;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "forLeagues")
    private List<Tournament> tournaments;

    public void removeUser(User user){
            members.remove(user);
    }
    public void addUser(User user){
        if (!members.contains(user)){
            members.add(user);
        }
    }

    public String toString(){
        return name;
    }

    public League(League league){
        this.name = league.name;
        this.leaguePassword = league.leaguePassword;
        this.owner = league.owner;
    }

    public void addTournament(Tournament tournament){
        tournaments.add(tournament);
    }

    //------------ Accessors and Modifiers ----
    public int getNumberOfMembers(){
        return members.size();
    }
    public String getLeaguePassword() {
        return leaguePassword;
    }

    public void setLeaguePassword(String leaguePassword) {
        this.leaguePassword = leaguePassword;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name ) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
