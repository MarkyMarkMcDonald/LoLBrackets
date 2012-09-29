package controllers;

import models.Bracket;
import models.League;
import models.User;
import play.mvc.Controller;

/**
 * Deals with changes to leagues that require authentication by the admin of the league
 */
public class LeagueAdmin extends Controller{

    public static void removeUserFromLeague(String username, String leagueName){
        User user = User.find("byUsername",username).first();
        League league = League.find("byLeague",leagueName).first();
        // Make sure user and league exists
        if (user != null && league != null) {
            // Make sure current user is owner of the league
            if (session.get("username").equals(league.getOwner().getUsername())){
                league.removeUser(user);
                league.save();
            }
        }
        render("Leagues/view", league);
    }

    public static void addUserToLeague(String username, String leagueName){
        User user = User.find("byUsername",username).first();
        League league = League.find("byLeague",leagueName).first();
        // Make sure user and league exists
        if (user != null && league != null){
            // Make sure current user is ownder of the league
            if (session.get("username").equals(league.getOwner().getUsername())){
                league.addUser(user);
                league.save();
            }
        }
    }

    public static void updateResultsBracket(Bracket bracket){

    }

}
