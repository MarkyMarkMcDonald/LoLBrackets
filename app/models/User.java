package models;

import play.data.validation.*;
import play.db.jpa.Model;
import play.mvc.Scope;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Marky
 * Date: 9/24/12
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class User extends Model {

//    @Unique("That Username is Unavailable")
    @Required(message = "Please enter a username")
    @MinSize(value=5, message = "Username too short")
    @MaxSize(value = 20, message = "Username too long")
    private String username;

//    @Unique("That Email already has an Associated Account")
    @Required(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    private String email;

    @ManyToMany
    private List<League> leagues;

    @OneToMany(mappedBy = "owner")
    private List<League> ownedLeagues;

    @OneToMany(mappedBy = "owner")
    private List<Tournament> ownedTournaments;

    @OneToMany(mappedBy = "madeBy")
    private List<Prediction> predictionsMade;

    @Required(message = "Please enter a password")
    @MaxSize(value=20, message = "Password too long")
    @MinSize(value=5, message = "Password too short")
    private String password;

    private League favoriteLeague;

    private Boolean mayMakeTournaments;

    public void joinLeague(League league){
        leagues.add(league);
        league.addUser(this);
    }

    public void leaveLeague(League league){
        leagues.remove(league);
        league.removeUser(this);
    }

    public User(User user){
        this.password = user.password;
        this.email = user.email;
        this.username = user.username;
    }

    public String toString(){
        return username;
    }

    public static User findByUsername(String username){
        return User.find("byUsername", username).first();
    }
    //--------------------- Accessors and Modifiers ------------------

    public Boolean getMayMakeTournaments() {
        return mayMakeTournaments;
    }

    public void setMayMakeTournaments(Boolean mayMakeTournaments) {
        this.mayMakeTournaments = mayMakeTournaments;
    }

    public List<Prediction> getPredictionsMade() {
        return predictionsMade;
    }

    public void setPredictionsMade(List<Prediction> predictionsMade) {
        this.predictionsMade = predictionsMade;
    }

    public List<Tournament> getOwnedTournaments() {
        return ownedTournaments;
    }

    public void setOwnedTournaments(List<Tournament> ownedTournaments) {
        this.ownedTournaments = ownedTournaments;
    }

    public List<League> getOwnedLeagues() {
        return ownedLeagues;
    }

    public void setOwnedLeagues(List<League> ownedLeagues) {
        this.ownedLeagues = ownedLeagues;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public League getFavoriteLeague() {
        return favoriteLeague;
    }

    public void setFavoriteLeague(League favoriteLeague) {
        this.favoriteLeague = favoriteLeague;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}
