package controllers;

import models.*;
import play.mvc.Before;
import play.mvc.Controller;
import services.Ownership;

import java.util.List;

/**
 * Deals with changes to Tournaments that require authentication by the admin of the Tournaments
 */
public class TournamentAdmin extends Controller {

    @Before
    public static void checkAuthenticated(){
        if(!session.contains("username")){
            Information.login();
        }
        User user = User.findByUsername(session.get("username"));
        if (!user.getMayMakeTournaments()){
            render("Application/dashboard.html", user);
        }
    }
    
    public static void addTournamentToLeague(String tournamentName, String leagueName){
        Tournament tournament = Tournament.find("byName",tournamentName).first();
        League league = League.find("byName",leagueName).first();
        if (!league.getTournaments().contains(tournament)){
            league.addTournament(tournament);
            tournament.addLeague(league);
            league.save();
            tournament.save();
        }
        render("Leagues/view.html",league);

    }

    public static void addTournament(String tournamentName){
        Tournament tournament = Tournament.find("byOwner",session.get("username")).first();
        User user = User.findByUsername(session.get("username"));
        List<League> usersOwnedLeagues = user.getOwnedLeagues();
        // If the admin of the league only administrates one league, don't ask for league selection  
        if (usersOwnedLeagues.size() == 1){
            //May need to do add here in order to not pass extra params
            addTournamentToLeague(tournament.getName(), usersOwnedLeagues.get(0).getName());
        }
        else {
            render("Tournaments/signup.html");
        }
    }

    public static void createRoundRobin(){
        render("Brackets/robin.html");
    }

    public static void createElimBracket(){
        render("Brackets/elim.html");
    }

    public static void addRoundRobinToTournament(String tournamentName, RobinBracket bracket){
        User user = User.findByUsername(session.get("username"));
        Tournament tournament = Tournament.findByName(tournamentName);
        if (Ownership.isTournamentOwner(user,tournament)){
            tournament.getBracketsTemplate().addBracket(bracket);
        }
    }

    public static void addElimToTournament(String tournamentName,ElimBracket bracket){
        User user = User.findByUsername(session.get("username"));
        Tournament tournament = Tournament.findByName(tournamentName);
        if (Ownership.isTournamentOwner(user,tournament)){
            tournament.getBracketsTemplate().addBracket(bracket);
        }

    }

    public static void updateResultsOfRoundRobin(String tournamentName, RobinBracket bracket){
        User user = User.findByUsername(session.get("username"));
        Tournament tournament = Tournament.findByName(tournamentName);
        if (Ownership.isTournamentOwner(user,tournament)){
            tournament.getResults().replaceBracket(bracket);
        }
    }

    public static void updateResultsOfSingleElim(String tournamentName,ElimBracket bracket){
        User user = User.findByUsername(session.get("username"));
        Tournament tournament = Tournament.findByName(tournamentName);
        if (Ownership.isTournamentOwner(user,tournament)){
            tournament.getResults().replaceBracket(bracket);
        }
    }

}
