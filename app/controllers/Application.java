package controllers;

import models.League;
import models.Tournament;
import models.User;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import services.Ownership;

import java.util.List;

/**
 * Deals with requests that require basic user authentication
 */
public class Application extends Controller {

    @Before
    public static void checkAuthenticated(){
        if(!session.contains("username")){
            Information.login();
        }
    }

    public static void logout(){
        session.remove("username");
        Information.index();
    }

    public static void dashboard(){
        User user = User.findByUsername(session.get("username"));
        List<Tournament> tournaments = Tournament.findAll();
        boolean isDashboard = true;
        render(user, isDashboard, tournaments);
    }

    public static void createLeague(){
        render("Leagues/create.html");
    }

    public static void processCreateLeague(@Valid League league){
        User user = User.findByUsername(session.get("username"));
        league = new League(league);
        league.setOwner(user);
        league.save();
        user.save();
        boolean isOwner = true;
        render("Leagues/view.html", league, isOwner);
    }

    public static void joinLeague(String leagueName,String password){
        User user = User.findByUsername(session.get("username"));
        League league = League.find("byName",leagueName).first();
        if (league.getLeaguePassword() != null && !league.getLeaguePassword().isEmpty()){
            addCurrentUserToLeaguePass(leagueName,password);
        }
        else {
            addCurrentUserToLeague(leagueName);
        }

    }

    public static void addCurrentUserToLeague(String leagueName){
        User user = User.findByUsername(session.get("username"));
        League league = League.find("byName",leagueName).first();
        // If the league exists and isn't already in the user's list, add it
        if (league != null && league.getLeaguePassword().isEmpty()) {
            if (user.getLeagues() != null) {
                if (!user.getLeagues().contains(league)){
                    user.joinLeague(league);
                    user.save();
                    league.save();
                }
            }
        }
        boolean isOwner = Ownership.isLeagueOwner(user, league);
        render("Leagues/view.html", league, isOwner);
    }

    public static void addCurrentUserToLeaguePass(String leagueName, String password){
        User user = User.findByUsername(session.get("username"));
        League league = League.find("byName",leagueName).first();
        // If the league exists and isn't already in the user's list, add it
        if (league != null) {
            if (user.getLeagues() != null) {
                if (!user.getLeagues().contains(league)){
                    if (league.getLeaguePassword().equals(password)){
                        user.joinLeague(league);
                        user.save();
                        league.save();
                    }
                }
            }
        }
        boolean isOwner = Ownership.isLeagueOwner(user, league);
        render("Leagues/view.html", league, isOwner);


    }

    public static void removeUserFromLeague(String username, String leagueName){
        User user = User.findByUsername(username);
        League league = League.find("byName",leagueName).first();
        // Make sure user and league exists
        if (user != null && league != null) {
            // Make sure current user is owner of the league
            if (session.get("username").equals(league.getOwner().getUsername())){
                user.leaveLeague(league);
                user.save();
                league.save();
            }
        }
        boolean isOwner = Ownership.isLeagueOwner(user, league);
        render("Leagues/view.html", league, isOwner);

    }

    public static void manageLeagueString(String leagueName){
        User user = User.findByUsername(session.get("username"));
        League league = League.find("byName",leagueName).first();
        boolean isOwner = Ownership.isLeagueOwner(user, league);
        render("Leagues/view.html", league, isOwner);
    }

    public static void manageLeague(User user, League league){
        user = User.findByUsername(session.get("username"));
        league = League.find("byName",league.getName()).first();
        boolean isOwner = Ownership.isLeagueOwner(user, league);
        render("Leagues/view.html", league, isOwner);
    }

    public static void createTournament(){
        User user = User.findByUsername(session.get("username"));

    }



}